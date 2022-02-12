package com.floleproto.thetower.gui.waitingroom;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.TeamManager;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemFlag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamGui extends GuiManager {

    public TeamGui(Player p) {
        super(p, InventoryType.HOPPER, "§f§lTeam selection");
        inventory.setItem(0, new ItemCreator(Material.RED_WOOL, 1,"§4§lRed").create());
        inventory.setItem(2, new ItemCreator(Material.BARRIER, 1, "§f§lRandom").create());
        inventory.setItem(4, new ItemCreator(Material.BLUE_WOOL, 1, "§1§lBlue").create());
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
        Map<Enchantment, Integer> enchants = new HashMap<>();

        if (ev.getCurrentItem().getType() == Material.RED_WOOL) {
            enchants.put(Enchantment.DURABILITY, 1);
            Main.instance.teamManager.addPlayer(player, TeamManager.redTeam);
            player.getOpenInventory().close();
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
            player.getInventory().setItem(4, new ItemCreator(Material.RED_WOOL, 1, "§b§lTeam (§4§lRed§b§l)", null, enchants, List.of(ItemFlag.HIDE_ENCHANTS)).create());
        } else if (ev.getCurrentItem().getType() == Material.BLUE_WOOL) {
            enchants.put(Enchantment.DURABILITY, 1);
            Main.instance.teamManager.addPlayer(player, TeamManager.blueTeam);
            player.getOpenInventory().close();
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
            player.getInventory().setItem(4, new ItemCreator(Material.BLUE_WOOL, 1, "§b§lTeam (§1§lBlue§b§l)", null, enchants, List.of(ItemFlag.HIDE_ENCHANTS)).create());

        } else if (ev.getCurrentItem().getType() == Material.BARRIER) {
            Main.instance.teamManager.removePlayer(player);
            enchants.put(Enchantment.DURABILITY, 1);
            player.getOpenInventory().close();
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
            player.getInventory().setItem(4, new ItemCreator(Material.WHITE_WOOL, 1, "§b§lTeam", null, enchants, List.of(ItemFlag.HIDE_ENCHANTS)).create());
        }
        ev.setCancelled(true);
    }
}
