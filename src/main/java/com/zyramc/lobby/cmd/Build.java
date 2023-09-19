package com.zyramc.lobby.cmd;

import com.zyramc.lobby.api.ActionBarAPI;
import com.zyramc.lobby.hotbar.HidePlayers;
import com.zyramc.lobby.hotbar.guis.Lobbies;
import com.zyramc.lobby.hotbar.guis.MenuGames;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Build implements CommandExecutor {
    private boolean buildEnable = false;
    private GameMode original = GameMode.ADVENTURE;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("build")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (player.hasPermission("lobby.build.command") || player.isOp()) {
                    buildEnable = !buildEnable;
                    if (buildEnable) {
                        original = player.getGameMode();
                        ActionBarAPI.sendActionBar(player, "§aModo Build ativado");
                        player.setGameMode(GameMode.CREATIVE);
                        player.getInventory().clear();
                    } else {
                        if (player.getAllowFlight()){
                            player.setAllowFlight(false);
                        }
                        ActionBarAPI.sendActionBar(player, "§eModo Build desativado");
                        player.setGameMode(original);
                        player.getInventory().setItem(0, MenuGames.bussolaMenu());
                        player.getInventory().setItem(7, HidePlayers.hidePlayers());
                        player.getInventory().setItem(8, Lobbies.lobbiesMenu());
                    }
                } else {
                    player.sendMessage("");
                    player.sendMessage(" §c§lERRO!");
                    player.sendMessage("");
                    player.sendMessage(" §fVocê não tem permissão para");
                    player.sendMessage(" §futilizar este comando.");
                    player.sendMessage("");
                    player.sendMessage(" §7www.redeliberty.com");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);
                }
            } else {
                sender.sendMessage("Este comando só pode ser usado por jogadores.");
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if (!buildEnable && !player.hasPermission("lobby.build.event") || !player.isOp()) {
            e.setCancelled(true);
        }
        if (buildEnable && !player.hasPermission("lobby.build.event") || !player.isOp()){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if (!buildEnable && !player.hasPermission("lobby.build.event") || !player.isOp()) {
            e.setCancelled(true);
        }
        if (buildEnable && !player.hasPermission("lobby.build.event") || !player.isOp()){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (buildEnable){
            buildEnable = false;
        }
    }
}
