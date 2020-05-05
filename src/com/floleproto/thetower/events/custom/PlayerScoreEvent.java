package com.floleproto.thetower.events.custom;

import com.floleproto.thetower.game.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerScoreEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private Team team;
    private boolean isCancelled = false;

    public PlayerScoreEvent(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
