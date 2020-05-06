package com.floleproto.thetower.scenarios.scenarios;

import com.floleproto.thetower.Main;
import com.floleproto.thetower.events.custom.GameStartEvent;
import com.floleproto.thetower.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class SuperHero extends Scenario {

    public Map<UUID, Integer> playerPotionEffect = new HashMap<>();
    public ArrayList<PotionEffect> potionEffects = new ArrayList<>();

    public SuperHero() {
        super("Super Hero", "A random effect is giving to you.", Material.POTION, null);
        potionEffects.add(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
        potionEffects.add(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
        potionEffects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
        potionEffects.add(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
        potionEffects.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
        potionEffects.add(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 1));
    }

    private void assignPotion(Player p){
        int random = new Random().nextInt(potionEffects.size());
        playerPotionEffect.put(p.getUniqueId(), random);
    }

    private PotionEffect getPotionEffect(Player p){
        int index = playerPotionEffect.get(p.getUniqueId());
        return potionEffects.get(index);
    }

    @EventHandler
    public void onGameStart(GameStartEvent ev){
        for (Player p : Bukkit.getOnlinePlayers()) {
            assignPotion(p);
            p.addPotionEffect(getPotionEffect(p));
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev){
        Player p = ev.getPlayer();
        Bukkit.getScheduler().runTaskLater(Main.instance, () -> p.addPotionEffect(getPotionEffect(p)), 1L);

    }
}
