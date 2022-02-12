package com.floleproto.thetower.events.listened;

import com.floleproto.thetower.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;

public class EnchantEvent implements Listener {

    private Main main;

    public EnchantEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onEnchantEvent(EnchantItemEvent ev) {
        ev.getInventory().setItem(1, new ItemStack(Material.LAPIS_LAZULI, 3));
    }

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent ev) {
        if (ev.getInventory() instanceof EnchantingInventory) {
            ev.getInventory().setItem(1, new ItemStack(Material.LAPIS_LAZULI, 3));
        }
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent ev) {
        if (ev.getInventory() instanceof EnchantingInventory) {
            ev.getInventory().setItem(1, new ItemStack(Material.AIR));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent ev) {
        if (ev.getClickedInventory() instanceof EnchantingInventory) {
            if (ev.getCurrentItem().getType() == Material.INK_SAC) {
                ev.setCancelled(true);
            }
        }
    }
}
