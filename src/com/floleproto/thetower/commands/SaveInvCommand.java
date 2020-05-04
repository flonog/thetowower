package com.floleproto.thetower.commands;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameStates;
import com.floleproto.thetower.game.Team;
import com.floleproto.thetower.game.save.InventorySave;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SaveInvCommand implements CommandExecutor {
    public static HashMap<Player, Team> playersEditing = new HashMap<>();
    private Main main;

    public SaveInvCommand(Main main) {
        this.main = main;
    }

    public static void openEdit(Player p, Team team) {
        p.setGameMode(GameMode.CREATIVE);
        p.setAllowFlight(false);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§a You are editing the inventory of " + team.getName() + " §ateam. Execute \"/save\" in the chat to save your changes.");

        team.setTeamInventory(p);

        playersEditing.put(p, team);
    }

    public static void closeEdit(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        playersEditing.remove(p);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!commandSender.hasPermission("thetowower.config") || !commandSender.hasPermission("thetowower.*") || !commandSender.isOp()) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c You don't have the permission to execute this command.");
            return false;
        }

        if (!main.gameManager.isStates(GameStates.WAITING)) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c You can't use this command in game.");
            return false;
        }

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c You are not a player.");
            return false;
        }

        Player p = (Player) commandSender;

        if (!playersEditing.containsKey(p)) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c You are not editing an inventory.");
            return false;
        }

        Team team = playersEditing.get(p);

        InventorySave.saveInventory(team.getTag(), p.getInventory().getContents(), p.getInventory().getArmorContents());
        closeEdit(p);

        Map<Enchantment, Integer> enchants = new HashMap<>();
        enchants.put(Enchantment.DURABILITY, 1);
        p.getInventory().setItem(4, new ItemCreator(Material.WOOL, 1, (byte) 0, "§b§lTeam", null, enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        p.getInventory().setItem(8, new ItemCreator(Material.COMMAND, 1, (byte) 0, "§c§lConfig", null, enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());

        return true;
    }
}
