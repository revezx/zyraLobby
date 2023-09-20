package com.zyramc.lobby.cmd;

import com.zyramc.lobby.Main;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (Main.configSpawn.contains("spawn")){
                player.teleport(Main.configSpawn.getLocation("spawn"));
                return true;
            }else {
                player.sendMessage("");
                player.sendMessage(" §c§lERRO!");
                player.sendMessage("");
                player.sendMessage(" §fO Spawn ainda não foi setado,");
                player.sendMessage(" §fcaso você seja um moderador");
                player.sendMessage(" §fvá até o local e");
                player.sendMessage(" §fuse: /setspawn");
                player.sendMessage("");
                player.sendMessage(" §7www.zyramc.com");
                player.sendMessage("");
                player.playSound(player.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);
            }
        }else{
            sender.sendMessage("§cEste comando só pode ser executado por players.");
        }
        return true;
    }
}
