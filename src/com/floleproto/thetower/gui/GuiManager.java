package com.floleproto.thetower.gui;

import com.floleproto.thetower.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class GuiManager implements Listener {
    protected Player player;
    protected int size;
    protected InventoryType type;
    protected String name;
    protected Inventory inventory;

    public GuiManager(Player player, int size, String name) {
        this.player = player;
        this.size = size;
        this.name = name;
        inventory = Bukkit.createInventory(null, size, name);
    }

    public GuiManager(Player player, InventoryType type, String name) {
        this.player = player;
        this.type = type;
        this.name = name;
        inventory = Bukkit.createInventory(null, type, name);
    }

    public void show() { Bukkit.getPluginManager().registerEvents(this, Main.instance); this.player.openInventory(inventory); }

    public Player getPlayer() { return this.player; }

    public void setPlayer(Player player) { this.player = player; }

    public int getSize() { return this.size; }

    public void setSize(int size) { this.size = size; }

    public InventoryType getType() { return this.type; }

    public void setType(InventoryType type) { this.type = type; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public Inventory getInventory() { return inventory; }

    public void setInventory(Inventory inventory) { this.inventory = inventory; }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent ev) {
        if(ev.getInventory() == inventory) {
            HandlerList.unregisterAll(this);
        }
    }
}