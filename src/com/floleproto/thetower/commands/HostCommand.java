package com.floleproto.thetower.commands;

import com.floleproto.thetower.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HostCommand implements CommandExecutor {
    private Main main;

    public HostCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("thetowower.command.host") || !commandSender.hasPermission("thetowower.command.*") || !commandSender.hasPermission("thetowower.*") || !commandSender.isOp()) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c You don't have the permission to execute this command.");
            return false;
        }

        if (strings.length == 0) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c Please type a message.");
            return false;
        }

        StringBuilder builder = new StringBuilder();
        for (String str : strings) {
            builder.append(str + " ");
        }
        String message = "\n§c§lInformation §4§l>§1§l>§r " + builder.toString() + "\n ";
        main.getServer().broadcastMessage(message.replace('&', '§'));
        return true;
    }
}