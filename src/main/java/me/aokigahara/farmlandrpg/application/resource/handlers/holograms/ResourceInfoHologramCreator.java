package me.aokigahara.farmlandrpg.application.resource.handlers.holograms;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceHologramService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceService;
import me.aokigahara.farmlandrpg.application.common.events.HologramPreSpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ResourceInfoHologramCreator implements Listener {

    @Inject private IResourceService resourceService;
    @Inject private IResourceHologramService hologramService;
    private final double distance = 15;

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        var resources = resourceService.getAllResources();

        for (var resource : resources){
            var distant = resource.getLocation().distance(event.getPlayer().getLocation());
            boolean hasHologram = hologramService.hasHolograms(resource);

            if (!hasHologram){
                if (distant < distance && resource.getDurability() != 0){
                    // спавн голограм
                    Location location = resource.getLocation().clone().subtract(event.getPlayer().getFacing().getDirection())
                            .add(0, 1, 0);
                    var preSpawnEvent = new HologramPreSpawnEvent(location, resource);

                    Bukkit.getServer().getPluginManager().callEvent(preSpawnEvent);

                    hologramService.addHologram(preSpawnEvent.getResource(), preSpawnEvent.getHologramList());
                }
            } else {
                if (distant > distance ){
//&&
//
                    hologramService.deleteHologram(resource);
                }
            }

        }
    }
}
