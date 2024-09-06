package me.aokigahara.farmlandrpg.application.resource.handlers;

import me.aokigahara.farmlandrpg.application.item.events.PlayerGetItemEvent;
import me.aokigahara.farmlandrpg.application.item.services.InventoryHelper;
import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourceGiveItemHandler implements Listener {

    @EventHandler
    public void onResourceBreak(ResourceBreakEvent event){
        var info = event.getResource().getResourceInfo();

        for (var x : info.getDrops()){
            InventoryHelper.giveItemToPlayer(event.getPlayer(), x.getFirst(), x.getSecond(), PlayerGetItemEvent.GotItemType.Mined);
        }
    }
}
