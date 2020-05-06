package com.floleproto.thetower.events.listened;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.game.GameStates;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DamageEvent implements Listener {
    private final Main main;
    public static Map<UUID, UUID> lastHit = new HashMap<>();

    public DamageEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent ev) {
        if (main.gameManager.isStates(GameStates.WAITING)) {
            if (ev.getEntityType().equals(EntityType.PLAYER)) {
                ev.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent ev) {
        if (main.gameManager.isStates(GameStates.ONGAME)) {
            if (ev.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                if (ev.getEntity() instanceof Player && ev.getDamager() instanceof Player) {
                    Player p = (Player) ev.getEntity();
                    Player damager = (Player) ev.getDamager();
                    lastHit.put(p.getUniqueId(), damager.getUniqueId());
                    if (!GameConfig.friendlyfire) {
                        if (Main.instance.teamManager.getTeam(p) == Main.instance.teamManager.getTeam(damager))
                            ev.setCancelled(true);
                    }
                }
            }
        }
    }
}
