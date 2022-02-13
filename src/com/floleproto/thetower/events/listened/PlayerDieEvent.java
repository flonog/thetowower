package com.floleproto.thetower.events.listened;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.game.GameStates;
import com.floleproto.thetower.game.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDieEvent implements Listener {

    private final Main main;

    public PlayerDieEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent ev) {

        if (!main.gameManager.isStates(GameStates.ONGAME))
            return;

        if(TeamManager.spectatorTeam.isInTeam(ev.getEntity()))
            return;

        Player killer = ev.getEntity().getKiller();
        Player victim = ev.getEntity();

        if(killer == null && DamageEvent.lastHit.containsKey(victim.getUniqueId())){
            killer = Bukkit.getPlayer(DamageEvent.lastHit.get(victim.getUniqueId()));
        }

        DamageEvent.lastHit.remove(victim.getUniqueId());
        DamageEvent.lastHit.values().removeIf(x -> x == victim.getUniqueId());

        if (killer != null) {
            main.statistics.addKill(killer, 1);
            main.scoreboardManager.refreshKillsDeaths(killer);

            ev.setDeathMessage(killer.getPlayerListName() + " §ckilled " + victim.getPlayerListName() + ".");
            return;
        }

        ev.setDeathMessage(victim.getPlayerListName() + " §cdied.");


        //victim.spigot().respawn();
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev) {
        if (!main.gameManager.isStates(GameStates.ONGAME))
            return;

        Player p = ev.getPlayer();
        main.scoreboardManager.refreshKillsDeaths(p);
        main.teamManager.getTeam(p).setTeamInventory(p);
        main.statistics.addDeath(p, 1);
        main.scoreboardManager.refreshKillsDeaths(p);
        if(GameConfig.noCooldown)
            p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(999999);
        ev.setRespawnLocation(main.teamManager.getTeam(p).getSpawn());
    }
}
