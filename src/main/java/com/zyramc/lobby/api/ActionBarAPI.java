package com.zyramc.lobby.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class ActionBarAPI {

    public static void sendActionBar(Player player, String message) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        try {
            Class<?> chatSerializerClass = Class.forName("net.minecraft.server." + version + ".IChatBaseComponent$ChatSerializer");
            Class<?> packetPlayOutChatClass = Class.forName("net.minecraft.server." + version + ".PacketPlayOutChat");

            Object chatBaseComponent = chatSerializerClass.getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}");

            Constructor<?> packetConstructor = packetPlayOutChatClass.getConstructor(
                    Class.forName("net.minecraft.server." + version + ".IChatBaseComponent"),
                    byte.class
            );

            Object packet = packetConstructor.newInstance(chatBaseComponent, (byte) 2); // ActionBar (2) enum value

            Object craftPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = craftPlayer.getClass().getField("playerConnection").get(craftPlayer);
            playerConnection.getClass().getMethod("sendPacket", packetPlayOutChatClass.getInterfaces()[0]).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendActionBarToAllPlayers(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendActionBar(player, message);
        }
    }
}
