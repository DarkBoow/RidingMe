package fr.darkbow_.ridingme;

import org.bukkit.plugin.java.JavaPlugin;

public class RidingMe extends JavaPlugin {

    private RidingMe instance;

    public RidingMe getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new Events(this), this);

        System.out.println("[RidingMe] Plugin ON!");
    }

    @Override
    public void onDisable() {
        System.out.println("[SamplePlugin] Plugin OFF!");
    }
}