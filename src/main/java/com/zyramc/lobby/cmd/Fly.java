package com.zyramc.lobby.cmd;

import com.zyramc.lobby.api.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Fly implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (sender instanceof Player) {
                // Comando para jogadores
                Player player = (Player) sender;

                if (player.hasPermission("lobby.fly") || player.isOp()) {
                    if (args.length == 0) {
                        if (!player.getAllowFlight()) {
                            player.setAllowFlight(true);
                            player.setFlying(true);
                            ActionBarAPI.sendActionBar(player, "§aModo Fly ativado.");
                        } else {
                            player.setAllowFlight(false);
                            player.setFlying(false);
                            ActionBarAPI.sendActionBar(player, "§eModo Fly desativado.");
                        }
                    } else {
                        player.sendMessage("");
                        player.sendMessage(" §e§lINFO!");
                        player.sendMessage("");
                        player.sendMessage(" §fPara utilizar o modo FLy,");
                        player.sendMessage(" §fUse: /fly ou /voar");
                        player.sendMessage("");
                        player.sendMessage(" §7www.redeliberty.com");
                        player.sendMessage("");
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
                    }
                    if (args.length == 1) {
                        if (player.hasPermission("lobby.fly.admin")) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null) {
                                if (!target.getAllowFlight()) {
                                    target.setAllowFlight(true);
                                    target.setFlying(true);
                                    target.sendMessage("");
                                    target.sendMessage(" §e§lAVISO!");
                                    target.sendMessage("");
                                    target.sendMessage(" §fO modo Fly foi §aativado §fpara você");
                                    target.sendMessage(" §fpor um Moderador.");
                                    target.sendMessage("");
                                    target.sendMessage(" §7www.zyramc.com");
                                    target.sendMessage("");

                                    target.playSound(target.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);

                                    sender.sendMessage("§eModo Fly §aativado para §f" + target.getName());
                                } else {
                                    target.setAllowFlight(false);
                                    target.setFlying(false);
                                    target.sendMessage("");
                                    target.sendMessage(" §e§lAVISO!");
                                    target.sendMessage("");
                                    target.sendMessage(" §fO modo Fly foi §cdesativado §fpara você");
                                    target.sendMessage(" §fpor um Moderador.");
                                    target.sendMessage("");
                                    target.sendMessage(" §7www.zyramc.com");
                                    target.sendMessage("");

                                    target.playSound(target.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);

                                    sender.sendMessage("§eModo Fly §cdesativado para §f" + target.getName());
                                }
                            } else {
                                player.sendMessage("jogador não encontrado");
                            }
                        } else {
                            player.sendMessage("");
                            player.sendMessage(" §c§lERRO!");
                            player.sendMessage("");
                            player.sendMessage(" §fVocê não tem permissão para");
                            player.sendMessage(" §futilizar este comando.");
                            player.sendMessage("");
                            player.sendMessage(" §7www.zyramc.com");
                            player.sendMessage("");
                            player.playSound(player.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);
                        }
                    }
                } else {
                    player.sendMessage("");
                    player.sendMessage(" §c§lERRO!");
                    player.sendMessage("");
                    player.sendMessage(" §fVocê não tem permissão para");
                    player.sendMessage(" §futilizar este comando.");
                    player.sendMessage("");
                    player.sendMessage(" §7www.zyramc.com");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);
                }

            } else {
                // Comando para o console
                if (args.length == 1) {
                    if (sender.hasPermission("lobby.fly.admin") || sender.isOp()) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            if (!target.getAllowFlight()) {
                                target.setAllowFlight(true);
                                target.setFlying(true);
                                target.sendMessage("");
                                target.sendMessage(" §e§lAVISO!");
                                target.sendMessage("");
                                target.sendMessage(" §fO modo Fly foi §aativado §fpara você");
                                target.sendMessage(" §fpor um Moderador.");
                                target.sendMessage("");
                                target.sendMessage(" §7www.zyramc.com");
                                target.sendMessage("");

                                target.playSound(target.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);

                                sender.sendMessage("§eModo Fly §aativado para §f" + target.getName());
                            } else {
                                target.setAllowFlight(false);
                                target.setFlying(false);
                                target.sendMessage("");
                                target.sendMessage(" §e§lAVISO!");
                                target.sendMessage("");
                                target.sendMessage(" §fO modo Fly foi §cdesativado §fpara você");
                                target.sendMessage(" §fpor um Moderador.");
                                target.sendMessage("");
                                target.sendMessage(" §7www.zyramc.com");
                                target.sendMessage("");

                                target.playSound(target.getLocation(), Sound.CHICKEN_IDLE, 1.0f, 1.0f);

                                sender.sendMessage("§eModo Fly §cdesativado para §f" + target.getName());
                            }
                        } else {
                            sender.sendMessage("Jogador não encontrado.");
                        }
                    } else {
                        sender.sendMessage("Você não tem permissão");
                    }
                } else {
                    sender.sendMessage("§fUso correto: /fly §e<player>");
                }
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (player.hasPermission("lobby.fly.join") || player.isOp()){
            player.setAllowFlight(true);
            player.setFlying(true);
        }
    }

}
