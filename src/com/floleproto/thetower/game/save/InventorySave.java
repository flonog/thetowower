package com.floleproto.thetower.game.save;

import com.floleproto.thetower.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventorySave {
    public static File path = new File("plugins//TheTOwOwer");
    public static File file = new File("plugins//TheTOwOwer//inventories.yml");
    public static YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

    public static void LoadFile(){
        if(!path.exists()) path.mkdir();
        if(!file.exists()) Main.instance.saveResource("inventories.yml", true);

        try {
            yml.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveInventory(String teamName, ItemStack[] itemStack, ItemStack[] itemStackArmor){
        yml.set(teamName, null);

        for (int i = 0 ; i < itemStack.length ; i++) {
            if(itemStack[i] == null)
                continue;
            yml.set(teamName + ".inventory." + i, itemStack[i]);
        }

        for (int i = 0 ; i < itemStackArmor.length ; i++) {
            if(itemStackArmor[i] == null)
                continue;
            yml.set(teamName + ".armor." + i, itemStackArmor[i]);
        }

        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ItemStack[][] loadInventory(String teamName) {
        ConfigurationSection armorSection = yml.getConfigurationSection(teamName + ".armor");
        ConfigurationSection inventorySection = yml.getConfigurationSection(teamName + ".inventory");

        List<ItemStack> armorInventory = new ArrayList<ItemStack>();
        List<ItemStack> inventoryInventory = new ArrayList<>(Arrays.asList(new ItemStack[36]));

        for( String s : armorSection.getKeys(false)){
            armorInventory.add(Integer.parseInt(s), yml.getItemStack(teamName + ".armor." + s));
        }

        for( String s : inventorySection.getKeys(false)){
            inventoryInventory.set(Integer.parseInt(s), yml.getItemStack(teamName + ".inventory." + s));
        }

        return new ItemStack[][] {armorInventory.toArray(new ItemStack[0]), inventoryInventory.toArray(new ItemStack[0])};
    }
}
