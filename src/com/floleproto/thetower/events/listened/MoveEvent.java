package com.floleproto.thetower.events.listened;

import com.floleproto.thetower.Main;
import com.google.common.base.Function;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class MoveEvent implements Listener {
    Main main;
    public MoveEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent ev){
        if(ev.getTo().getY() < 0 && ev.getPlayer().getHealth() > 0){
            ev.getPlayer().setHealth(0);
        }
    }
}
