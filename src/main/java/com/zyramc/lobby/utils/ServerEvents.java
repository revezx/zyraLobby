package com.zyramc.lobby.utils;


import com.zyramc.lobby.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerEvents implements Listener {

    @EventHandler
    public void spawnJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        e.setJoinMessage(null);
        if (Main.configSpawn.contains("spawn")){
            player.teleport(player.getWorld().getSpawnLocation());

            player.setHealth(20.0);
            player.setHealthScale(2.0);
            player.setFoodLevel(20);
            player.setWalkSpeed(0.3f);

            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
        }
    }

    @EventHandler
    public void quitServer(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }

    @EventHandler
    public void noFood(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void noDamage(EntityDamageEvent e){
        e.setCancelled(true);
    }

}
