package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.scenarios.Scenario;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoFood extends Scenario {
    public NoFood() {
        super("No Food", "Don't need to use food.", Material.CAKE, null);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent ev){
        ev.setCancelled(true);
    }
}
