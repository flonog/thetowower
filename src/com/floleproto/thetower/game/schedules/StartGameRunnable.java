package com.floleproto.thetower.game.schedules;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.utils.Title;
import com.floleproto.thetower.utils.XpBarManager;
import org.bukkit.scheduler.BukkitRunnable;

public class StartGameRunnable extends BukkitRunnable {

    private int timer;
    private int maxTimer;

    public StartGameRunnable(int timer) {
        this.timer = timer;
        this.maxTimer = timer;
    }

    @Override
    public void run() {
        XpBarManager.broadcastLevel(timer);
        XpBarManager.broadcastSetBar(timer, maxTimer);



        if (timer <= 10) {
            Title title = new Title("", "");
            if(timer <= 0){
                title.setSubtitle("§eMark " + GameConfig.scoretowin + " points to win !");
                title.setTitle("§b§lThe TOwOwer");

            } else if (timer == 1) {
                title.setTitle("§4" + timer);
            } else if (timer <= 3) {
                title.setTitle("§c" + timer);
            } else if (timer <= 5) {
                title.setTitle("§e" + timer);
            } else {
                title.setTitle("§a" + timer);
            }

            title.broadcast();
        }

        if (timer <= 0) {

            Main.instance.gameManager.startGame();
            Main.instance.gameManager.stopCountdown(false);
            return;
        }

        timer--;
    }
}
