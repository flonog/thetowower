package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GameConfig {
    public static int minplayer = 5;
    public static int scoretowin = 10;

    public static long spawnrate_xp = 5; //TODO: XP and Iron spawner
    public static long spawnrate_iron = 5;
    public static boolean spawnlapis = false; //TODO: No lapis into the enchantment table when it disabled

    public static boolean chest_breakable = true;
    public static boolean friendlyfire = false;
    public static boolean timelimit_enable = true;
    public static int timelimit_time = 30;
    public static boolean spawnmob = false; //TODO: Change spawn mob
    public static boolean eternalday = true; //TODO: Change eternal day

    public static boolean healonpoint = true;

    public static void loadConfig() {
        FileConfiguration config = Main.instance.getConfig();

        minplayer = config.getInt("minplayer");
        scoretowin = config.getInt("scoretowin");

        spawnrate_xp = config.getLong("spawnrate_xp");
        spawnrate_iron = config.getLong("spawnrate_iron");
        spawnlapis = config.getBoolean("spawnlapis");

        chest_breakable = config.getBoolean("chest_breakable");
        friendlyfire = config.getBoolean("friendlyfire");
        timelimit_enable = config.getBoolean("timelimit_enable");
        timelimit_time = config.getInt("timelimit_time");
        spawnmob = config.getBoolean("spawnmob");
        eternalday = config.getBoolean("eternalday");

        healonpoint = config.getBoolean("healonpoint");
    }

    public static void saveConfig() {
        FileConfiguration config = Main.instance.getConfig();

        config.set("minplayer", minplayer);
        config.set("scoretowin", scoretowin);

        config.set("spawnrate_xp", spawnrate_xp);
        config.set("spawnrate_iron", spawnrate_iron);
        config.set("spawnlapis", spawnlapis);

        config.set("chest_breakable", chest_breakable);
        config.set("friendlyfire", friendlyfire);
        config.set("timelimit_enable", timelimit_enable);
        config.set("timelimit_time", timelimit_time);
        config.set("spawnmob", spawnmob);

        config.set("healonpoint", healonpoint);

        config.set("eternalday", eternalday);

        Main.instance.saveConfig();
    }

    public static void sendRules(Player p){
        p.sendMessage("\n§c §b===================================================\n§c ");
        for (String s : getRules()) {
            p.sendMessage( "§e" + s);
        }
        p.sendMessage("\n§c §b===================================================\n ");
    }

    public static void sendRules(CommandSender commandSender){
        commandSender.sendMessage("\n§c §b===================================================\n§c ");
        for (String s : getRules()) {
            commandSender.sendMessage( "§e" + s);
        }
        commandSender.sendMessage("\n§c §b===================================================\n§c ");
    }

    private static String[] getRules(){
        return new String[] {
                "   Score to Win : §b§l" + scoretowin,
                "   XP SpawnRate : §b§l" + spawnrate_xp,
                "   Iron SpawnRate : §b§l" + spawnrate_iron,
                "   Lapis spawn : " + (spawnlapis ? "§a§lON" : "§c§lOFF") + "§r\n§c ",
                "   Time Limit : " + (timelimit_enable ? "§a§lON (§b" + timelimit_time +"§a)" : "§c§lOFF") + "§r",
                "   Breakable Chest : " + (chest_breakable ? "§a§lON" : "§c§lOFF") + "§r",
                "   FriendlyFire : " + (friendlyfire ? "§a§lON" : "§c§lOFF") + "§r",
                "   Mob spawn : " + (spawnmob ? "§a§lON" : "§c§lOFF") + "§r",
                "   Eternal day : " + (eternalday ? "§a§lON" : "§c§lOFF") + "§r",
                "   Heal on point marked : " + (healonpoint ? "§a§lON" : "§c§lOFF") + "§r§r\n§c ",
        };
    }
}
