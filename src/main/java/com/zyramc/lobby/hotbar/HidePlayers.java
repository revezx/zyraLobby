package com.zyramc.lobby.hotbar;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HidePlayers implements Listener {

    public static ItemStack hidePlayers(){
        ItemStack purpleDye = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta purpleDyeMeta = purpleDye.getItemMeta();

        purpleDyeMeta.setDisplayName("§a✦ Ocultar Players §7(Clique Direito)");

        purpleDye.setItemMeta(purpleDyeMeta);
        return purpleDye;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        player.getInventory().setItem(2, hidePlayers());
    }

}
