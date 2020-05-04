package com.floleproto.thetower.game.runnables;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameConfig;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

public class TimerRunnable extends BukkitRunnable {
    private int minutes = 0;
    private int seconds = 0;

    @Override
    public void run() {
        seconds++;

        if (seconds >= 60) {
            seconds = 0;
            minutes++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("00");

        if (GameConfig.timelimit_enable) {
            if (seconds == 0) {
                if (GameConfig.timelimit_time - minutes == 5) {
                    Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game end in 5 minutes.");
                } else if (GameConfig.timelimit_time - minutes == 1) {
                    Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game end in 1 minute.");
                }
            }

            if (GameConfig.timelimit_time - minutes == 1) {
                if (60 - seconds == 30) {
                    Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game end in 30 seconds.");
                } else if (60 - seconds == 10) {
                    Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game end in 10 seconds.");
                } else if (60 - seconds <= 5) {
                    Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§e The game end in " + (60 - seconds) + " seconds.");
                }
            }
        }

        if (GameConfig.timelimit_time == minutes) {
            Main.instance.gameManager.timeOut();
        }

        Main.instance.scoreboardManager.setTimer(decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds));
    }
}
