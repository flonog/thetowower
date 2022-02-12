package com.floleproto.thetower.gui.configmenu.scenario;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.gui.GuiManager;
import com.floleproto.thetower.gui.configmenu.MainMenu;
import com.floleproto.thetower.scenarios.Scenario;
import com.floleproto.thetower.scenarios.ScenarioManager;
import com.floleproto.thetower.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ScenarioMenu extends GuiManager {

    private int page = 0;

    public ScenarioMenu(Player player) {
        super(player, 54, "§rScenarios");
        setItems();
    }

    public void setItems(){
        ScenarioManager scenarioManager = Main.instance.scenarioManager;
        List<Scenario> scenarios = new ArrayList<>(scenarioManager.getRegisteredScenario());
        inventory.clear();
        for (int i = (45 * page); 45 * page <= i && i < 45 * (page + 1); i++) {
            if(scenarios.size() <= i )
                break;
            ItemStack item = new ItemCreator(scenarios.get(i).icon, 1, "§e§l" + scenarios.get(i).name + (scenarioManager.isScenarioActive(scenarios.get(i)) ? " §a§lON" : " §c§lOFF"), Collections.singletonList("§r" + scenarios.get(i).description), null, Collections.singletonList(ItemFlag.HIDE_ENCHANTS)).create();
            ItemMeta meta = item.getItemMeta();
            if(scenarioManager.isScenarioActive(scenarios.get(i))){
                meta.addEnchant(Enchantment.DURABILITY, 1, true);
            }
            if(scenarios.get(i).configGui != null) {
                List<String> lore = meta.getLore();
                lore.add("");
                lore.add("§rRight click to config");
                meta.setLore(lore);
            }

            item.setItemMeta(meta);

            inventory.addItem(item);
        }

        if(page > 0)
            inventory.setItem(48, new ItemCreator(Material.ARROW, 1,"§bPrevious page").create());
        inventory.setItem(49, new ItemCreator(Material.PAPER, 1, "§cPage " + (page + 1)).create());
        if(45 * page > scenarioManager.getRegisteredScenario().size())
            inventory.setItem(50, new ItemCreator(Material.ARROW, 1, "§bNext page").create());

        inventory.setItem(53, new ItemCreator(Material.BARRIER, 1, "§rReturn to main menu").create());
    }

    @Override
    public void onClick(InventoryClickEvent ev) {
        if (!ev.getView().getTitle().equals(this.name)) {
            return;
        }

        if (ev.getCurrentItem() == null || ev.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }

        ev.setCancelled(true);

        if (!player.hasPermission("thetowower.config") || !player.hasPermission("thetowower.*") || !player.isOp()) {
            player.getOpenInventory().close();
            return;
        }

        if (ev.getCurrentItem().getType().equals(Material.BARRIER)) {
            player.getOpenInventory().close();
            new MainMenu(player).show();
            return;
        }

        if (ev.getCurrentItem().getItemMeta().getDisplayName().equals("§bNext page")) {
            page++;
            return;
        } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("§bPrevious page")){
            page--;
            return;
        }
        ScenarioManager scenarioManager = Main.instance.scenarioManager;
        String displayName = ev.getCurrentItem().getItemMeta().getDisplayName();
        Scenario scenario = scenarioManager.getScenario(displayName.substring(4).replace(" §a§lON", "").replace(" §c§lOFF", ""));
        if(scenario == null)
            return;

        if(scenario.configGui != null && ev.getClick() == ClickType.RIGHT){
            Class<?> clazz = scenario.configGui;
            Constructor<?> ctor;
            try {
                ctor = clazz.getConstructor(new Class[] { Player.class });
                Object object = ctor.newInstance(new Object[] { this.player });
                Method method = object.getClass().getMethod("show", new Class[0]);
                method.invoke(object, new Object[0]);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } else {
            if (scenarioManager.isScenarioActive(scenario)) {
                scenarioManager.disableScenario(scenario);
            } else {
                scenarioManager.activeScenario(scenario);
            }
            setItems();
        }
    }
}
