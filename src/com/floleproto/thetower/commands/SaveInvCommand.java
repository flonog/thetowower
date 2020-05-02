package com.floleproto.thetower.commands;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.save.InventorySave;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveInvCommand implements CommandExecutor {
    private Main main;

    public SaveInvCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        InventorySave.saveInventory("spectator", p.getInventory().getContents(), p.getInventory().getArmorContents());
        System.out.println(InventorySave.loadInventory("spectator")[0][0]);
        return true;
    }
}
