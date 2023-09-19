package com.zyramc.lobby.api;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigAPI {

    private JavaPlugin plugin;
    private String name;
    private File file;

    private FileConfiguration config;

    public ConfigAPI(String name, JavaPlugin plugin){
        this.plugin = plugin;
        this.name = name;
        reloadConfig();
    }

    public void saveConfig(){
        try {
            config.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void reloadConfig() {
        file = new File(plugin.getDataFolder(), name);
        config = YamlConfiguration.loadConfiguration(file);
        InputStream configFile = plugin.getResource(name);

        if (configFile != null) {
            InputStreamReader reader = new InputStreamReader(configFile);
            YamlConfiguration loadConfig = YamlConfiguration.loadConfiguration(reader);
            config.setDefaults(loadConfig);
        }
    }


    public boolean contains(String path){
        return config.contains(path);
    }

    public ConfigurationSection create(String path){
        return config.createSection(path);
    }

    public ConfigurationSection getSection(String path){
        return config.getConfigurationSection(path);
    }
}
