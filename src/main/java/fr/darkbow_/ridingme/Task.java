package fr.darkbow_.ridingme;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task extends BukkitRunnable {
    private RidingMe main;

    public Task(RidingMe ridingme){this.main = ridingme;}

    public static boolean isRunning = false;

    @Override
    public void run() {
        if(!main.getInvertriding().isEmpty()){
            List<Entity> removelist = new ArrayList<>();
            for(Map.Entry<Entity, Entity> ridingmap : main.getInvertriding().entrySet()){
                Entity mounted;
                if(ridingmap.getValue().getPassengers().size() > 0){
                    mounted = ridingmap.getValue().getPassengers().get(ridingmap.getValue().getPassengers().size() - 1);
                } else {
                    mounted = ridingmap.getValue();
                }

                Entity vehicle = null;
                if(ridingmap.getKey().isInsideVehicle() && ridingmap.getKey().getVehicle() != null){
                    if(ridingmap.getKey().getVehicle() != ridingmap.getKey() && ridingmap.getKey().getVehicle() != ridingmap.getValue()){
                        vehicle = ridingmap.getKey().getVehicle();
                    }
                }
                mounted.addPassenger(ridingmap.getKey());
                removelist.add(ridingmap.getKey());
            }
            removelist.forEach(main.getInvertriding()::remove);
        }

        if(main.getInvertriding().isEmpty()){
            isRunning = false;
            cancel();
        }
    }
}