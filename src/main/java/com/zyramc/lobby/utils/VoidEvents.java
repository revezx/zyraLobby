package com.zyramc.lobby.utils;

import com.zyramc.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidEvents implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if (e.getPlayer().getLocation().getY() < -10){
            teleportVoid(player);
        }
    }

    private void teleportVoid(Player player){
        if (Main.configSpawn.contains("spawn")){
            player.teleport(Main.configSpawn.getLocation("spawn"));
        }
    }

}
