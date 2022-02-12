package com.floleproto.thetower.gui.configmenu;

import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class NumberConfigMenu extends GuiManager {

    private int min;
    private int max;
    private int number;

    public NumberConfigMenu(Player player, String name) {
        super(player, 27, name);
        loadInventory();

    }

    public void loadInventory() {
        inventory.setItem(10, new ItemCreator(Material.RED_WOOL, 1, "§c-10").create());
        inventory.setItem(11, new ItemCreator(Material.RED_WOOL, 1, "§c-5").create());
        inventory.setItem(12, new ItemCreator(Material.RED_WOOL, 1, "§c-1").create());

        inventory.setItem(14, new ItemCreator(Material.GREEN_WOOL, 1, "§a+1").create());
        inventory.setItem(15, new ItemCreator(Material.GREEN_WOOL, 1, "§a+5").create());
        inventory.setItem(16, new ItemCreator(Material.GREEN_WOOL, 1, "§a+10").create());
        inventory.setItem(26, new ItemCreator(Material.BARRIER, 1, "§rReturn to Main Menu").create());
    }
}
