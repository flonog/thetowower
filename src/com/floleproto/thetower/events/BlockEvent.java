package com.floleproto.thetower.events;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameStates;
import com.floleproto.thetower.game.TeamManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvent implements Listener {
    private final Main main;

    public BlockEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent ev) {
        if (ev.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;
        if (main.gameManager.isStates(GameStates.WAITING)) {
            ev.setCancelled(true);
        } else if (main.gameManager.isStates(GameStates.ONGAME)) {
            if (TeamManager.redTeam.getPoolProtected().isInArea(ev.getBlock().getLocation())) {
                ev.setCancelled(true);
            } else if (TeamManager.blueTeam.getPoolProtected().isInArea(ev.getBlock().getLocation())) {
                ev.setCancelled(true);
            } else if (TeamManager.redTeam.getSpawnProtected().isInArea(ev.getBlock().getLocation())) {
                ev.setCancelled(true);
            } else if (TeamManager.blueTeam.getSpawnProtected().isInArea(ev.getBlock().getLocation())) {
                ev.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent ev) {
        if (ev.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;
        if (main.gameManager.isStates(GameStates.WAITING)) {
            ev.setCancelled(true);
        } else if (main.gameManager.isStates(GameStates.ONGAME)) {
            if (TeamManager.redTeam.getPoolProtected().isInArea(ev.getBlock().getLocation())) {
                ev.setCancelled(true);
            } else if (TeamManager.blueTeam.getPoolProtected().isInArea(ev.getBlock().getLocation())) {
                ev.setCancelled(true);
            } else if (TeamManager.redTeam.getSpawnProtected().isInArea(ev.getBlock().getLocation())) {
                ev.setCancelled(true);
            } else if (TeamManager.blueTeam.getSpawnProtected().isInArea(ev.getBlock().getLocation())) {
                ev.setCancelled(true);
            }
        }

    }
}
