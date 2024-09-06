package me.aokigahara.farmlandrpg.application.resource.handlers;

import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourceParticlesOnBreakHandler implements Listener {

    @EventHandler
    public void onEvent(ResourceBreakEvent event){
        var player = event.getPlayer();
        var location = event.getBlock().getLocation().clone();
        double offsetX = (location.getX() >= 0) ? 0.5 : -0.5;
        double offsetY = (location.getY() >= 0) ? 0.5 : -0.5;
        double offsetZ = (location.getZ() >= 0) ? 0.5 : -0.5;

        location.add(offsetX, offsetY, offsetZ+1);
        player.spawnParticle(Particle.COMPOSTER, location, 100, 0.5, 0.5, 0.5, 0.1);
    }
}
