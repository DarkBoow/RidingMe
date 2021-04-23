package fr.darkbow_.ridingme;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.spigotmc.event.entity.EntityMountEvent;

public class Events implements Listener {
    private RidingMe main;

    public Events(RidingMe sampleplugin){main = sampleplugin;}

    @EventHandler
    public void onMount(EntityMountEvent event){
        if(!main.getInvertriding().containsKey(event.getEntity())){
            boolean action = true;
            if(event.getEntity().getPassengers().contains(event.getMount())){
                action = false;
            }

            if(main.getInvertriding().containsKey(event.getMount()) && main.getInvertriding().get(event.getMount()).equals(event.getEntity())){
                action = false;
            }

            if(action){
                if(!event.getMount().isInsideVehicle()){
                    event.setCancelled(true);
                    main.getInvertriding().put(event.getMount(), event.getEntity());
                    if(!Task.isRunning){
                        Task.isRunning = true;
                        RidingMe.task = new Task(main.getInstance()).runTaskTimer(main.getInstance(), 1L, 1L);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event){
        if(event.isSneaking()){
            if(event.getPlayer().getPassengers().size() > 0){
                int passengercount = event.getPlayer().getPassengers().size();
                Entity vehicle = event.getPlayer().getPassengers().get(0);
                while(passengercount > 0){
                    passengercount = vehicle.getPassengers().size();
                    if(vehicle.getPassengers().size() > 0){
                        vehicle = vehicle.getPassengers().get(0);
                    }
                }
                vehicle.leaveVehicle();
            }
        }
    }
}