package com.floleproto.thetower.events;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameStates;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

        Player killer = ev.getEntity().getKiller();
        Player victim = ev.getEntity();

        if (killer != null) {
            main.statistics.addKill(killer, 1);
            main.scoreboardManager.refreshKillsDeaths(killer);
        }


        //victim.spigot().respawn();
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev) {
        Player p = ev.getPlayer();
        main.scoreboardManager.refreshKillsDeaths(p);
        main.teamManager.getTeam(p).setTeamInventory(p);
        main.statistics.addDeath(p, 1);
        main.scoreboardManager.refreshKillsDeaths(p);
        ev.setRespawnLocation(main.teamManager.getTeam(p).getSpawn());
    }
}
