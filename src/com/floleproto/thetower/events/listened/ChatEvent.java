package com.floleproto.thetower.events.listened;

import com.floleproto.thetower.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    private Main main;
    public ChatEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onAsyncChatEvent(AsyncPlayerChatEvent ev){

        Player p = ev.getPlayer();

        if(main.teamManager.isInTeam(p)){
            if(ev.getMessage().startsWith("!")){
                ev.setFormat( main.teamManager.getTeam(p).getColor() + p.getDisplayName() + " §r> " + ev.getMessage().substring(1));
            } else {
                ev.setCancelled(true);
                main.teamManager.getTeam(p).getPlayers().forEach(player -> player.sendMessage( "§e§lTEAM " + main.teamManager.getTeam(p).getColor() + p.getDisplayName() + " §r> §e" + ev.getMessage()));
            }
        } else {
            ev.setFormat(p.getDisplayName() + " §r> " + ev.getMessage());
        }
    }
}
