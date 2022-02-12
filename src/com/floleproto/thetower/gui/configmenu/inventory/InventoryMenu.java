package com.floleproto.thetower.gui.configmenu.inventory;

import com.floleproto.thetower.game.TeamManager;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.gui.configmenu.MainMenu;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryMenu extends GuiManager {
    public InventoryMenu(Player player) {
        super(player, 18, "§rInventory Config");
        inventory.setItem(2, new ItemCreator(Material.RED_WOOL, 1, "§4§lRed").create());
        inventory.setItem(6, new ItemCreator(Material.BLUE_WOOL, 1, "§1§lBlue").create());
        inventory.setItem(17, new ItemCreator(Material.BARRIER, 1, "§rReturn to Main Menu").create());
    }

    @Override
    public void onClick(InventoryClickEvent ev) {
        if (ev.getInventory() == null)
            return;

        if (!ev.getClickedInventory().equals(inventory))
            return;

        if (ev.getCurrentItem() == null) {
            return;
        }

        ev.setCancelled(true);

        if (ev.getCurrentItem().getType() == Material.RED_WOOL) {
            player.getOpenInventory().close();
            new InventoryViewerMenu(player, TeamManager.redTeam).show();
        } else if (ev.getCurrentItem().getType() == Material.BLUE_WOOL) {
            player.getOpenInventory().close();
            new InventoryViewerMenu(player, TeamManager.blueTeam).show();
        } else if (ev.getCurrentItem().getType() == Material.BARRIER) {
            player.getOpenInventory().close();
            new MainMenu(player).show();
        }
        ev.setCancelled(true);
    }
}
