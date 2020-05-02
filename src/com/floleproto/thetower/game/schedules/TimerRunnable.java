package com.floleproto.thetower.game.schedules;

import com.floleproto.thetower.Main;
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

        Main.instance.scoreboardManager.setTimer(decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds));
    }
}
