package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.events.custom.GameEndEvent;
import com.floleproto.thetower.events.custom.GameStartEvent;
import com.floleproto.thetower.events.listened.EnchantEvent;
import com.floleproto.thetower.game.runnables.CheckPointRunnable;
import com.floleproto.thetower.game.runnables.ItemSpawnRunnable;
import com.floleproto.thetower.game.runnables.StartGameRunnable;
import com.floleproto.thetower.game.runnables.TimerRunnable;
import com.floleproto.thetower.game.save.PositionSave;
import com.floleproto.thetower.utils.XpBarManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class GameManager {
    private GameStates states = GameStates.WAITING;
    private StartGameRunnable startGameRunnable = null;
    private final TimerRunnable timerRunnable = new TimerRunnable();
    private final CheckPointRunnable checkPointRunnable = new CheckPointRunnable();
    private final ItemSpawnRunnable ironSpawnItem = new ItemSpawnRunnable(new ItemStack(Material.IRON_INGOT), PositionSave.ironSpawn);
    private final ItemSpawnRunnable xpSpawnItem = new ItemSpawnRunnable(new ItemStack(Material.EXPERIENCE_BOTTLE), PositionSave.xpSpawn);
    private final ItemSpawnRunnable lapisSpawnItem = new ItemSpawnRunnable(new ItemStack(Material.INK_SAC, 1), PositionSave.lapisSpawn);

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
        startGameRunnable.runTaskTimer(Main.instance, 0L, 20L);
        Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game will start in 60 seconds.");
    }

    public void forceStartCountdown() {
        startGameRunnable = new StartGameRunnable(10);
        startGameRunnable.runTaskTimer(Main.instance, 0L, 20L);
        Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game start has been forced. It will start in 10 seconds.");
    }

    public void startGame() {
        setStates(GameStates.ONGAME);

        Main.instance.scenarioManager.enableListener();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Main.instance.teamManager.isInTeam(p)) {
                p.sendMessage("§b§lThe TOwOwer §4§l>§1§l> §rBecause of you didn't chose any team, your team has been set randomly.");
                Main.instance.teamManager.setRandomTeam(p);
            }
        }

        TeamManager.redTeam.setupPlayers();
        TeamManager.blueTeam.setupPlayers();

        if (!GameConfig.spawnlapis) {
            Bukkit.getPluginManager().registerEvents(new EnchantEvent(Main.instance), Main.instance);
        }

        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, !GameConfig.eternalday);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_MOB_SPAWNING, GameConfig.spawnmob);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_MOB_LOOT, false);
        Bukkit.getWorld("world").setGameRule(GameRule.MOB_GRIEFING, GameConfig.mobgriefing);

        Bukkit.getWorld("world").setTime(6000L);

        if (GameConfig.timelimit_enable) {
            Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l> §eThe game end in " + GameConfig.timelimit_time + " minutes.");
        }

        timerRunnable.runTaskTimer(Main.instance, 0L, 20L);
        checkPointRunnable.runTaskTimer(Main.instance, 0L, 10L);
        if (GameConfig.spawnrate_iron_enable)
            ironSpawnItem.runTaskTimer(Main.instance, 0L, 20L * GameConfig.spawnrate_iron);
        if (GameConfig.spawnrate_xp_enable)
            xpSpawnItem.runTaskTimer(Main.instance, 0L, 20L * GameConfig.spawnrate_xp);
        if (GameConfig.spawnlapis)
            lapisSpawnItem.runTaskTimer(Main.instance, 0L, 20L * GameConfig.spawnlapis_rate);

        Bukkit.getPluginManager().callEvent(new GameStartEvent());
    }

    public void stopCountdown(boolean isForced) {
        if (startGameRunnable == null) {
            throw new NullPointerException();
        }

        if (isForced) {
            Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l> §cThe countdown has been cancelled.");
        }

        XpBarManager.broadcastLevel(0);
        XpBarManager.broadcastSetBar(0, 1);

        startGameRunnable.cancel();
        startGameRunnable = null;
    }

    public void timeOut() {
        stopGame();
        if (TeamManager.redTeam.getScore() > TeamManager.blueTeam.getScore()) {
            TeamManager.redTeam.win();
        } else if (TeamManager.redTeam.getScore() < TeamManager.blueTeam.getScore()) {
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
        for (Player p : Bukkit.getOnlinePlayers()) {
            Main.instance.scoreboardManager.setScoreboardTemplate(p, GameStates.FINISH);
            p.setGameMode(GameMode.SPECTATOR);
        }

        Bukkit.broadcastMessage("§e");

        Bukkit.getPluginManager().callEvent(new GameEndEvent());

        timerRunnable.cancel();
        checkPointRunnable.cancel();
        if (GameConfig.spawnrate_iron_enable)
            ironSpawnItem.cancel();
        if (GameConfig.spawnrate_xp_enable)
            xpSpawnItem.cancel();
        if (GameConfig.spawnlapis)
            lapisSpawnItem.cancel();
        else
            HandlerList.unregisterAll(new EnchantEvent(Main.instance));
    }
}
