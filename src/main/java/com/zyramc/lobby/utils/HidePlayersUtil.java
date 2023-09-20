package com.zyramc.lobby.utils;

import com.zyramc.lobby.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class HidePlayersUtil implements Listener {

    private final Main plugin;

    private Map<Player, Long> cooldownMap = new HashMap<>();

    public HidePlayersUtil(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent e){
        ItemStack dropItem = e.getItemDrop().getItemStack();
        if (dropItem != null){
            ItemMeta meta = dropItem.getItemMeta();
            if (meta != null
                    && meta.hasDisplayName()
                    && meta.getDisplayName().equalsIgnoreCase("§a➽ Ocultar Players §7(Clique Direito)")){
                e.setCancelled(true);
            }
            if (meta != null
                    && meta.hasDisplayName()
                    && meta.getDisplayName().equalsIgnoreCase("§a➽ Mostrar Players §7(Clique Direito)")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){
        ItemStack clickItem = e.getCurrentItem();
        if (clickItem != null){
            ItemMeta meta = clickItem.getItemMeta();
            if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§a➽ Mostrar Players §7(Clique Direito)")){
                e.setCancelled(true);
            } else if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§a➽ Ocultar Players §7(Clique Direito)")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack itemInHand = player.getItemInHand();

            if (itemInHand != null && itemInHand.hasItemMeta()) {
                ItemMeta itemMeta = itemInHand.getItemMeta();
                String displayName = itemMeta.getDisplayName();

                if (displayName.equals("§a➽ Ocultar Players §7(Clique Direito)")) {
                    // Lime Dye foi clicado, ocultar os jogadores
                    if (cooldownMap.containsKey(player)) {
                        long lastUsage = cooldownMap.get(player);
                        long currentTime = System.currentTimeMillis();
                        long timeElapsed = currentTime - lastUsage;

                        if (timeElapsed < 20000) {
                            long remainingTime = 20000 - timeElapsed;
                            player.sendMessage("");
                            player.sendMessage(" §cAguarde " + (remainingTime / 1000) + " segundos");
                            player.sendMessage(" §cantes de usar novamente.");
                            player.sendMessage("");
                            player.playSound(player.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);
                            return;
                        }
                    }

                    for (Player otherPlayer : player.getServer().getOnlinePlayers()) {
                        if (!otherPlayer.equals(player)) {
                            player.hidePlayer(otherPlayer);
                        }
                    }

                    // Mudar o item para Gray Dye
                    itemInHand.setType(Material.INK_SACK);
                    itemInHand.setDurability((short) 8);
                    itemMeta.setDisplayName("§a➽ Mostrar Players §7(Clique Direito)");
                    itemInHand.setItemMeta(itemMeta);

                    if (!player.hasPermission("lobby.players") || !player.isOp()) {
                        cooldownMap.put(player, System.currentTimeMillis());
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                cooldownMap.remove(player);
                            }
                        }.runTaskLater(plugin, 400);
                    }
                } else if (displayName.equals("§a➽ Mostrar Players §7(Clique Direito)")) {
                    // Gray Dye foi clicado, mostrar os jogadores
                    for (Player otherPlayer : player.getServer().getOnlinePlayers()) {
                        if (!otherPlayer.equals(player)) {
                            player.showPlayer(otherPlayer);
                        }
                    }

                    // Mudar o item para Lime Dye
                    itemInHand.setType(Material.INK_SACK);
                    itemInHand.setDurability((short) 10);
                    itemMeta.setDisplayName("§a➽ Ocultar Players §7(Clique Direito)");
                    itemInHand.setItemMeta(itemMeta);
                }
            }
        }

        event.setCancelled(true); // Cancela o evento para evitar ações adicionais no clique esquerdo.
    }


}
