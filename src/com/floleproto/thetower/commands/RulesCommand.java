package com.floleproto.thetower.commands;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.game.GameStates;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCommand implements CommandExecutor {
    private Main main;

    public RulesCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!main.gameManager.isStates(GameStates.ONGAME)) {
            commandSender.sendMessage("§b§lThe TOwOwer §4§l>§1§l>§c The game hasn't started yet.");
            return false;
        }

        GameConfig.sendRules(commandSender);

        return true;
    }
}
