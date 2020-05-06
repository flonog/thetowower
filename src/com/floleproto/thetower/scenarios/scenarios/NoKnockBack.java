package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class NoKnockBack extends Scenario {
    public NoKnockBack() {
        super("No Knockback", "Disable the knockback", Material.ANVIL, null);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent ev){
        if(ev.getEntity() instanceof Player){
            Bukkit.getScheduler().runTaskLater(Main.instance, () -> ev.getEntity().setVelocity(new Vector(0,0,0)), 1l);
        }
    }
}
