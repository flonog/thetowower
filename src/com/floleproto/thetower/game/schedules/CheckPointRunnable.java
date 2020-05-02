package com.floleproto.thetower.game.schedules;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class CheckPointRunnable extends BukkitRunnable {
    @Override
    public void run() {
        TeamManager teamManager = Main.instance.teamManager;
        Player[] players = teamManager.redTeam.getPool().getPlayersInside();
        if(players.length >= 1){
            for (Object p : Arrays.stream(players).filter(x -> teamManager.getTeam(x).getTag() == "blue").toArray()) {
                teamManager.blueTeam.scorePoint((Player) p);
            };
            Main.instance.scoreboardManager.setScore();
        }

        players = teamManager.blueTeam.getPool().getPlayersInside();
        if(players.length >= 1){
            for (Object p : Arrays.stream(players).filter(x -> teamManager.getTeam(x).getTag() == "red").toArray()) {
                teamManager.redTeam.scorePoint((Player) p);
            };
            Main.instance.scoreboardManager.setScore();
        }
    }
}
