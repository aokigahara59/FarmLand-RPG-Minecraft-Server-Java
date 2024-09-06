package me.aokigahara.farmlandrpg.application.resource.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourceBreakCameraShakeHandler implements Listener {

    @Inject private IPlayerInterface playerInterface;

    @EventHandler
    public void onBlockBreak(ResourceBreakEvent event){
        playerInterface.sendShake(event.getPlayer(), 0.16);
    }
}
