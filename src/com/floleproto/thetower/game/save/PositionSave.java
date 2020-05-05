package com.floleproto.thetower.game.save;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.game.Area;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PositionSave {

    public static Location redSpawn;
    public static Area redPool;
    public static Area redPoolProtected;
    public static Area redSpawnProtected;

    public static Location blueSpawn;
    public static Area bluePool;
    public static Area bluePoolProtected;
    public static Area blueSpawnProtected;

    public static Location ironSpawn;
    public static Location xpSpawn;
    public static Location lapisSpawn;

    public static List<Location> chestPos;

    public static File path = new File("plugins//TheTOwOwer");
    public static File file = new File("plugins//TheTOwOwer//positions.yml");
    public static YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

    public static void LoadFile() {
        if (!path.exists())
            path.mkdir();
        if (!file.exists())
            Main.instance.saveResource("positions.yml", true);

        try {
            yml.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void registerChestPos(Location pos) {
        chestPos.add(pos);
    }

    public static void loadChestsPos() {
        ConfigurationSection section = yml.getConfigurationSection("chest");
        chestPos = new ArrayList<>();
        for (String s : section.getKeys(false)) {
            double x = yml.getDouble("chest." + s + ".x");
            double y = yml.getDouble("chest." + s + ".y");
            double z = yml.getDouble("chest." + s + ".z");
            String world = yml.getString("chest." + s + ".world");
            chestPos.add(new Location(Bukkit.getWorld(world), x, y, z));
        }
    }

    public static void saveChests() throws IOException {
        yml.set("chest", null);
        for (int i = 0; i < chestPos.size(); i++) {
            String x = String.valueOf(chestPos.get(i).getX());
            String y = String.valueOf(chestPos.get(i).getY());
            String z = String.valueOf(chestPos.get(i).getZ());
            String world = chestPos.get(i).getWorld().getName();

            yml.set("chest." + i + ".x", Double.parseDouble(x));
            yml.set("chest." + i + ".y", Double.parseDouble(y));
            yml.set("chest." + i + ".z", Double.parseDouble(z));
            yml.set("chest." + i + ".world", world);

            yml.save(file);
        }
    }

    public static void removeChestPos(Location pos) {
        chestPos.remove(pos);
    }

    public static void LoadPositions() {
        redSpawn = new Location(Bukkit.getWorld(yml.getString("red.spawn.world")), yml.getDouble("red.spawn.x"), yml.getDouble("red.spawn.y"), yml.getDouble("red.spawn.z"), (float) yml.getDouble("red.spawn.yaw"), (float) yml.getDouble("red.spawn.pitch"));

        Location pos1RedPool = new Location(Bukkit.getWorld(yml.getString("red.pool.pos1.world")), yml.getDouble("red.pool.pos1.x"), yml.getDouble("red.pool.pos1.y"), yml.getDouble("red.pool.pos1.z"));
        Location pos2RedPool = new Location(Bukkit.getWorld(yml.getString("red.pool.pos2.world")), yml.getDouble("red.pool.pos2.x"), yml.getDouble("red.pool.pos2.y"), yml.getDouble("red.pool.pos2.z"));
        redPool = new Area(pos1RedPool, pos2RedPool);

        Location pos1RedProtectedPool = new Location(Bukkit.getWorld(yml.getString("red.protectedPool.pos1.world")), yml.getDouble("red.protectedPool.pos1.x"), yml.getDouble("red.protectedPool.pos1.y"), yml.getDouble("red.protectedPool.pos1.z"));
        Location pos2RedProtectedPool = new Location(Bukkit.getWorld(yml.getString("red.protectedPool.pos2.world")), yml.getDouble("red.protectedPool.pos2.x"), yml.getDouble("red.protectedPool.pos2.y"), yml.getDouble("red.protectedPool.pos2.z"));
        redPoolProtected = new Area(pos1RedProtectedPool, pos2RedProtectedPool);

        Location pos1RedSpawnProtected = new Location(Bukkit.getWorld(yml.getString("red.protectedSpawn.pos1.world")), yml.getDouble("red.protectedSpawn.pos1.x"), yml.getDouble("red.protectedSpawn.pos1.y"), yml.getDouble("red.protectedSpawn.pos1.z"));
        Location pos2RedSpawnProtected = new Location(Bukkit.getWorld(yml.getString("red.protectedSpawn.pos2.world")), yml.getDouble("red.protectedSpawn.pos2.x"), yml.getDouble("red.protectedSpawn.pos2.y"), yml.getDouble("red.protectedSpawn.pos2.z"));
        redSpawnProtected = new Area(pos1RedSpawnProtected, pos2RedSpawnProtected);

        blueSpawn = new Location(Bukkit.getWorld(yml.getString("blue.spawn.world")), yml.getDouble("blue.spawn.x"), yml.getDouble("blue.spawn.y"), yml.getDouble("blue.spawn.z"), (float) yml.getDouble("blue.spawn.yaw"), (float) yml.getDouble("blue.spawn.pitch"));

        Location pos1BluePool = new Location(Bukkit.getWorld(yml.getString("blue.pool.pos1.world")), yml.getDouble("blue.pool.pos1.x"), yml.getDouble("blue.pool.pos1.y"), yml.getDouble("blue.pool.pos1.z"));
        Location pos2BluePool = new Location(Bukkit.getWorld(yml.getString("blue.pool.pos2.world")), yml.getDouble("blue.pool.pos2.x"), yml.getDouble("blue.pool.pos2.y"), yml.getDouble("blue.pool.pos2.z"));
        bluePool = new Area(pos1BluePool, pos2BluePool);

        Location pos1BlueProtectedPool = new Location(Bukkit.getWorld(yml.getString("blue.protectedPool.pos1.world")), yml.getDouble("blue.protectedPool.pos1.x"), yml.getDouble("blue.protectedPool.pos1.y"), yml.getDouble("blue.protectedPool.pos1.z"));
        Location pos2BlueProtectedPool = new Location(Bukkit.getWorld(yml.getString("blue.protectedPool.pos2.world")), yml.getDouble("blue.protectedPool.pos2.x"), yml.getDouble("blue.protectedPool.pos2.y"), yml.getDouble("blue.protectedPool.pos2.z"));
        bluePoolProtected = new Area(pos1BlueProtectedPool, pos2BlueProtectedPool);

        Location pos1BlueSpawnProtected = new Location(Bukkit.getWorld(yml.getString("blue.protectedSpawn.pos1.world")), yml.getDouble("blue.protectedSpawn.pos1.x"), yml.getDouble("blue.protectedSpawn.pos1.y"), yml.getDouble("blue.protectedSpawn.pos1.z"));
        Location pos2BlueSpawnProtected = new Location(Bukkit.getWorld(yml.getString("blue.protectedSpawn.pos2.world")), yml.getDouble("blue.protectedSpawn.pos2.x"), yml.getDouble("blue.protectedSpawn.pos2.y"), yml.getDouble("blue.protectedSpawn.pos2.z"));
        blueSpawnProtected = new Area(pos1BlueSpawnProtected, pos2BlueSpawnProtected);

        ironSpawn = new Location(Bukkit.getWorld(yml.getString("general.ironSpawner.world")), yml.getDouble("general.ironSpawner.x"), yml.getDouble("general.ironSpawner.y"), yml.getDouble("general.ironSpawner.z"));
        xpSpawn = new Location(Bukkit.getWorld(yml.getString("general.xpSpawner.world")), yml.getDouble("general.xpSpawner.x"), yml.getDouble("general.xpSpawner.y"), yml.getDouble("general.xpSpawner.z"));
        lapisSpawn = new Location(Bukkit.getWorld(yml.getString("general.lapisSpawner.world")), yml.getDouble("general.lapisSpawner.x"), yml.getDouble("general.lapisSpawner.y"), yml.getDouble("general.lapisSpawner.z"));

        loadChestsPos();
    }
}
