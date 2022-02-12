package com.floleproto.thetower.gui.configmenu.itemspawn;

import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.gui.configmenu.MainMenu;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemSpawnMenu extends GuiManager {
    public ItemSpawnMenu(Player player) {
        super(player, 27, "§rItem Spawn Config");
        refreshInventory();
    }

    public void refreshInventory() {
        inventory.setItem(11, new ItemCreator(Material.IRON_INGOT, 1, "§eIron " + (GameConfig.spawnrate_iron_enable ? "§a§lON (§b" + GameConfig.spawnrate_iron + "§a§l)" : "§c§lOFF")).create());
        inventory.setItem(13, new ItemCreator(Material.EXPERIENCE_BOTTLE, 1, "§eXP " + (GameConfig.spawnrate_xp_enable ? "§a§lON (§b" + GameConfig.spawnrate_xp + "§a§l)" : "§c§lOFF")).create());
        inventory.setItem(15, new ItemCreator(Material.LAPIS_LAZULI, 1, "§eLapis " + (GameConfig.spawnlapis ? "§a§lON (§b" + GameConfig.spawnlapis_rate + "§a§l)" : "§c§lOFF")).create());
        inventory.setItem(26, new ItemCreator(Material.BARRIER, 1, "§rReturn to Main Menu").create());
    }

    @Override
    public void onClick(InventoryClickEvent ev) {
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
            case BARRIER:
                player.getOpenInventory().close();
                new MainMenu(player).show();
                break;
            case INK_SAC:
                if (ev.getClick() == ClickType.RIGHT) {
                    GameConfig.spawnlapis = !GameConfig.spawnlapis;
                    refreshInventory();
                } else {
                    player.getOpenInventory().close();
                    new LapisRateConfig(player).show();
                }
                break;
            case IRON_INGOT:
                if (ev.getClick() == ClickType.RIGHT) {
                    GameConfig.spawnrate_iron_enable = !GameConfig.spawnrate_iron_enable;
                    refreshInventory();
                } else {
                    player.getOpenInventory().close();
                    new IronRateConfig(player).show();
                }
                break;
            case EXPERIENCE_BOTTLE:_BOTTLE:
                if (ev.getClick() == ClickType.RIGHT) {
                    GameConfig.spawnrate_xp_enable = !GameConfig.spawnrate_xp_enable;
                    refreshInventory();
                } else {
                    player.getOpenInventory().close();
                    new XpRateConfig(player).show();
                }
                break;
        }
    }
}
