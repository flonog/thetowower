package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.scenarios.Scenario;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class UHC extends Scenario {
    public UHC() {
        super("Ultra Hardcore", "No natural life regeneration", Material.SKELETON_SKULL, null);
    }

    @EventHandler
    public void onRegenLife(EntityRegainHealthEvent ev){
        ev.setCancelled(true);
    }
}
