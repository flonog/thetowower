package com.floleproto.thetower.gui.configmenu.time;

import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.gui.configmenu.MainMenu;
import com.floleproto.thetower.gui.configmenu.NumberConfigMenu;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TimeConfig extends NumberConfigMenu {
    public TimeConfig(Player player) {
        super(player, "§rTime Limit Configuration");
        refreshInventory();
    }

    public void refreshInventory(){
        inventory.setItem(4, new ItemCreator(Material.WATCH, 1, (byte) 0, "§b" + GameConfig.timelimit_time).create());
    }

    @EventHandler
    public void onClick(InventoryClickEvent ev){
        if (ev.getInventory() == null)
            return;

        if (!ev.getInventory().equals(inventory)){
            return;
        }

        if (ev.getCurrentItem() == null){
            return;
        }

        ev.setCancelled(true);

        if (!player.hasPermission("thetowower.config") || !player.hasPermission("thetowower.*") || !player.isOp()){
            player.getOpenInventory().close();
            return;
        }

        if(ev.getCurrentItem().getType().equals(Material.BARRIER)){
            player.getOpenInventory().close();
            new MainMenu(player).show();
        }

        if(!ev.getCurrentItem().getType().equals(Material.WOOL)){
            return;
        }
        String itemName = ev.getCurrentItem().getItemMeta().getDisplayName();
        int number = Integer.parseInt(itemName.substring(2));

        GameConfig.timelimit_time += number;
        refreshInventory();
    }
}
