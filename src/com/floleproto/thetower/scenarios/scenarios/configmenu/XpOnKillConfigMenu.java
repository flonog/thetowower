package com.floleproto.thetower.scenarios.scenarios.configmenu;

import com.floleproto.thetower.gui.configmenu.MainMenu;
import com.floleproto.thetower.gui.configmenu.NumberConfigMenu;
import com.floleproto.thetower.gui.configmenu.scenario.ScenarioMenu;
import com.floleproto.thetower.scenarios.scenarios.XpOnKill;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class XpOnKillConfigMenu extends NumberConfigMenu {

    public XpOnKillConfigMenu(Player player) {
        super(player, "§rXP Configuration");
        refreshInventory();
    }

    public void refreshInventory() {
        inventory.setItem(4, new ItemCreator(Material.EXP_BOTTLE, 1, (byte) 0, "§b" + XpOnKill.xpEarned).create());
    }

    @EventHandler
    public void onClick(InventoryClickEvent ev) {
        if (ev.getInventory() == null)
            return;

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

        if (!ev.getCurrentItem().getType().equals(Material.WOOL)) {
            return;
        }
        String itemName = ev.getCurrentItem().getItemMeta().getDisplayName();
        int number = Integer.parseInt(itemName.substring(2));

        XpOnKill.xpEarned += number;
        refreshInventory();
    }
}
