package com.floleproto.thetower.events;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameStates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodEvent implements Listener {
    private final Main main;

    public FoodEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onFoodLevelChanged(FoodLevelChangeEvent ev){
        if(main.gameManager.isStates(GameStates.WAITING) || main.gameManager.isStates(GameStates.FINISH))
            ev.setFoodLevel(20);
    }
}
