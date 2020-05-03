package com.floleproto.thetower;

import com.floleproto.thetower.commands.CancelStartCommand;
import com.floleproto.thetower.commands.HostCommand;
import com.floleproto.thetower.commands.SaveInvCommand;
import com.floleproto.thetower.commands.StartCommand;
import com.floleproto.thetower.events.*;
import com.floleproto.thetower.game.*;
import com.floleproto.thetower.game.save.InventorySave;
import com.floleproto.thetower.game.save.PositionSave;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public GameManager gameManager;
    public TeamManager teamManager;
    public Statistics statistics;
    public ScoreboardManager scoreboardManager;

    @Override
    public void onDisable() {

        System.out.println("[The TOwOwer] Plugin disabled.");
    }

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eDefault Config saved.");
        PositionSave.LoadFile();
        PositionSave.LoadPositions();
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §ePositions loaded.");
        InventorySave.LoadFile();
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eInventories loaded.");
        try{
            GameConfig.loadConfig();
            Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r]§e Configurations loaded.");
        } catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r]§c Error ! Save default configuration to try to fix it.");
            GameConfig.saveConfig();
        }


        statistics = new Statistics();
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eStatistics loaded.");
        gameManager = new GameManager();
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eGameManager loaded.");
        teamManager = new TeamManager();
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eTeamManager loaded.");
        scoreboardManager = new ScoreboardManager();
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eScoreboardManager loaded. (ScoreboardSign by zyuiop)");

        getCommand("host").setExecutor(new HostCommand(this));
        getCommand("start").setExecutor(new StartCommand(this));
        getCommand("cancelstart").setExecutor(new CancelStartCommand(this));
        getCommand("save").setExecutor(new SaveInvCommand(this));
        getCommand("rules").setExecutor(new RulesCommand(this));
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eCommands registered.");

        getServer().getPluginManager().registerEvents(new JoinAndLeftEvent(this), this);
        getServer().getPluginManager().registerEvents(new FoodEvent(this), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(this), this);
        getServer().getPluginManager().registerEvents(new BlockEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDropEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDieEvent(this), this);
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eEvents registered.");

        Bukkit.getWorld("world").setGameRuleValue("doDaylightCycle", "false");
        Bukkit.getWorld("world").setGameRuleValue("doMobSpawning", "false");
        Bukkit.getWorld("world").setGameRuleValue("doMobLoot", "false");
        Bukkit.getWorld("world").setGameRuleValue("mobGriefing", "false");
        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §eGamerule world registered.");

        Bukkit.getConsoleSender().sendMessage("[§bThe TOwOwer§r] §aPlugin loaded successfully.");
    }

}
