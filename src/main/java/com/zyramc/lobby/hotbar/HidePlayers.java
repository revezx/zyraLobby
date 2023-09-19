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
        ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta limeDyeMeta = limeDye.getItemMeta();

        limeDyeMeta.setDisplayName("§aOcultar Players §7(Clique Direito)");

        limeDye.setItemMeta(limeDyeMeta);
        return limeDye;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        player.getInventory().setItem(7, hidePlayers());
    }

}
