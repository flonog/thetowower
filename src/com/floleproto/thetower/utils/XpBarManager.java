package com.floleproto.thetower.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class XpBarManager {

    public static void broadcastLevel(int lvl) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setLevel(lvl);
        }
    }

    public static void broadcastSetBar(float value, float maxValue) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            float maxXp = p.getExpToLevel();
            p.setExp(value / maxValue);
        }
    }

    public static void playerSetBar(Player p, float value, float maxValue) {
        p.setExp(value / maxValue);
    }

}
