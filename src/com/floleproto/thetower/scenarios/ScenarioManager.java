package com.floleproto.thetower.scenarios;

import com.floleproto.thetower.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScenarioManager {

    private Set<Scenario> registeredScenario = new HashSet<>();
    private Set<Scenario> activeScenario = new HashSet<>();

    public void registerScenario(Scenario scenario){
        registeredScenario.add(scenario);
    }

    public void activeScenario(Scenario scenario){
        activeScenario.add(scenario);
    }

    public void disableScenario(Scenario scenario){
        activeScenario.remove(scenario);
        HandlerList.unregisterAll(scenario);
    }

    public void enableListener(){
        for (Scenario scenario : activeScenario ) {
            Bukkit.getPluginManager().registerEvents(scenario, Main.instance);
        }
    }

    public boolean isScenarioActive(Scenario scenario){
        return activeScenario.contains(scenario);
    }

    public Set<Scenario> getRegisteredScenario() {
        return registeredScenario;
    }

    public Set<Scenario> getActiveScenario() {
        return activeScenario;
    }

    public Scenario getScenario(String name){
        for (Scenario scenario: registeredScenario) {
            if(scenario.name.equals(name)){
                return scenario;
            }
        }
        return null;
    }
}
