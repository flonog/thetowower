package com.floleproto.thetower.events;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameStates;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropEvent implements Listener {

    private Main main;

    public PlayerDropEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent ev) {
        if (ev.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;
        if (main.gameManager.isStates(GameStates.WAITING))
            ev.setCancelled(true);
    }
}
