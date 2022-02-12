package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.events.custom.GameStartEvent;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class InfiniteLevel extends Scenario {
    public InfiniteLevel() {
        super("Infinite Level", "Have a lot of XP. The power is in your hand.", Material.ENCHANTING_TABLE, null);
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent ev){
        Bukkit.getScheduler().runTaskLater(Main.instance, () -> ev.getEnchanter().setLevel(30), 1L);
    }

    @EventHandler
    public void onPlayerSpawn(PlayerRespawnEvent ev){

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> ev.getPlayer().setLevel(30), 1L);
    }

    @EventHandler
    public void onGameStart(GameStartEvent ev){
        Bukkit.getScheduler().runTaskLater(Main.instance, () -> Bukkit.getOnlinePlayers().forEach(p -> p.setLevel(30)), 1L);
    }
}
