package com.floleproto.thetower.gui.configmenu.inventory;

import com.floleproto.thetower.commands.SaveInvCommand;
import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.game.Team;
import com.floleproto.thetower.game.save.InventorySave;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.gui.configmenu.itemspawn.ItemSpawnMenu;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryViewerMenu extends GuiManager {

    private Team team;

    public InventoryViewerMenu(Player player, Team team) {
        super(player, 54, team.getName());

        this.team = team;

        ItemStack[][] items = InventorySave.loadInventory(team.getTag());
        ItemStack[] armor = items[0];
        ItemStack[] content = items[1];

        for (int i = 0; i < content.length; i++) {
            inventory.setItem(i, content[i]);
        }
        inventory.setItem(39, armor[0]);
        inventory.setItem(38, armor[1]);
        inventory.setItem(37, armor[2]);
        inventory.setItem(36, armor[3]);

        inventory.setItem(53, new ItemCreator(Material.INK_SACK, 1, (byte) 10, "§aEdit").create());
        inventory.setItem(52, new ItemCreator(Material.BARRIER, 1, (byte) 10, "§rMain menu").create());
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
            new InventoryMenu(player).show();
        } else if(ev.getCurrentItem().getType().equals(Material.INK_SACK)){
            if(ev.getCurrentItem().getItemMeta().getDisplayName() == "§aEdit"){
                SaveInvCommand.openEdit(player, team);
                player.getOpenInventory().close();
            }
        }
    }
}
