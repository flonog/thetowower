package com.floleproto.thetower.scenarios;

import com.floleproto.thetower.gui.GuiManager;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Scenario implements Listener {

    public String name;
    public String description;
    public Material icon;

    public Class<? extends GuiManager> configGui;

    public Scenario(String name, String description, Material icon, Class<? extends GuiManager> configGui) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.configGui = configGui;
    }

    public void onEnable(){
    }
}