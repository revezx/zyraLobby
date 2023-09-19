package com.zyramc.lobby.utils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldEvents implements Listener {

    @EventHandler
    public void onBurn(BlockBurnEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onToMelt(BlockFadeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void weatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }

}
