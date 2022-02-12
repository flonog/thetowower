package com.floleproto.thetower.game.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

public class ItemSpawnRunnable extends BukkitRunnable {

    private final ItemStack item;
    private final Location position;
    private Item previousItem;

    public ItemSpawnRunnable(ItemStack item, Location position) {
        this.item = item;
        this.position = position;
    }

    @Override
    public void run() {
        if (previousItem != null && previousItem.isValid()) {
            previousItem.remove();
        }
        previousItem = Objects.requireNonNull(Bukkit.getWorld("world")).dropItem(position, item);
        previousItem.setVelocity(new Vector(0, 0, 0));
    }
}
