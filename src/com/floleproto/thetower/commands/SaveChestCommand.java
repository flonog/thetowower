package com.floleproto.thetower.commands;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.save.PositionSave;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SaveChestCommand implements CommandExecutor {
    private Main main;

    public SaveChestCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("thetowower.command.savechest") || !commandSender.hasPermission("thetowower.command.*") || !commandSender.hasPermission("thetowower.*") || !commandSender.isOp()) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c You don't have the permission to execute this command.");
            return false;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c Only player can execute this command.");
        }
        if (strings.length < 1) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c /savechest <add | remove>.");
            return false;
        }
        Player p = (Player) commandSender;

        if (strings[0].equalsIgnoreCase("add")) {

            Set<Material> chests = new HashSet<Material>(Arrays.asList(new Material[]{Material.AIR}));
            Block block = p.getTargetBlock(chests, 4);

            System.out.println(block);

            if (block == null) {
                commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c Please look at a chest.");
                return false;
            }
            if (!block.getType().equals(Material.CHEST)) {
                commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c This is not a chest.");
                return false;
            }
            PositionSave.registerChestPos(block.getLocation());

            try {
                PositionSave.saveChests();
            } catch (IOException e) {
                e.printStackTrace();
            }

            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§a Chest saved successfully.");
            return true;
        } else if (strings[0].equalsIgnoreCase("remove")) {
            Set<Material> chests = new HashSet<Material>(Arrays.asList(new Material[]{Material.AIR}));
            Block block = p.getTargetBlock(chests, 4);

            System.out.println(block);

            if (block == null) {
                commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c Please look at a chest.");
                return false;
            }
            if (!block.getType().equals(Material.CHEST)) {
                commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c This is not a chest.");
                return false;
            }
            PositionSave.removeChestPos(block.getLocation());
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§a Chest removed successfully.");

            try {
                PositionSave.saveChests();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        } else {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c /savechest <add | remove>.");
        }
        return false;
    }
}
