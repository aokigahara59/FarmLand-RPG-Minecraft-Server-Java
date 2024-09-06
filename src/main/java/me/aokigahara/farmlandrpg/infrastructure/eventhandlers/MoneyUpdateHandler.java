package me.aokigahara.farmlandrpg.infrastructure.eventhandlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.ICrossplatformDataManager;
import me.aokigahara.farmlandrpg.application.common.events.PlayerUpdateMoneyEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MoneyUpdateHandler implements Listener {

    @Inject private ICrossplatformDataManager dataManager;

    @EventHandler
    public void onUpdate(PlayerUpdateMoneyEvent event){
        dataManager.setData(event.getPlayer(), "balance", event.getNewBalance());
    }
}
