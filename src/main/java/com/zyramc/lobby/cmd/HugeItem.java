package com.zyramc.lobby.cmd;

import com.zyramc.lobby.api.HugeItemsAPI;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import static org.bukkit.Material.*;

public class HugeItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player;
        if (sender instanceof Player) {
            player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("setnpc")) {
                // Verifique se o jogador forneceu argumentos suficientes
                if (args.length < 1) {
                    player.sendMessage("Uso correto: /spawnhugeitem <item>");
                    return true;
                }

                // Obtenha o nome do item do argumento
                String itemName = args[0];

                // Verifique se o Material com o nome especificado existe
                Material material = matchMaterial(itemName);
                if (material == null) {
                    player.sendMessage("Item não encontrado.");
                    return true;
                }

                // Crie um ItemStack com o Material
                ItemStack itemStack = new ItemStack(material);

                // Você pode ajustar a quantidade e dados do item se desejar
                if (args.length > 1) {
                    try {
                        int amount = Integer.parseInt(args[1]);
                        itemStack.setAmount(amount);
                    } catch (NumberFormatException e) {
                        player.sendMessage("Quantidade inválida.");
                        return true; // Importante retornar true para evitar que o código abaixo seja executado
                    }
                }

                ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
                Location playerLoc = player.getLocation();

                // Chame a API para criar um item gigante flutuante
                HugeItemsAPI.spawn(playerLoc, espada);
                player.sendMessage("Item gigante flutuante criado com sucesso!");
                return true;
            } else if (cmd.getName().equalsIgnoreCase("removehugeitem")) {
                // Chame a API para remover itens gigantes nas proximidades do jogador
                HugeItemsAPI.remove(player); // Raio de 10 blocos
                player.sendMessage("Itens gigantes removidos nas proximidades.");
                return true;
            }
        } else {
            sender.sendMessage("Este comando só pode ser usado por jogadores.");
        }
        return false;
    }

}
