package com.floleproto.thetower.gui.configmenu.itemspawn;

import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.gui.configmenu.MainMenu;
import com.floleproto.thetower.gui.configmenu.NumberConfigMenu;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LapisRateConfig extends NumberConfigMenu {
    public LapisRateConfig(Player player) {
        super(player, "§rLapis Config");
        refreshInventory();
    }

    public void refreshInventory(){
        inventory.setItem(4, new ItemCreator(Material.INK_SACK, 1, (byte) 4, "§b" + GameConfig.spawnlapis_rate).create());
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
            new ItemSpawnMenu(player).show();
        }

        if(!ev.getCurrentItem().getType().equals(Material.WOOL)){
            return;
        }
        String itemName = ev.getCurrentItem().getItemMeta().getDisplayName();
        int number = Integer.parseInt(itemName.substring(2));

        GameConfig.spawnlapis_rate += number;
        refreshInventory();
    }
}
