package com.floleproto.thetower.commands;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameStates;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    private final Main main;

    public StartCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.hasPermission("thetowower.command.start") || !commandSender.hasPermission("thetowower.command.*") || !commandSender.hasPermission("thetowower.*") || !commandSender.isOp()){
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c You don't have the permission to execute this command.");
            return false;
        }

        if(!main.gameManager.isStates(GameStates.WAITING)){
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c The game has already started.");
            return false;
        }

        if(main.gameManager.isStarting()){
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c The game is already starting. Execute /cancelstart to cancel.");
            return false;
        }

        commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§a The game will start in 10 seconds. Execute /cancelstart to cancel.");
        main.gameManager.forceStartCountdown();
        return true;
    }
}