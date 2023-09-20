package com.zyramc.lobby.cmd;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public class RemoveArmorStands implements CommandExecutor {
    private final JavaPlugin plugin;

    public RemoveArmorStands(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Uso correto: /removerarmorstands <nome_do_mundo>");
            return true;
        }

        String worldName = args[0];
        World world = plugin.getServer().getWorld(worldName);

        if (world == null) {
            sender.sendMessage("Mundo não encontrado.");
            return true;
        }

        int removedCount = removeArmorStandsInWorld(world);
        sender.sendMessage("Foram removidas " + removedCount + " ArmorStands do mundo '" + world.getName() + "'.");

        return true;
    }

    private int removeArmorStandsInWorld(World world) {
        int count = 0;
        for (Entity entity : world.getEntitiesByClass(ArmorStand.class)) {
            entity.remove();
            count++;
        }
        return count;
    }
}
