package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.utils.ScoreboardSign;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;

public class ScoreboardManager {

    public HashMap<Player, ScoreboardSign> scoreboards = new HashMap<>();

    public void setScoreboardTemplate(Player p, GameStates states) {
        ScoreboardSign scoreboardSign = scoreboards.get(p);
        DecimalFormat decimalFormat = new DecimalFormat("000");
        switch (states) {
            case WAITING:
                scoreboardSign.setLine(0, "§c");
                scoreboardSign.setLine(1, "Waiting for players");
                scoreboardSign.setLine(2, "§d");
                break;
            case FINISH:
                scoreboardSign.setLine(0, "§c");
                scoreboardSign.setLine(1, "§aKills  §r§cDeaths");
                scoreboardSign.setLine(2, "§r" + decimalFormat.format(Main.instance.statistics.getKills(p)) + "   " + decimalFormat.format(Main.instance.statistics.getDeaths(p)));
                scoreboardSign.setLine(3, "§d");
                scoreboardSign.setLine(4, "§bBlue won !");
                scoreboardSign.setLine(5, "§e");
                scoreboardSign.setLine(6, "§f");
                break;
            case ONGAME:
                scoreboardSign.setLine(0, "§c");
                scoreboardSign.setLine(1, "§aKills  §r§cDeaths");
                scoreboardSign.setLine(2, "§r" + decimalFormat.format(Main.instance.statistics.getKills(p)) + "   " + decimalFormat.format(Main.instance.statistics.getDeaths(p)));
                scoreboardSign.setLine(3, "§d");
                scoreboardSign.setLine(4, "§4§lRed§r   " + TeamManager.redTeam.getScore());
                scoreboardSign.setLine(5, "§1§lBlue§r  " + TeamManager.blueTeam.getScore());
                scoreboardSign.setLine(6, "§e");
                scoreboardSign.setLine(7, "Time §e00:00");
                break;
        }
    }

    public void createScoreboard(Player p) {
        ScoreboardSign scoreboardSign = new ScoreboardSign(p, "§b§lThe TOwOwer");
        scoreboardSign.create();
        scoreboardSign.setLine(0, "§c");
        scoreboards.put(p, scoreboardSign);
    }

    public void setTimer(String time) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            ScoreboardSign scoreboardSign = scoreboards.get(p);
            scoreboardSign.setLine(7, "Time §e" + time);
            scoreboards.put(p, scoreboardSign);
        }
    }

    public void setScore() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            ScoreboardSign scoreboardSign = scoreboards.get(p);
            scoreboardSign.setLine(4, "§4§lRed§r   " + TeamManager.redTeam.getScore());
            scoreboardSign.setLine(5, "§1§lBlue§r  " + TeamManager.blueTeam.getScore());
            scoreboards.put(p, scoreboardSign);
        }
    }

    public void setWinner(String teamName){
        for (Player p : Bukkit.getOnlinePlayers()) {
            ScoreboardSign scoreboardSign = scoreboards.get(p);
            scoreboardSign.setLine(4, "§b" + teamName +" §bwon !");
            scoreboards.put(p, scoreboardSign);
        }
    }

    public void refreshKillsDeaths(Player p) {
        ScoreboardSign scoreboardSign = scoreboards.get(p);
        DecimalFormat decimalFormat = new DecimalFormat("000");
        scoreboardSign.setLine(2, "§r" + decimalFormat.format(Main.instance.statistics.getKills(p)) + "   " + decimalFormat.format(Main.instance.statistics.getDeaths(p)));
        scoreboards.put(p, scoreboardSign);
    }

    public void removeScoreboard(Player p) {
        scoreboards.remove(p);
    }

}
