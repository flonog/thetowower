package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.scenarios.Scenario;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoFallDamage extends Scenario {
    public NoFallDamage() {
        super("No Fall Damage", "Deactivate the fall damage.", Material.FEATHER, null);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent ev){
        if(ev.getCause() == EntityDamageEvent.DamageCause.FALL){
            ev.setCancelled(true);
        }
    }
}
