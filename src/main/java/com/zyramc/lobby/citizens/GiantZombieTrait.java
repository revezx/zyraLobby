package com.zyramc.lobby.citizens;

import net.citizensnpcs.api.trait.Trait;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GiantZombieTrait extends Trait {

    protected GiantZombieTrait(String name) {
        super(name);
    }

    public GiantZombieTrait() {
        super("giantZombie");
    }

    @Override
    public void run() {
        if (!npc.isSpawned()) {
            return;
        }

        Zombie zombie = (Zombie) npc.getEntity();

        // Defina o zumbi como invisível
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));

        // Defina o tamanho do zumbi como gigante
        zombie.setFireTicks(6);

        // Crie um item gigante para o zumbi segurar
        ItemStack giantItem = new ItemStack(Material.DIAMOND_SWORD);
        giantItem.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10); // Adicione encantamentos personalizados, se desejar

        // Defina o item que o zumbi está segurando
        zombie.getEquipment().setItemInHand(giantItem);
    }
}
