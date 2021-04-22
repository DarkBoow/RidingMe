package fr.darkbow_.ridingme;

import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;

public class Events implements Listener {
    private RidingMe main;

    public Events(RidingMe sampleplugin){main = sampleplugin;}

    public void onMount(VehicleEnterEvent event){
        event.setCancelled(true);
        event.getEntered().addPassenger(event.getVehicle());
    }
}