package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class GameConfig {
    public static int minplayer = 5;
    public static int scoretowin = 10;

    public static long spawnrate_xp = 5; //TODO: XP and Iron spawner
    public static long spawnrate_iron = 5;
    public static boolean spawnlapis = false;

    public static boolean chest_breakable = true;
    public static boolean friendlyfire = false;
    public static boolean nolapisneeded = true; //TODO: No lapis into the enchantment table
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
        nolapisneeded = config.getBoolean("nolapisneeded");
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
        config.set("nolapisneeded", nolapisneeded);
        config.set("timelimit_enable", timelimit_enable);
        config.set("timelimit_time", timelimit_time);
        config.set("spawnmob", spawnmob);

        config.set("healonpoint", healonpoint);

        config.set("eternalday", eternalday);

        Main.instance.saveConfig();
    }
}
