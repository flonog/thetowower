package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.schedules.CheckPointRunnable;
import com.floleproto.thetower.game.schedules.StartGameRunnable;
import com.floleproto.thetower.game.schedules.TimerRunnable;
import com.floleproto.thetower.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.UUID;

public class GameManager {
    private GameStates states = GameStates.WAITING;
    private StartGameRunnable startGameRunnable = null;
    private TimerRunnable timerRunnable = new TimerRunnable();
    private CheckPointRunnable checkPointRunnable = new CheckPointRunnable();

    public GameStates getStates() {
        return states;
    }

    public void setStates(GameStates states) {
        this.states = states;
    }

    public boolean isStates(GameStates arg) {
        return states == arg;
    }

    public void startCountdown() {
        startGameRunnable = new StartGameRunnable(60);
        startGameRunnable.runTaskTimerAsynchronously(Main.instance, 0L, 20L);
        Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game will start in 60 seconds.");
    }

    public void forceStartCountdown() {
        startGameRunnable = new StartGameRunnable(10);
        startGameRunnable.runTaskTimerAsynchronously(Main.instance, 0L, 20L);
        Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game start has been forced. It will start in 10 seconds.");
    }

    public void startGame() {
        setStates(GameStates.ONGAME);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Main.instance.teamManager.isInTeam(p)) {
                Main.instance.teamManager.setRandomTeam(p);
            }
        }

        Main.instance.teamManager.redTeam.setupPlayers();
        Main.instance.teamManager.blueTeam.setupPlayers();

        if(GameConfig.timelimit_enable){
            Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l> §eThe game end in " + GameConfig.timelimit_time + " minutes.");
        }

        timerRunnable.runTaskTimer(Main.instance, 0L, 20L);
        checkPointRunnable.runTaskTimer(Main.instance, 0L, 10L);
    }

    public void stopCountdown(boolean isForced) {
        if (startGameRunnable == null) {
            throw new NullPointerException();
        }

        if (isForced) {
            Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l> §cThe countdown has been cancelled.");
        }

        startGameRunnable.cancel();
        startGameRunnable = null;
    }

    public void timeOut(){
        stopGame();
        if(TeamManager.redTeam.getScore() > TeamManager.blueTeam.getScore()){
            TeamManager.redTeam.win();
        } else if(TeamManager.redTeam.getScore() < TeamManager.blueTeam.getScore()){
            TeamManager.blueTeam.win();
        } else {
            Bukkit.broadcastMessage("\n§b§lThe TOwOwer §4§l>§1§l> §2§lTie game§r. The both teams have an equivalent number of points.\n\n");
            Main.instance.scoreboardManager.setWinner("§2§lNobody");
        }

    }

    public boolean isStarting() {
        return startGameRunnable != null;
    }

    public void stopGame() {
        Main.instance.gameManager.setStates(GameStates.FINISH);
        for (Player p: Bukkit.getOnlinePlayers()) {
            Main.instance.scoreboardManager.setScoreboardTemplate(p, GameStates.FINISH);
            p.setGameMode(GameMode.SPECTATOR);
        }

        Bukkit.broadcastMessage("§e");

        timerRunnable.cancel();
        checkPointRunnable.cancel();
    }
}
