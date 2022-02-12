package com.floleproto.thetower.gui.configmenu.inventory;

import com.floleproto.thetower.commands.SaveInvCommand;
import com.floleproto.thetower.game.Team;
import com.floleproto.thetower.game.save.InventorySave;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryViewerMenu extends GuiManager {

    private final Team team;

    public InventoryViewerMenu(Player player, Team team) {
        super(player, 54, team.getName());

        this.team = team;

        ItemStack[][] items = InventorySave.loadInventory(team.getTag());
        ItemStack[] armor = items[0];
        ItemStack[] content = items[1];

        if(content.length != 0){
            for (int i = 0; i < content.length; i++) {
                inventory.setItem(i, content[i]);
            }
        }

        if(armor.length != 0){
            inventory.setItem(39, armor[0] == null ? new ItemStack(Material.AIR) : armor[0]);
            inventory.setItem(38, armor[1] == null ? new ItemStack(Material.AIR) : armor[1]);
            inventory.setItem(37, armor[2] == null ? new ItemStack(Material.AIR) : armor[2]);
            inventory.setItem(36, armor[3] == null ? new ItemStack(Material.AIR) : armor[3]);
        }

        inventory.setItem(53, new ItemCreator(Material.GREEN_DYE, 1, "§aEdit").create());
        inventory.setItem(52, new ItemCreator(Material.BARRIER, 1, "§rMain menu").create());
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
            new InventoryMenu(player).show();
        } else if (ev.getCurrentItem().getType().equals(Material.GREEN_DYE)) {
            SaveInvCommand.openEdit(player, team);
            player.getOpenInventory().close();
        }
    }
}
