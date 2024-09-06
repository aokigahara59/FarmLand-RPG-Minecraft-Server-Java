package me.aokigahara.farmlandrpg.application.resource.handlers;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceService;
import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ResourceBreakCreator implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        var resource = FarmLandRpg.getInjector().getInstance(IResourceService.class).getResource(event.getBlock());

        if (resource != null)
            Bukkit.getServer().getPluginManager()
                .callEvent(new ResourceBreakEvent(event.getBlock(), event.getPlayer(), resource));
    }
}
