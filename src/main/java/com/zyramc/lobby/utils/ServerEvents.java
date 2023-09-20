package com.zyramc.lobby.utils;


import com.zyramc.lobby.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerEvents implements Listener {
    private static boolean cooldownActive = false;

    @EventHandler
    public void spawnJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(null);

        player.setHealth(20.0);
        player.setHealthScale(2.0);
        player.setFoodLevel(20);
        player.setWalkSpeed(0.3f);

        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();

        player.getInventory().setHeldItemSlot(0);
        startCooldown(player);
    }

    @EventHandler
    public void quitServer(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        e.getPlayer().getInventory().setHeldItemSlot(0);
    }

    @EventHandler
    public void noFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void noDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    public static void startCooldown(Player player) {
        if (!cooldownActive) {
            cooldownActive = true;

            // Define o slot para 0
            player.getInventory().setHeldItemSlot(0);

            // Agendar uma tarefa para alternar para o slot 4 após 1 segundo (20 ticks)
            new BukkitRunnable() {
                @Override
                public void run() {
                    // Define o slot para 4 após 1 segundo
                    player.getInventory().setHeldItemSlot(4);
                    cooldownActive = false;
                }
            }.runTaskLater(Main.getPlugin(Main.class), 20); // 20 ticks é igual a 1 segundo

            // Você pode ajustar o tempo do cooldown conforme necessário
        } else {
            player.sendMessage("Aguarde o cooldown terminar.");
        }
    }

}
