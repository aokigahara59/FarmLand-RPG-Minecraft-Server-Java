package me.aokigahara.farmlandrpg.application.resource.handlers.holograms;

import me.aokigahara.farmlandrpg.application.common.events.HologramPreSpawnEvent;
import me.aokigahara.farmlandrpg.application.resource.realizations.SimpleCoalResource;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourceHologramFixLocationMiddleware implements Listener {

    @EventHandler
    public void preSpawn(HologramPreSpawnEvent event){
        var resource = event.getResource();
        if (resource instanceof SimpleCoalResource){
            event.setLocation(resource.getLocation().clone().add(0, 3, 0));
        }
    }
}
