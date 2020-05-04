package com.floleproto.thetower.gui.configmenu;

import com.floleproto.thetower.game.GameConfig;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.gui.configmenu.inventory.InventoryMenu;
import com.floleproto.thetower.gui.configmenu.itemspawn.ItemSpawnMenu;
import com.floleproto.thetower.gui.configmenu.point.PointConfigMenu;
import com.floleproto.thetower.gui.configmenu.time.TimeConfig;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainMenu extends GuiManager {
    public MainMenu(Player player) {
        super(player, 27, "§bConfig Menu");
        refresh();
    }

    private void refresh(){
        Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
        enchants.put(Enchantment.DURABILITY, 1);
        inventory.setItem(0, new ItemCreator(Material.NETHER_STAR, 1, (byte) 0, "§ePoint to win (§b§l" + GameConfig.scoretowin + "§e)", Arrays.asList(new String[] { "§rThe point needed for a team to win." }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(1, new ItemCreator(Material.IRON_AXE, 1, (byte) 0, "§eFriendlyFire " + (GameConfig.friendlyfire ? "§a§lON" : "§c§lOFF"), Arrays.asList(new String[] { "§rDefined if the FriendlyFire is active or not. " }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(2, new ItemCreator(Material.CHEST, 1, (byte) 0, "§eBreakable Chest " + (GameConfig.chest_breakable ? "§a§lON" : "§c§lOFF"), Arrays.asList(new String[] { "§rDefined if the chests can be destroyed or not. " }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(3, new ItemCreator(Material.WOOL, 1, (byte) 4, "§eEternal Day " + (GameConfig.eternalday ? "§a§lON" : "§c§lOFF"), Arrays.asList(new String[] { "§rDefined if the day is there all the time. " }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(4, new ItemCreator(Material.GLASS, 1, (byte) 14, "§eHeal on point " + (GameConfig.healonpoint ? "§a§lON" : "§c§lOFF"), Arrays.asList(new String[] { "§rHeal the player when he score a point. " }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(5, new ItemCreator(Material.SKULL_ITEM, 1, (byte) 2, "§eMob spawning " + (GameConfig.spawnmob ? "§a§lON" : "§c§lOFF"), Arrays.asList(new String[] { "§rDefined if the mobs can spawn. " }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(6, new ItemCreator(Material.TNT, 1, (byte) 0, "§eMob griefing " + (GameConfig.mobgriefing ? "§a§lON" : "§c§lOFF"), Arrays.asList(new String[] { "§rDefined if the mobs can damage the terrain. " }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(7, new ItemCreator(Material.IRON_INGOT, 1, (byte) 0, "§eItem Spawning Config", Arrays.asList(new String[] { "§rLapis spawn : " + (GameConfig.spawnlapis ? "§a§lON (§b" + GameConfig.spawnlapis_rate + "§a§l)" : "§c§lOFF"), "§rXP spawn : §b§l" + (GameConfig.spawnrate_xp_enable ? "§a§lON (§b" + GameConfig.spawnrate_xp + "§a§l)" : "§c§lOFF"), "§rIron SpawnRate : §b§l" + (GameConfig.spawnrate_iron_enable ? "§a§lON (§b" + GameConfig.spawnrate_iron + "§a§l)" : "§c§lOFF")}), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(8, new ItemCreator(Material.IRON_CHESTPLATE, 1, (byte) 0, "§eInventory Config", Arrays.asList(new String[] { "§rDefined the inventory of the teams" }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(9, new ItemCreator(Material.BOOK, 1, (byte) 0, "§eScenario", Arrays.asList(new String[] { "§c§lComing soon" }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
        inventory.setItem(10, new ItemCreator(Material.WATCH, 1, (byte) 0, "§eTime Limit " + (GameConfig.timelimit_enable ? "§a§lON (§b" + GameConfig.timelimit_time +"§a§l)" : "§c§lOFF"), Arrays.asList(new String[] { "§rSet the time limit.", "§rLeft click to toggle.", "§rRight click to edit the time." }), enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
    }

    @EventHandler
    public void onClick(InventoryClickEvent ev) {
        if (ev.getInventory() == null)
            return;

        if (!ev.getInventory().equals(inventory)){
            return;
        }

        if (ev.getCurrentItem() == null){
            return;
        }

        ev.setCancelled(true);

        if (!player.hasPermission("thetowower.config") || !player.hasPermission("thetowower.*") || !player.isOp()){
            player.getOpenInventory().close();
            return;
        }

        switch (ev.getCurrentItem().getType()){
            case NETHER_STAR:
                player.getOpenInventory().close();
                new PointConfigMenu(player).show();
                break;
            case IRON_AXE:
                GameConfig.friendlyfire = !GameConfig.friendlyfire;
                refresh();
                break;
            case CHEST:
                GameConfig.chest_breakable = !GameConfig.chest_breakable;
                refresh();
                break;
            case WOOL:
                GameConfig.eternalday = !GameConfig.eternalday;
                refresh();
                break;
            case GLASS:
                GameConfig.healonpoint = !GameConfig.healonpoint;
                refresh();
                break;
            case SKULL_ITEM:
                GameConfig.spawnmob = !GameConfig.spawnmob;
                refresh();
                break;
            case TNT:
                GameConfig.mobgriefing = !GameConfig.mobgriefing;
                refresh();
                break;
            case IRON_INGOT:
                player.getOpenInventory().close();
                new ItemSpawnMenu(player).show();
                break;
            case IRON_CHESTPLATE:
                player.getOpenInventory().close();
                new InventoryMenu(player).show();
                break;
            case BOOK:
                break;
            case WATCH:
                if(ev.getClick() == ClickType.RIGHT){
                    GameConfig.timelimit_enable = !GameConfig.timelimit_enable;
                    refresh();
                } else {
                    player.getOpenInventory().close();
                    new TimeConfig(player).show();
                }
                break;
        }
    }
}
