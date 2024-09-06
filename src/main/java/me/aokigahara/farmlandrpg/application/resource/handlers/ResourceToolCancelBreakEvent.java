package me.aokigahara.farmlandrpg.application.resource.handlers;

import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ResourceToolCancelBreakEvent implements Listener {


    @EventHandler
    public void onBreak(ResourceBreakEvent event){

        if (!event.getResource().getResourceInfo().canBreak(event.getPlayer()))
            event.setCancelled(true);
    }



}
