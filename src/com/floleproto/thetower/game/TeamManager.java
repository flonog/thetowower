package com.floleproto.thetower.game;

import com.floleproto.thetower.game.save.PositionSave;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamManager {

    public static Team redTeam;
    public static Team blueTeam;
    public static Team spectatorTeam;

    public TeamManager() {
        redTeam = new Team("§4§lRed", "red", ChatColor.DARK_RED, PositionSave.redSpawn, PositionSave.redPool, PositionSave.redPoolProtected, PositionSave.redSpawnProtected);
        blueTeam = new Team("§1§lBlue", "blue", ChatColor.DARK_BLUE, PositionSave.blueSpawn, PositionSave.bluePool, PositionSave.bluePoolProtected, PositionSave.blueSpawnProtected);
        spectatorTeam = new Team("Spectator", "spectator", ChatColor.GREEN, PositionSave.redSpawn, null, null, null);
    }


    public void addPlayer(Player p, Team team) {
        if(isInTeam(p))
            removePlayer(p);
        team.addPlayer(p);
    }

    public void removePlayer(Player p) {
        if (redTeam.getPlayers().contains(p)) {
            redTeam.removePlayer(p);
        } else if (blueTeam.getPlayers().contains(p)) {
            blueTeam.removePlayer(p);
        } else {
            spectatorTeam.removePlayer(p);
        }
    }

    public void setRandomTeam(Player p) {
        int redSize = redTeam.getSize();
        int blueSize = blueTeam.getSize();

        if (redSize > blueSize) {
            addPlayer(p, blueTeam);
        } else {
            addPlayer(p, redTeam);
        }
    }

    public Team getTeam(Player p) {
        if (blueTeam.isInTeam(p))
            return blueTeam;
        else if (redTeam.isInTeam(p))
            return redTeam;

        return spectatorTeam;
    }

    public boolean isInTeam(Player p) {
        return redTeam.isInTeam(p) || blueTeam.isInTeam(p) || spectatorTeam.isInTeam(p);
    }
}
