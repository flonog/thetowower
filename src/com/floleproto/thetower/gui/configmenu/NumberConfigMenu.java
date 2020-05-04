package com.floleproto.thetower.gui.configmenu;

import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.utils.ItemCreator;
import org.apache.commons.lang.mutable.MutableInt;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class NumberConfigMenu extends GuiManager {

    private int min;
    private int max;
    private MutableInt number;

    public NumberConfigMenu(Player player, String name) {
        super(player, 27, name);
        loadInventory();

    }

    public void loadInventory() {
        inventory.setItem(10, new ItemCreator(Material.WOOL, 1, (byte) 14, "§c-10").create());
        inventory.setItem(11, new ItemCreator(Material.WOOL, 1, (byte) 14, "§c-5").create());
        inventory.setItem(12, new ItemCreator(Material.WOOL, 1, (byte) 14, "§c-1").create());

        inventory.setItem(14, new ItemCreator(Material.WOOL, 1, (byte) 5, "§a+1").create());
        inventory.setItem(15, new ItemCreator(Material.WOOL, 1, (byte) 5, "§a+5").create());
        inventory.setItem(16, new ItemCreator(Material.WOOL, 1, (byte) 5, "§a+10").create());
        inventory.setItem(26, new ItemCreator(Material.BARRIER, 1, (byte) 5, "§rReturn to Main Menu").create());
    }
}
