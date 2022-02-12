package com.floleproto.thetower.gui.configmenu.point;

import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.gui.configmenu.MainMenu;
import com.floleproto.thetower.gui.configmenu.NumberConfigMenu;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class PointConfigMenu extends NumberConfigMenu {
    public PointConfigMenu(Player player) {
        super(player, "§rTime Limit Configuration");
        refreshInventory();
    }

    public void refreshInventory() {
        inventory.setItem(4, new ItemCreator(Material.NETHER_STAR, 1, "§b" + GameConfig.scoretowin).create());
    }

    @Override
    public void onClick(InventoryClickEvent ev) {

        if (!ev.getInventory().equals(inventory)) {
            return;
        }

        if (ev.getCurrentItem() == null) {
            return;
        }

        ev.setCancelled(true);

        if (!player.hasPermission("thetowower.config") || !player.hasPermission("thetowower.*") || !player.isOp()) {
            player.getOpenInventory().close();
            return;
        }

        if (ev.getCurrentItem().getType().equals(Material.BARRIER)) {
            player.getOpenInventory().close();
            new MainMenu(player).show();
        }

        if (!(ev.getCurrentItem().getType().equals(Material.GREEN_WOOL) || ev.getCurrentItem().getType().equals(Material.RED_WOOL))) {
            return;
        }
        String itemName = Objects.requireNonNull(ev.getCurrentItem().getItemMeta()).getDisplayName();
        int number = Integer.parseInt(itemName.substring(2));

        GameConfig.scoretowin += number;
        refreshInventory();
    }
}
