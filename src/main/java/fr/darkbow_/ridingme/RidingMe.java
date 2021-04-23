package fr.darkbow_.ridingme;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class RidingMe extends JavaPlugin {

    public static BukkitTask task;

    private RidingMe instance;

    public RidingMe getInstance() {
        return instance;
    }

    private Map<Entity, Entity> invertriding;

    @Override
    public void onEnable() {
        instance = this;
        this.invertriding = new HashMap<>();

        getServer().getPluginManager().registerEvents(new Events(this), this);

        System.out.println("[RidingMe] Plugin ON!");
    }

    @Override
    public void onDisable() {
        System.out.println("[SamplePlugin] Plugin OFF!");
    }

    public Map<Entity, Entity> getInvertriding() {
        return invertriding;
    }
}