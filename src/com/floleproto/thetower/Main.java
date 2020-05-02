package com.floleproto.thetower;

import com.floleproto.thetower.commands.CancelStartCommand;
import com.floleproto.thetower.commands.HostCommand;
import com.floleproto.thetower.commands.SaveInvCommand;
import com.floleproto.thetower.commands.StartCommand;
import com.floleproto.thetower.events.*;
import com.floleproto.thetower.game.GameManager;
import com.floleproto.thetower.game.ScoreboardManager;
import com.floleproto.thetower.game.Statistics;
import com.floleproto.thetower.game.TeamManager;
import com.floleproto.thetower.game.save.InventorySave;
import com.floleproto.thetower.game.save.PositionSave;
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
        System.out.println("[The TOwOwer] Default Config saved.");
        PositionSave.LoadFile();
        PositionSave.LoadPositions();
        System.out.println("[The TOwOwer] Positions loaded.");
        InventorySave.LoadFile();
        System.out.println("[The TOwOwer] Inventories loaded.");

        statistics = new Statistics();
        System.out.println("[The TOwOwer] Statistics loaded.");
        gameManager = new GameManager();
        System.out.println("[The TOwOwer] GameManager loaded.");
        teamManager = new TeamManager();
        System.out.println("[The TOwOwer] TeamManager loaded.");
        scoreboardManager = new ScoreboardManager();
        System.out.println("[The TOwOwer] ScoreboardManager loaded. (ScoreboardSign by zyuiop)");

        getCommand("host").setExecutor(new HostCommand(this));
        getCommand("start").setExecutor(new StartCommand(this));
        getCommand("cancelstart").setExecutor(new CancelStartCommand(this));
        getCommand("save").setExecutor(new SaveInvCommand(this));
        System.out.println("[The TOwOwer] Commands registered.");

        getServer().getPluginManager().registerEvents(new JoinAndLeftEvent(this), this);
        getServer().getPluginManager().registerEvents(new FoodEvent(this), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(this), this);
        getServer().getPluginManager().registerEvents(new BlockEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDropEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDieEvent(this), this);
        System.out.println("[The TOwOwer] Events registered.");

        System.out.println("[The TOwOwer] Plugin loaded successfully.");
    }

}
