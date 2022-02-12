package com.floleproto.thetower.scenarios.scenarios.configmenu;

import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.gui.configmenu.inventory.InventoryMenu;
import com.floleproto.thetower.gui.configmenu.itemspawn.ItemSpawnMenu;
import com.floleproto.thetower.gui.configmenu.point.PointConfigMenu;
import com.floleproto.thetower.gui.configmenu.scenario.ScenarioMenu;
import com.floleproto.thetower.gui.configmenu.time.TimeConfig;
import com.floleproto.thetower.scenarios.scenarios.Upgrade;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class UpgradeConfigMenu extends GuiManager {

    public UpgradeConfigMenu(Player player) {
        super(player, 27, "§rUpgrade Config");
        refreshItem();
    }

    public void refreshItem(){
        Map<Enchantment, Integer> enchants = new HashMap<>();
        enchants.put(Enchantment.DURABILITY, 1);

        inventory.setItem(9, new ItemCreator(Material.STONE_SWORD, 1, "§7§lStone level (§r" + (Upgrade.stone ? "§a§lON" : "§c§lOFF" )  + "§7§l)").create());
        inventory.setItem(11, new ItemCreator(Material.GOLDEN_SWORD, 1, "§6§lGold level (§r" + (Upgrade.gold ? "§a§lON" : "§c§lOFF" )  + "§6§l)").create());
        inventory.setItem(13, new ItemCreator(Material.IRON_SWORD, 1, "§r§lIron level (§r" + (Upgrade.iron ? "§a§lON" : "§c§lOFF" )  + "§r§l)").create());
        inventory.setItem(15, new ItemCreator(Material.DIAMOND_SWORD, 1, "§b§lDiamond level (§r" + (Upgrade.diamond ? "§a§lON" : "§c§lOFF" )  + "§b§l)").create());
        inventory.setItem(17, new ItemCreator(Material.ENCHANTED_BOOK, 1, "§b§lEnchant level (§r" + (Upgrade.diamondEnchant ? "§a§lON" : "§c§lOFF" )  + "§b§l)", null, enchants).create());
        inventory.setItem(24, new ItemCreator(Material.BARRIER, 1, "§rBack").create());
    }

    @EventHandler
    public void onClick(InventoryClickEvent ev){
        if (ev.getInventory() == null)
            return;

        if (!ev.getInventory().equals(inventory)) {
            return;
        }

        if (ev.getCurrentItem() == null) {
            return;
        }

        ev.setCancelled(true);

        if (!player.hasPermission("thetowower.config") || !player.hasPermission("thetowower.*") || !player.isOp()) {
            player.getOpenInventory().close();
            return;
        }

        switch (ev.getCurrentItem().getType()) {
            case STONE_SWORD:
                Upgrade.stone = !Upgrade.stone;
                refreshItem();
                break;
            case GOLDEN_SWORD:
                Upgrade.gold = !Upgrade.gold;
                refreshItem();
                break;
            case IRON_SWORD:
                Upgrade.iron = !Upgrade.iron;
                refreshItem();
                break;
            case DIAMOND_SWORD:
                Upgrade.diamond = !Upgrade.diamond;
                refreshItem();
                break;
            case ENCHANTED_BOOK:
                Upgrade.diamondEnchant = !Upgrade.diamondEnchant;
                refreshItem();
                break;
            case BARRIER:
                player.getOpenInventory().close();
                new ScenarioMenu(player).show();
                break;
        }
    }
}
