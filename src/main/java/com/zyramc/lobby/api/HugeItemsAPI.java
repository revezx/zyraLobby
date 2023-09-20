package com.zyramc.lobby.api;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class HugeItemsAPI implements Listener {
    private static int i = 0;

    public static void spawn(Location loc, ItemStack item) {
        Giant giant = (Giant)loc.getWorld().spawn(loc, Giant.class);
        giant.getEquipment().setItemInHand(item);
        giant.setCustomName("HugeItemHolder");
        giant.setCustomNameVisible(false);
        giant.getEquipment().setItemInHandDropChance(0.0F);
        giant.setCanPickupItems(false);
        giant.setRemoveWhenFarAway(false);
        giant.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
                2147483647, 999));
        ArmorStand stand = (ArmorStand)loc.getWorld().spawn(loc.clone().subtract(0.0D, 8.0D, 0.0D),
                ArmorStand.class);
        stand.setCustomName("HugeItemStand");
        stand.setCustomNameVisible(false);
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setRemoveWhenFarAway(false);
        stand.setPassenger((Entity)giant);
    }

    public static void remove(Player p) {
        List<Entity> entities = p.getNearbyEntities(4.0D, 8.0D, 4.0D);
        setI(0);
        for (Entity entity : entities) {
            if (entity instanceof ArmorStand) {
                ArmorStand stand = (ArmorStand)entity;
                if (stand.getPassenger() instanceof Giant) {
                    Giant giant = (Giant)stand.getPassenger();
                    if (stand.getCustomName().equalsIgnoreCase("HugeItemStand"))
                        if (p.getLocation().getY() > stand.getLocation().getY()) {
                            giant.remove();
                            stand.remove();
                            setI(getI() + 1);
                        }
                }
            }
        }
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        HugeItemsAPI.i = i;
    }
}
