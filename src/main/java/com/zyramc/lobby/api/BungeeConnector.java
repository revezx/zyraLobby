package com.zyramc.lobby.api;

import com.zyramc.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BungeeConnector {

    public static Plugin plugin = Main.getInstance();
    public static Queue<Player> playerQueue = new LinkedList<>();

    public static void addToQueue(Player player, String serverName) {
        player.sendMessage("Aguarde na fila...");

        int position = playerQueue.size() + 1;
        notifyPositionInQueue(player, position);
        new BukkitRunnable() {
            @Override
            public void run() {
                addToQueueDelayed(player, serverName);
            }
        }.runTaskLater(plugin, 100);
    }

    public static void addToQueueDelayed(Player player, String serverName) {
        playerQueue.add(player);

        if (playerQueue.peek() == player){
            connectToServer(player, serverName);
            playerQueue.remove();
        }
    }

    public static void notifyPositionInQueue(Player player, int position) {
        ActionBarAPI.sendActionBar(player, "§eVocê está na posição §f#" + position + " §ena fila de espera.");
    }

    public static void connectToServer(Player player, String serverName) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("ConnectOther");
            out.writeUTF(player.getName());
            out.writeUTF(serverName);
        } catch (IOException e) {
            e.printStackTrace();
        }
            player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

}
