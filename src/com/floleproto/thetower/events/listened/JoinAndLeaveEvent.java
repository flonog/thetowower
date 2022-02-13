package com.floleproto.thetower.events.listened;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.GameStates;
import com.floleproto.thetower.game.TeamManager;
import com.floleproto.thetower.utils.ItemCreator;
import com.floleproto.thetower.utils.Title;
import com.floleproto.thetower.utils.XpBarManager;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JoinAndLeaveEvent implements Listener {

    private Main main;

    public JoinAndLeaveEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();
        main.scoreboardManager.createScoreboard(p);
        main.scoreboardManager.setScoreboardTemplate(p, main.gameManager.getStates());
        ev.setJoinMessage("§r" + Bukkit.getOnlinePlayers().size() + "§r / §r" + Bukkit.getMaxPlayers() + " §4§l>§1§l> §a" + p.getDisplayName() + " join the game.");
        if (main.gameManager.isStates(GameStates.WAITING)) {
            p.setLevel(0);
            p.setExp(0);

            FileConfiguration config = main.getConfig();
            double x = config.getDouble("spawn.x");
            double y = config.getDouble("spawn.y");
            double z = config.getDouble("spawn.z");
            String worldName = config.getString("spawn.world");
            World world = Bukkit.getWorld(worldName);
            p.teleport(new Location(world, x, y, z));
            p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            p.setFoodLevel(20);
            p.setGameMode(GameMode.SURVIVAL);

            p.getInventory().clear();
            Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
            enchants.put(Enchantment.DURABILITY, 1);
            p.getInventory().setItem(4, new ItemCreator(Material.WHITE_WOOL, 1, "§b§lTeam", null, enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());
            if (p.hasPermission("thetowower.config") || p.hasPermission("thetowower.*") || p.isOp())
                p.getInventory().setItem(8, new ItemCreator(Material.COMMAND_BLOCK, 1, "§c§lConfig", null, enchants, Arrays.asList(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS})).create());

            Title title = new Title("§b§lThe TOwOwer", "§eThe game will start soon");
            title.sendToPlayer(p);

            main.statistics.registerPlayer(p);

            if (Bukkit.getOnlinePlayers().size() >= main.getConfig().getInt("minplayer") && !main.gameManager.isStarting()) {
                main.gameManager.startCountdown();
            }
        } else if (main.gameManager.isStates(GameStates.ONGAME)){
            if(main.teamManager.isInTeam(p)){
                p.setPlayerListName(main.teamManager.getTeam(p).getColor().toString() + p.getName() + "§r");
                p.setDisplayName(main.teamManager.getTeam(p).getColor().toString() + p.getName() + "§r");
            }
            else{
                TeamManager.spectatorTeam.addPlayer(p);
                TeamManager.spectatorTeam.setupPlayer(p);
            }
            //p.setDisplayName();
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent ev) {
        main.scoreboardManager.removeScoreboard(ev.getPlayer());
        ev.setQuitMessage("§r" + (Bukkit.getOnlinePlayers().size() - 1) + "§r / §r" + Bukkit.getMaxPlayers() + " §4§l>§l§1> §c" + ev.getPlayer().getDisplayName() + " left the game.");

        if (main.gameManager.isStates(GameStates.WAITING)) {
            if (Bukkit.getOnlinePlayers().size() - 1 < main.getConfig().getInt("minplayer") && main.gameManager.isStarting()) {
                main.gameManager.stopCountdown(false);
                Bukkit.broadcastMessage("§b§lThe TOwOwer §4§l>§1§l>§c There isn't enough players to start the game. Cancelling.");
            }
        } else if (main.gameManager.isStates(GameStates.ONGAME)) {
            if(!TeamManager.spectatorTeam.isInTeam(ev.getPlayer()))
                ev.getPlayer().setHealth(0);
        }
    }
}
