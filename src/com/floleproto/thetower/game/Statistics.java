package com.floleproto.thetower.game;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Statistics {

    private HashMap<UUID, Integer> deaths = new HashMap<>();
    private HashMap<UUID, Integer> kills = new HashMap<>();

    public void addKill(Player p, int amount){
        int kill = kills.get(p.getUniqueId());
        kills.put(p.getUniqueId(), kill + amount);
    }

    public void addDeath(Player p, int amount){
        int death = deaths.get(p.getUniqueId());
        deaths.put(p.getUniqueId(), death + amount);
    }

    public int getKills(Player p){
        return kills.get(p.getUniqueId());
    }

    public int getDeaths(Player p){
        return deaths.get(p.getUniqueId());
    }

    public void registerPlayer(Player p){
        deaths.put(p.getUniqueId(), 0);
        kills.put(p.getUniqueId(), 0);
    }

}
