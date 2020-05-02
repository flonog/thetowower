package com.floleproto.thetower.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Area {

    private Location pos1;
    private Location pos2;

    public Area(Location pos1, Location pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public Location getPos1() {
        return pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public boolean isInArea(Location loc) {
        Location minLocation;
        Location maxLocation;

        minLocation = new Location(pos1.getWorld(), min(pos1.getX(), pos2.getX()), min(pos1.getY(), pos2.getY()), min(pos1.getZ(), pos2.getZ()));
        maxLocation = new Location(pos1.getWorld(), max(pos1.getX(), pos2.getX()), max(pos1.getY(), pos2.getY()), max(pos1.getZ(), pos2.getZ()));

        return (minLocation.getX() <= loc.getX()
                && minLocation.getY() <= loc.getY()
                && minLocation.getZ() <= loc.getZ()
                && maxLocation.getX() >= loc.getX()
                && maxLocation.getY() >= loc.getY()
                && maxLocation.getZ() >= loc.getZ());
    }

    public Player[] getPlayersInside() {
        List<Player> playerList = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (isInArea(p.getLocation())) {
                playerList.add(p);
            }
        }

        return playerList.toArray(new Player[0]);
    }
}
