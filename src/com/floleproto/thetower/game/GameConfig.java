package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GameConfig {
    public static int minplayer = 5;
    public static int scoretowin = 10;

    public static long spawnrate_xp = 5;
    public static boolean spawnrate_xp_enable = true;
    public static long spawnrate_iron = 5;
    public static boolean spawnrate_iron_enable = true;
    public static boolean spawnlapis = false;
    public static long spawnlapis_rate = 5;

    public static boolean chest_breakable = true;
    public static boolean friendlyfire = false;
    public static boolean timelimit_enable = true;
    public static int timelimit_time = 30;
    public static boolean spawnmob = false;
    public static boolean mobgriefing = false;
    public static boolean eternalday = true;

    public static boolean healonpoint = true;

    public static void loadConfig() {
        FileConfiguration config = Main.instance.getConfig();

        minplayer = config.getInt("minplayer");
        scoretowin = config.getInt("scoretowin");

        spawnrate_xp = config.getLong("spawnrate_xp");
        spawnrate_xp_enable = config.getBoolean("spawnrate_xp_enable");
        spawnrate_iron = config.getLong("spawnrate_iron");
        spawnrate_iron_enable = config.getBoolean("spawnrate_iron_enable");
        spawnlapis = config.getBoolean("spawnlapis");
        spawnlapis_rate = config.getLong("spawnlapis_rate");

        chest_breakable = config.getBoolean("chest_breakable");
        friendlyfire = config.getBoolean("friendlyfire");
        timelimit_enable = config.getBoolean("timelimit_enable");
        timelimit_time = config.getInt("timelimit_time");
        spawnmob = config.getBoolean("spawnmob");
        mobgriefing = config.getBoolean("mobgriefing");
        eternalday = config.getBoolean("eternalday");

        healonpoint = config.getBoolean("healonpoint");
    }

    public static void saveConfig() {
        FileConfiguration config = Main.instance.getConfig();

        config.set("minplayer", minplayer);
        config.set("scoretowin", scoretowin);

        config.set("spawnrate_xp", spawnrate_xp);
        config.set("spawnrate_xp_enable", spawnrate_xp_enable);
        config.set("spawnrate_iron", spawnrate_iron);
        config.set("spawnrate_iron_enable", spawnrate_iron_enable);
        config.set("spawnlapis", spawnlapis);
        config.set("spawnlapis_rate", spawnlapis_rate);

        config.set("chest_breakable", chest_breakable);
        config.set("friendlyfire", friendlyfire);
        config.set("timelimit_enable", timelimit_enable);
        config.set("timelimit_time", timelimit_time);
        config.set("spawnmob", spawnmob);
        config.set("mobgriefing", mobgriefing);

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
                "   XP spawn : §b§l" + (spawnrate_xp_enable ? "§a§lON (§b" + spawnrate_xp + "§a§l)" : "§c§lOFF") + "§r",
                "   Iron spawn : §b§l" + (spawnrate_iron_enable ? "§a§lON (§b" + spawnrate_iron + "§a§l)" : "§c§lOFF") + "§r",
                "   Lapis spawn : " + (spawnlapis ? "§a§lON (§b" + spawnlapis_rate + "§a§l)" : "§c§lOFF") + "§r\n§c ",
                "   Time Limit : " + (timelimit_enable ? "§a§lON (§b" + timelimit_time +"§a§l)" : "§c§lOFF") + "§r",
                "   Breakable Chest : " + (chest_breakable ? "§a§lON" : "§c§lOFF") + "§r",
                "   FriendlyFire : " + (friendlyfire ? "§a§lON" : "§c§lOFF") + "§r",
                "   Eternal day : " + (eternalday ? "§a§lON" : "§c§lOFF") + "§r",
                "   Heal on point marked : " + (healonpoint ? "§a§lON" : "§c§lOFF") + "§r\n§c ",
                "   Mob spawning : " + (spawnmob ? "§a§lON" : "§c§lOFF") + "§r",
                "   Mob griefing : " + (mobgriefing ? "§a§lON" : "§c§lOFF") + "§r",
        };
    }
}
