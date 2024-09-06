package me.aokigahara.farmlandrpg.application.region.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.region.abstractions.IRegionService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RegionSwapCreator implements Listener {

    @Inject private IRegionService regionService;

    @EventHandler
    public void onWalk(PlayerMoveEvent event){
        regionService.updateRegions(event.getPlayer());
    }
}
