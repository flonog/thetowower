package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;

public class ScoreboardManager {

    public HashMap<Player, FastBoard> scoreboards = new HashMap<>();

    public void setScoreboardTemplate(Player p, GameStates states) {
        FastBoard fastBoard = scoreboards.get(p);
        DecimalFormat decimalFormat = new DecimalFormat("000");
        switch (states) {
            case WAITING -> fastBoard.updateLines(
                    "",
                    "Waiting for players",
                    "");
            case FINISH -> fastBoard.updateLines(
                    "",
                    "§aKills  §r§cDeaths",
                    "§r" + decimalFormat.format(Main.instance.statistics.getKills(p)) + "   " + decimalFormat.format(Main.instance.statistics.getDeaths(p)),
                    "",
                    "OwO",
                    "",
                    "");
            case ONGAME -> fastBoard.updateLines(
                    "",
                    "§aKills  §r§cDeaths",
                    "§r" + decimalFormat.format(Main.instance.statistics.getKills(p)) + "   " + decimalFormat.format(Main.instance.statistics.getDeaths(p)),
                    "",
                    "§4§lRed§r   " + TeamManager.redTeam.getScore(),
                    "§1§lBlue§r  " + TeamManager.blueTeam.getScore(),
                    "",
                    "Time §e00:00");
        }
    }

    public void createScoreboard(Player p) {
        FastBoard fastBoard = new FastBoard(p);
        fastBoard.updateTitle("§b§lThe TOwOwer");
        scoreboards.put(p, fastBoard);
    }

    public void setTimer(String time) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            FastBoard fastBoard = scoreboards.get(p);
            fastBoard.updateLine(7, "Time §e" + time);
            scoreboards.put(p, fastBoard);
        }
    }

    public void setScore() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            FastBoard fastBoard = scoreboards.get(p);
            fastBoard.updateLine(4, "§4§lRed§r   " + TeamManager.redTeam.getScore());
            fastBoard.updateLine(5, "§1§lBlue§r  " + TeamManager.blueTeam.getScore());
            scoreboards.put(p, fastBoard);
        }
    }

    public void setWinner(String teamName) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            FastBoard fastBoard = scoreboards.get(p);
            fastBoard.updateLine(4, "§b" + teamName + " §bwon !");
            scoreboards.put(p, fastBoard);
        }
    }

    public void refreshKillsDeaths(Player p) {
        FastBoard fastBoard = scoreboards.get(p);
        DecimalFormat decimalFormat = new DecimalFormat("000");
        fastBoard.updateLine(2, "§r" + decimalFormat.format(Main.instance.statistics.getKills(p)) + "   " + decimalFormat.format(Main.instance.statistics.getDeaths(p)));
        scoreboards.put(p, fastBoard);
    }

    public void removeScoreboard(Player p) {
        scoreboards.remove(p);
    }

}
