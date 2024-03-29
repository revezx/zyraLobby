package com.zyramc.lobby.hotbar.guis;

import com.zyramc.lobby.api.BungeeConnector;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
public class MenuGames implements Listener {

    public static ItemStack bussolaMenu() {
        ItemStack bussola = new ItemStack(Material.COMPASS);
        ItemMeta meta = bussola.getItemMeta();

        meta.setDisplayName("§a➽ Menu de Jogos §7(Clique Direito)");

        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(" §7Abra o menu para selecionar");
        lore.add(" §7um Jogo.");
        lore.add(" ");
        meta.setLore(lore);

        bussola.setItemMeta(meta);
        return bussola;
    }

    @EventHandler
    public void setJoinItem(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.getInventory().setItem(4, bussolaMenu());
    }

    public static void openMenu(Player player){
        Inventory inventory = Bukkit.createInventory(player, 3 * 9, "§a§lJogos");

        ItemStack rankup = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta metaRankup = (SkullMeta) rankup.getItemMeta();
        metaRankup.setOwner("meatmods");
        metaRankup.setDisplayName("§d§lNOVO! §a§lRankUP HARVEST");
        List<String> loreRankup = new ArrayList<>();
        loreRankup.add(" ");
        loreRankup.add("  §7Um dos melhores RankUP ");
        loreRankup.add("  §7de plantações do Minecraft. ");
        loreRankup.add(" ");
        loreRankup.add("  §aNovidades:");
        loreRankup.add("    §7◌ Crates ");
        loreRankup.add("    §7◌ Bosses ");
        loreRankup.add("    §7◌ Máquinas ");
        loreRankup.add("    §7◌ Pesca ");
        loreRankup.add("    §7◌ Bosses ");
        loreRankup.add(" ");
        loreRankup.add("§aClique para jogar.");
        metaRankup.setLore(loreRankup);
        rankup.setItemMeta(metaRankup);

        inventory.setItem(13, rankup);


        player.openInventory(inventory);
    }

    @EventHandler
    public void clickEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack clickItem = e.getItem();

        if (clickItem != null){
            ItemMeta meta = clickItem.getItemMeta();
            if (meta != null
                    && meta.hasDisplayName()
                    && meta.getDisplayName().equalsIgnoreCase("§a➽ Menu de Jogos §7(Clique Direito)")){
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
                    openMenu(player);
                } else {
                    return;
                }
            }
        }

    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent e){
        ItemStack dropItem = e.getItemDrop().getItemStack();
        if (dropItem != null){
            ItemMeta meta = dropItem.getItemMeta();
            if (meta != null
                    && meta.hasDisplayName()
                    && meta.getDisplayName().equalsIgnoreCase("§a➽ Menu de Jogos §7(Clique Direito)")){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){
        ItemStack clickItem = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        if (clickItem != null){
            ItemMeta meta = clickItem.getItemMeta();
            if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§a➽ Menu de Jogos §7(Clique Direito)")){
                e.setCancelled(true);
            } else if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§aRankUp §f§lNOVO!")) {
                BungeeConnector.addToQueue(player, "rankup");
                player.closeInventory();
                e.setCancelled(true);
            } else if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§f§lEM BREVE")) {
                e.setCancelled(true);
            }
        }
    }

}
