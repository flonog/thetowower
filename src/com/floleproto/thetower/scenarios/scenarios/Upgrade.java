package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.events.listened.PlayerDieEvent;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.scenarios.Scenario;
import com.floleproto.thetower.scenarios.scenarios.configmenu.UpgradeConfigMenu;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Upgrade extends Scenario {

    public HashMap<UUID, Integer> players = new HashMap<>();
    public static boolean stone = true, gold = true, iron = true, diamond = true, diamondEnchant = true;

    public Upgrade() {
        super("Upgrade", "Your stuff upgrade when you kill someone.", Material.IRON_SWORD, UpgradeConfigMenu.class);
    }

    @EventHandler
    public void onPlayerKillEvent(PlayerDeathEvent ev){
        Player killer = ev.getEntity().getKiller();
        if(killer != null){
            int kill = 0;
            if(players.containsKey(killer.getUniqueId()))
                kill = players.get(killer.getUniqueId());
            kill++;
            players.put(killer.getUniqueId(), kill);

            if(kill == 1 && stone){
                if(killer.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS || killer.getInventory().getLeggings().getType() == Material.AIR)
                    killer.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));

                for (int i = 0; i < killer.getInventory().getContents().length; i++) {
                    if(killer.getInventory().getContents()[i] == null){
                        continue;
                    }
                    if(killer.getInventory().getContents()[i].getType().equals(Material.WOOD_SWORD)){
                        killer.getInventory().setItem(i, new ItemStack(Material.STONE_SWORD));
                        break;
                    }
                }
            }else if(kill == 2 && gold){
                if(killer.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE || killer.getInventory().getLeggings().getType() == Material.AIR)
                    killer.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));

                for (int i = 0; i < killer.getInventory().getContents().length; i++) {
                    if(killer.getInventory().getContents()[i] == null){
                        continue;
                    }
                    if(killer.getInventory().getContents()[i].getType().equals(Material.WOOD_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.STONE_SWORD)){
                        killer.getInventory().setItem(i, new ItemStack(Material.GOLD_SWORD));
                        break;
                    }
                }
            }else if(kill == 3 && iron){
                if(killer.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE || killer.getInventory().getChestplate().getType() == Material.AIR || killer.getInventory().getChestplate().getType() == Material.GOLD_CHESTPLATE)
                    killer.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));

                for (int i = 0; i < killer.getInventory().getContents().length; i++) {
                    if(killer.getInventory().getContents()[i] == null){
                        continue;
                    }
                    if(killer.getInventory().getContents()[i].equals(Material.WOOD_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.STONE_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.GOLD_SWORD)){
                        killer.getInventory().setItem(i, new ItemStack(Material.IRON_SWORD));
                        break;
                    }
                }
            }else if(kill == 4 && diamond){
                if(killer.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE || killer.getInventory().getChestplate().getType() == Material.AIR || killer.getInventory().getChestplate().getType() == Material.GOLD_CHESTPLATE || killer.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE)
                    killer.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));

                for (int i = 0; i < killer.getInventory().getContents().length; i++) {
                    if(killer.getInventory().getContents()[i] == null){
                        continue;
                    }
                    if(killer.getInventory().getContents()[i].getType().equals(Material.WOOD_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.STONE_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.GOLD_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.IRON_SWORD)){
                        killer.getInventory().setItem(i, new ItemStack(Material.DIAMOND_SWORD));
                        break;
                    }
                }
            }else if(kill == 5 && diamondEnchant){
                if(killer.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE || killer.getInventory().getChestplate().getType() == Material.AIR || killer.getInventory().getChestplate().getType() == Material.GOLD_CHESTPLATE || killer.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE || killer.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE){
                    Map<Enchantment, Integer> enchants = new HashMap<>();
                    enchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    killer.getInventory().setChestplate(new ItemCreator(Material.DIAMOND_CHESTPLATE, 1, (byte) 0, "§bDiamond Chestplate", null, enchants).create());
                }


                for (int i = 0; i < killer.getInventory().getContents().length; i++) {
                    if(killer.getInventory().getContents()[i] == null){
                        continue;
                    }
                    if(killer.getInventory().getContents()[i].getType().equals(Material.WOOD_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.STONE_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.GOLD_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.IRON_SWORD) || killer.getInventory().getContents()[i].getType().equals(Material.DIAMOND_SWORD)){
                        Map<Enchantment, Integer> enchants = new HashMap<>();
                        enchants.put(Enchantment.DAMAGE_ALL, 1);
                        killer.getInventory().setItem(i, new ItemCreator(Material.DIAMOND_SWORD, 1, (byte) 0, "§bDiamond SOwOrd", null, enchants).create());
                        break;
                    }
                }
            }
        }
        players.put(ev.getEntity().getUniqueId(), 0);
    }
}
