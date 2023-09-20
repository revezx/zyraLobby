package com.zyramc.lobby.cmd;

import com.zyramc.lobby.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            return true;
        }else {
            Player player = (Player) sender;
            if (player.hasPermission("lobby.spawn.reset")){
                Main.configSpawn.clearSection("");
                Main.configSpawn.saveConfig();
                player.sendMessage("§aLocal de spawn resetado.");
            }else {
                player.sendMessage("§cVocê não tem permissão");
            }
        }
        return true;
    }
}
