package me.aokigahara.farmlandrpg.application.leveling.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.leveling.abstractions.IPlayerLevelingService;
import me.aokigahara.farmlandrpg.application.leveling.events.PlayerGainExpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerGetExpInterfaceHandler implements Listener {

    @Inject private IPlayerInterface playerInterface;
    @Inject private IPlayerLevelingService skillService;

    @EventHandler
    public void onExpGain(PlayerGainExpEvent event){
        playerInterface.sendPushNotification(event.getPlayer(), "+"+event.getFinalExp()+" exp", 0x000000);
    }

}
