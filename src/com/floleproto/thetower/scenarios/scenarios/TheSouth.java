package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.scenarios.Scenario;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TheSouth extends Scenario {
    public TheSouth() {
        super("The South", "When you facing at the South, you are stronger.", Material.COMPASS, null);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent ev){
        double rotation = (ev.getPlayer().getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }

        if (225 <= rotation && rotation < 315) {
            ev.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true));
        }
    }
}
