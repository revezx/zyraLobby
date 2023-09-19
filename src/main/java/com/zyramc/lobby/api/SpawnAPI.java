package com.zyramc.lobby.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnAPI {

    private JavaPlugin plugin;
    private String name;
    private File file;
    private FileConfiguration config;

    public SpawnAPI(String name, JavaPlugin plugin) {
        this.plugin = plugin;
        this.name = name;
        reloadConfig();
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


    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setLocation(String path,
                            Location location) {
        setLocation(create(path), location);
    }
    public Location getLocation(String path) {
        return getLocation(getSection(path));
    }

    public static void setLocation(ConfigurationSection section,
                                   Location location) {
        section.set("world", location.getWorld().getName());
        section.set("x", location.getX());
        section.set("y", location.getY());
        section.set("z", location.getZ());
        section.set("yaw", location.getYaw());
        section.set("pitch", location.getPitch());
    }

    public static Location getLocation(ConfigurationSection section) {
        World world = Bukkit.getWorld(section.getString("world"));
        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        float yaw = (float) section.getDouble("yaw");
        float pitch = (float) section.getDouble("pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

    public ConfigurationSection create(String path) {
        return config.createSection(path);
    }


    public ConfigurationSection getSection(String path) {
        return config.getConfigurationSection(path);
    }

}