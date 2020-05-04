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
        inventory.setItem(2, new ItemCreator(Material.WOOL, 1, (byte) 14, "§4§lRed").create());
        inventory.setItem(6, new ItemCreator(Material.WOOL, 1, (byte) 11, "§1§lBlue").create());
        inventory.setItem(17, new ItemCreator(Material.BARRIER, 1, (byte) 0, "§rReturn to Main Menu").create());
    }

    @EventHandler
    public void onClick(InventoryClickEvent ev) {
        if (ev.getInventory() == null)
            return;

        if (!ev.getClickedInventory().equals(inventory))
            return;

        if (ev.getCurrentItem() == null) {
            return;
        }

        ev.setCancelled(true);

        if (ev.getCurrentItem().getType() == Material.WOOL) {
            if (ev.getCurrentItem().getDurability() == 14) {
                player.getOpenInventory().close();
                new InventoryViewerMenu(player, TeamManager.redTeam).show();
            } else if (ev.getCurrentItem().getDurability() == 11) {
                player.getOpenInventory().close();
                new InventoryViewerMenu(player, TeamManager.blueTeam).show();
            }
        } else if (ev.getCurrentItem().getType() == Material.BARRIER) {
            player.getOpenInventory().close();
            new MainMenu(player).show();
        }
        ev.setCancelled(true);
    }
}
