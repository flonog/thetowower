package com.floleproto.thetower.game.runnables;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.TeamManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class CheckPointRunnable extends BukkitRunnable {
    @Override
    public void run() {
        TeamManager teamManager = Main.instance.teamManager;
        Player[] players = TeamManager.redTeam.getPool().getPlayersInside();
        if (players.length >= 1) {
            for (Object p : Arrays.stream(players).filter(x -> teamManager.getTeam(x).getTag() == "blue").toArray()) {
                TeamManager.blueTeam.scorePoint((Player) p);
            }
        }

        players = TeamManager.blueTeam.getPool().getPlayersInside();
        if (players.length >= 1) {
            for (Object p : Arrays.stream(players).filter(x -> teamManager.getTeam(x).getTag() == "red").toArray()) {
                TeamManager.redTeam.scorePoint((Player) p);
            }
        }
    }
}
