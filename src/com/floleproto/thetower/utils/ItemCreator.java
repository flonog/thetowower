package com.floleproto.thetower.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class ItemCreator {

    private Material material;
    private int amount;
    private String name = null;
    private List<String> lores = null;
    private Map<Enchantment, Integer> enchantments = null;
    private List<ItemFlag> itemFlags = null;
    private byte data;

    public ItemCreator(Material material, int amount, byte data) {
        this.material = material;
        this.amount = amount;
        this.data = data;
    }

    public ItemCreator(Material material, int amount, byte data, String name) {
        this.material = material;
        this.amount = amount;
        this.name = name;
        this.data = data;
    }

    public ItemCreator(Material material, int amount, byte data, String name, List<String> lores) {
        this.material = material;
        this.amount = amount;
        this.name = name;
        this.lores = lores;
        this.data = data;
    }

    public ItemCreator(Material material, int amount, byte data, String name, List<String> lores, Map<Enchantment, Integer> enchantments) {
        this.material = material;
        this.amount = amount;
        this.name = name;
        this.lores = lores;
        this.enchantments = enchantments;
        this.data = data;
    }

    public ItemCreator(Material material, int amount, byte data, String name, List<String> lores, Map<Enchantment, Integer> enchantments, List<ItemFlag> itemFlags) {
        this.material = material;
        this.amount = amount;
        this.name = name;
        this.lores = lores;
        this.enchantments = enchantments;
        this.itemFlags = itemFlags;
        this.data = data;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLores() {
        return this.lores;
    }

    public void setLores(List<String> lores) {
        this.lores = lores;
    }

    public Map<Enchantment, Integer> getEnchantments() {
        return this.enchantments;
    }

    public void setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
    }

    public List<ItemFlag> getItemFlags() {
        return this.itemFlags;
    }

    public void setItemFlags(List<ItemFlag> itemFlags) {
        this.itemFlags = itemFlags;
    }

    public byte getData() {
        return data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public ItemStack create() {

        ItemStack item = new ItemStack(material, amount, data);
        ItemMeta itemM = item.getItemMeta();

        if (name != null) {
            itemM.setDisplayName(name);
        }

        if (lores != null) {
            itemM.setLore(lores);
        }

        if (enchantments != null) {
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                itemM.addEnchant(entry.getKey(), entry.getValue(), true);
            }
        }

        if (itemFlags != null) {
            for (ItemFlag flag : itemFlags) {
                itemM.addItemFlags(flag);
            }
        }

        item.setItemMeta(itemM);
        return item;
    }
}
