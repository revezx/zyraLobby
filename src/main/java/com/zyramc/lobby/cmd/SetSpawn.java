package com.zyramc.lobby.cmd;

import com.zyramc.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)){
            return true;
        }

        if (!Main.configSpawn.contains("spawn")){
            Player player = (Player) sender;
            if (player.hasPermission("lobby.spawn.admin") || player.isOp()){
                Main.configSpawn.setLocation("spawn", player.getLocation());
                Main.configSpawn.saveConfig();
                player.sendMessage("");
                player.sendMessage(" §a§lPERFEITO!");
                player.sendMessage("");
                player.sendMessage(" §fLobby setado com sucesso");
                player.sendMessage(" §fos players podem usar /spawn agora.");
                player.sendMessage("");
                player.sendMessage(" §7www.zyramc.com");
                player.sendMessage("");
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
            }else {
                player.sendMessage("");
                player.sendMessage(" §c§lERRO!");
                player.sendMessage("");
                player.sendMessage(" §fVocê não tem permissão para");
                player.sendMessage(" §futilizar este comando.");
                player.sendMessage("");
                player.sendMessage(" §7www.zyramc.com");
                player.sendMessage("");
                player.playSound(player.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);
                return true;
            }
        }else {
            Player player = (Player) sender;
            player.sendMessage("");
            player.sendMessage(" §c§lERRO!");
            player.sendMessage("");
            player.sendMessage(" §fO spawn já foi setado, utilize o comando");
            player.sendMessage(" §f/resetspawn para resetar a config.");
            player.sendMessage(" §cO comando /resetspawn ainda está ");
            player.sendMessage(" §cem manutenção.");
            player.sendMessage("");
            player.sendMessage(" §7www.zyramc.com");
            player.sendMessage("");
            player.playSound(player.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);
        }

        return true;
    }

}
