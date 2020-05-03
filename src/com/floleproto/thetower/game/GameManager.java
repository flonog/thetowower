package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.runnables.CheckPointRunnable;
import com.floleproto.thetower.game.runnables.ItemSpawnRunnable;
import com.floleproto.thetower.game.runnables.StartGameRunnable;
import com.floleproto.thetower.game.runnables.TimerRunnable;
import com.floleproto.thetower.game.save.PositionSave;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameManager {
    private GameStates states = GameStates.WAITING;
    private StartGameRunnable startGameRunnable = null;
    private TimerRunnable timerRunnable = new TimerRunnable();
    private CheckPointRunnable checkPointRunnable = new CheckPointRunnable();
    private ItemSpawnRunnable ironSpawnItem = new ItemSpawnRunnable(new ItemStack(Material.IRON_INGOT), PositionSave.ironSpawn);
    private ItemSpawnRunnable xpSpawnItem = new ItemSpawnRunnable(new ItemStack(Material.EXP_BOTTLE), PositionSave.xpSpawn);
    private ItemSpawnRunnable lapisSpawnItem = new ItemSpawnRunnable(new ItemStack(Material.INK_SACK, 1, (byte) 4), PositionSave.lapisSpawn);

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

        Bukkit.getWorld("world").setGameRuleValue("doDaylightCycle", String.valueOf(!GameConfig.eternalday));
        Bukkit.getWorld("world").setGameRuleValue("doMobSpawning", String.valueOf(GameConfig.spawnmob));
        Bukkit.getWorld("world").setGameRuleValue("doMobLoot", "false");
        Bukkit.getWorld("world").setGameRuleValue("mobGriefing", String.valueOf(GameConfig.mobgriefing));

        Bukkit.getWorld("world").setTime(6000L);

        if(GameConfig.timelimit_enable){
            Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l> §eThe game end in " + GameConfig.timelimit_time + " minutes.");
        }

        timerRunnable.runTaskTimer(Main.instance, 0L, 20L);
        checkPointRunnable.runTaskTimer(Main.instance, 0L, 10L);
        ironSpawnItem.runTaskTimer(Main.instance, 0L, 20L * GameConfig.spawnrate_iron);
        xpSpawnItem.runTaskTimer(Main.instance, 0L, 20L * GameConfig.spawnrate_xp);
        lapisSpawnItem.runTaskTimer(Main.instance, 0L, 20L * GameConfig.spawnlapis_rate);
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
        ironSpawnItem.cancel();
        xpSpawnItem.cancel();
        lapisSpawnItem.cancel();
    }
}
