package com.zyramc.lobby.hotbar.guis;

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

public class Lobbies implements Listener {

    public static ItemStack lobbiesMenu(){
        ItemStack lobbies = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = lobbies.getItemMeta();

        meta.setDisplayName("§a➽ Menu de Lobbies §7(Clique Direito)");

        lobbies.setItemMeta(meta);
        return lobbies;
    }

    @EventHandler
    public void setJoinItem(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.getInventory().setItem(6, lobbiesMenu());
    }

    public static void openMenu(Player player){
        Inventory inventory = Bukkit.createInventory(player, 3 * 9, "§a§lLobbies");

        ItemStack lobby1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta metaLobby1 = (SkullMeta) lobby1.getItemMeta();
        metaLobby1.setOwner("2013");
        metaLobby1.setDisplayName("§aLobby 1");
        List<String> loreLobby1 = new ArrayList<>();
        loreLobby1.add("§fPlayers online: §a0");
        metaLobby1.setLore(loreLobby1);
        lobby1.setItemMeta(metaLobby1);

        ItemStack lobby2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta metaLobby2 = (SkullMeta) lobby2.getItemMeta();
        metaLobby2.setOwner("2013");
        metaLobby2.setDisplayName("§aLobby 2");
        List<String> loreLobby2 = new ArrayList<>();
        loreLobby2.add("§fPlayers online: §a0");
        metaLobby2.setLore(loreLobby2);
        lobby2.setItemMeta(metaLobby2);

        ItemStack embreve = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta metaEmBreve = (SkullMeta) embreve.getItemMeta();
        metaEmBreve.setOwner("haohanklliu");
        metaEmBreve.setDisplayName("§7Em Breve");
        List<String> loreEmbreve = new ArrayList<>();
        loreEmbreve.add("§fEm Desenvolvimento!");
        metaEmBreve.setLore(loreEmbreve);
        embreve.setItemMeta(metaEmBreve);

        inventory.setItem(10, lobby1);
        inventory.setItem(13, lobby2);
        inventory.setItem(16, embreve);

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
                    && meta.getDisplayName().equalsIgnoreCase("§a➽ Menu de Lobbies §7(Clique Direito)")){
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
                    && meta.getDisplayName().equalsIgnoreCase("§a➽ Menu de Lobbies §7(Clique Direito)")){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){
        ItemStack clickItem = e.getCurrentItem();
        if (clickItem != null){
            ItemMeta meta = clickItem.getItemMeta();
            if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§a➽ Menu de Lobbies §7(Clique Direito)")){
                e.setCancelled(true);
            } else if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§aLobby 1")) {
                e.setCancelled(true);
            } else if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§aLobby 2")) {
                e.setCancelled(true);
            } else if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase("§7Em Breve")) {
                e.setCancelled(true);
            }
        }
    }

}
