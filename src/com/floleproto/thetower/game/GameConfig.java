package com.floleproto.thetower.game;

import com.floleproto.thetower.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class GameConfig {
    int minplayer = 5;
    int scoretowin = 10;

    long spawnrate_xp = 5;
    long spawnrate_iron = 5;
    boolean spawnlapis = false;

    boolean chest_breakable = true;
    boolean friendlyfire = false;
    boolean nolapisneeded = true;
    boolean timelimit = true;
    boolean spawnmob = false;
    boolean eternalday = true;

    public void loadConfig() {
        FileConfiguration config = Main.instance.getConfig();

        minplayer = config.getInt("minplayer");
        scoretowin = config.getInt("scoretowin");

        spawnrate_xp = config.getLong("spawnrate_xp");
        spawnrate_iron = config.getLong("spawnrate_iron");
        spawnlapis = config.getBoolean("spawnlapis");

        chest_breakable = config.getBoolean("chest_breakable");
        friendlyfire = config.getBoolean("friendlyfire");
        nolapisneeded = config.getBoolean("nolapisneeded");
        timelimit = config.getBoolean("timelimit");
        spawnmob = config.getBoolean("spawnmob");
        eternalday = config.getBoolean("eternalday");
    }

    public void saveConfig() {

        FileConfiguration config = Main.instance.getConfig();

        config.set("minplayer", minplayer);
        config.set("scoretowin", scoretowin);

        config.set("spawnrate_xp", spawnrate_xp);
        config.set("spawnrate_iron", spawnrate_iron);
        config.set("spawnlapis", spawnlapis);

        config.set("chest_breakable", chest_breakable);
        config.set("friendlyfire", friendlyfire);
        config.set("nolapisneeded", nolapisneeded);
        config.set("timelimit", timelimit);
        config.set("spawnmob", spawnmob);
        config.set("eternalday", eternalday);

    }
}
