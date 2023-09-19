package com.zyramc.lobby;

import com.zyramc.lobby.api.SpawnAPI;
import com.zyramc.lobby.cmd.Fly;
import com.zyramc.lobby.hotbar.HidePlayers;
import com.zyramc.lobby.hotbar.guis.Lobbies;
import com.zyramc.lobby.hotbar.guis.MenuGames;
import com.zyramc.lobby.cmd.Build;
import com.zyramc.lobby.cmd.SetSpawn;
import com.zyramc.lobby.cmd.Spawn;
import com.zyramc.lobby.utils.HidePlayersUtil;
import com.zyramc.lobby.utils.ServerEvents;
import com.zyramc.lobby.utils.VoidEvents;
import com.zyramc.lobby.utils.WorldEvents;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin {

    public static Main instance;
    public static SpawnAPI config;
    @Override
    public void onEnable() {
        instance = this;
        config = new SpawnAPI("world/spawn.yml", this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        getServer().getPluginManager().registerEvents(new WorldEvents(), this);
        getServer().getPluginManager().registerEvents(new ServerEvents(), this);
        getServer().getPluginManager().registerEvents(new VoidEvents(), this);
        getServer().getPluginManager().registerEvents(new MenuGames(), this);
        getServer().getPluginManager().registerEvents(new HidePlayers(), this);
        getServer().getPluginManager().registerEvents(new HidePlayersUtil(this), this);
        getServer().getPluginManager().registerEvents(new Lobbies(), this);

        getCommand("build").setExecutor(new Build());
        getCommand("fly").setExecutor(new Fly());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("setspawn").setExecutor(new SetSpawn());

    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
}