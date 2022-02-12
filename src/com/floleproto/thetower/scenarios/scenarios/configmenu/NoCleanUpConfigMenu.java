package com.floleproto.thetower.scenarios.scenarios.configmenu;

import com.floleproto.thetower.gui.configmenu.NumberConfigMenu;
import com.floleproto.thetower.gui.configmenu.scenario.ScenarioMenu;
import com.floleproto.thetower.scenarios.scenarios.NoCleanUp;
import com.floleproto.thetower.scenarios.scenarios.XpOnKill;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NoCleanUpConfigMenu extends NumberConfigMenu {

    public NoCleanUpConfigMenu(Player player) {
        super(player, "§rHealth Amount");
        refreshInventory();
    }

    public void refreshInventory() {
        inventory.setItem(4, new ItemCreator(Material.RED_STAINED_GLASS_PANE, 1,"§b" + NoCleanUp.healthAmount).create());
    }

    @EventHandler
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
            new ScenarioMenu(player).show();
        }

        if (!(ev.getCurrentItem().getType().equals(Material.GREEN_WOOL) || ev.getCurrentItem().getType().equals(Material.RED_WOOL))) {
            return;
        }
        String itemName = ev.getCurrentItem().getItemMeta().getDisplayName();
        int number = Integer.parseInt(itemName.substring(2));

        NoCleanUp.healthAmount += number;
        refreshInventory();
    }
}
