package com.floleproto.thetower.events;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameStates;
import com.floleproto.thetower.gui.configmenu.MainMenu;
import com.floleproto.thetower.gui.waitingroom.TeamGui;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    private final Main main;

    public PlayerInteract(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent ev) {
        if (!main.gameManager.isStates(GameStates.WAITING))
            return;

        Player p = ev.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE)
            return;

        if (ev.hasItem()) {
            switch (ev.getMaterial()) {
                case WOOL:
                    new TeamGui(p).show();
                    break;
                case COMMAND:
                    if(p.hasPermission("thetowower.config") || p.hasPermission("thetowower.*") || p.isOp()){
                        new MainMenu(p).show();
                    }
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerInteractGUI(InventoryClickEvent ev) {
        if (ev.getWhoClicked().getGameMode() == GameMode.CREATIVE)
            return;

        if(ev.getClickedInventory() != ev.getWhoClicked().getInventory()){
            return;
        }

        if (main.gameManager.isStates(GameStates.WAITING))
            ev.setCancelled(true);
    }
}
