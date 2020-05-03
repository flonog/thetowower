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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TeamGui extends GuiManager {

    public TeamGui(Player p) {
        super(p, InventoryType.HOPPER, "§f§lTeam selection");
        inventory.setItem(0, new ItemCreator(Material.WOOL, 1, (byte) 14, "§4§lRed").create());
        inventory.setItem(2, new ItemCreator(Material.BARRIER, 1, (byte) 0, "§f§lRandom").create());
        inventory.setItem(4, new ItemCreator(Material.WOOL, 1, (byte) 11, "§1§lBlue").create());
    }

    @EventHandler
    public void onClick(InventoryClickEvent ev) {
        if (ev.getInventory() == null)
            return;

        if (ev.getClickedInventory() != inventory)
            return;

        if (ev.getCurrentItem() == null){
            return;
        }

        if (ev.getCurrentItem().getType() == Material.WOOL) {
            TeamManager teamManager = Main.instance.teamManager;
            Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
            enchants.put(Enchantment.DURABILITY, 1);
            if (ev.getCurrentItem().getDurability() == 14) {
                Main.instance.teamManager.addPlayer(player, teamManager.redTeam);
                player.getOpenInventory().close();
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 2);
                player.getInventory().setItem(4, new ItemCreator(Material.WOOL, 1, (byte) 14, "§b§lTeam (§4§lRed§b§l)", null, enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());

            } else if (ev.getCurrentItem().getDurability() == 11) {
                Main.instance.teamManager.addPlayer(player, teamManager.blueTeam);
                player.getOpenInventory().close();
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 2);
                player.getInventory().setItem(4, new ItemCreator(Material.WOOL, 1, (byte) 11, "§b§lTeam (§1§lBlue§b§l)", null, enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());

            }
        } else if (ev.getCurrentItem().getType() == Material.BARRIER) {
            Main.instance.teamManager.removePlayer(player);
            Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
            enchants.put(Enchantment.DURABILITY, 1);
            player.getOpenInventory().close();
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 2);
            player.getInventory().setItem(4, new ItemCreator(Material.WOOL, 1, (byte) 0, "§b§lTeam", null, enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        }
        ev.setCancelled(true);
    }
}
