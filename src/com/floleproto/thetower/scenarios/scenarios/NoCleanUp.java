package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.scenarios.Scenario;
import com.floleproto.thetower.scenarios.scenarios.configmenu.NoCleanUpConfigMenu;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class NoCleanUp extends Scenario {

    public static int healthAmount = 4;

    public NoCleanUp() {
        super("No CleanUp", "Add health when someone kill someone.", Material.GOLDEN_APPLE, NoCleanUpConfigMenu.class);
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent ev){
        if(ev.getEntity().getKiller() != null){
            Player killer = ev.getEntity().getKiller();
            killer.setHealth( Math.max(0, Math.min(killer.getHealth() + healthAmount, killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())));
        }
    }
}
