package me.aokigahara.farmlandrpg.application.resource.handlers;

import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourceDamageHandler implements Listener {

    @EventHandler
    public void onBreak(ResourceBreakEvent event){
        event.getResource().subtractDurability(1);
    }
}
