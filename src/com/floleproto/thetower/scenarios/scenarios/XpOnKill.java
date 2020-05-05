package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.scenarios.Scenario;
import com.floleproto.thetower.scenarios.scenarios.configmenu.XpOnKillConfigMenu;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class XpOnKill extends Scenario {
    public static int xpEarned = 20;
    public XpOnKill() {
        super("XP On Kill", "Earn XP when you kill someone.", Material.EXP_BOTTLE, XpOnKillConfigMenu.class);
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent ev){
        if(ev.getEntity().getKiller() != null){
            ev.getEntity().getKiller().giveExp(xpEarned);
        }
    }
}

