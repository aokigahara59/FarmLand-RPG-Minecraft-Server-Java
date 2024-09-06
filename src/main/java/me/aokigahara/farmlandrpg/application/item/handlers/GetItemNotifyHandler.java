package me.aokigahara.farmlandrpg.application.item.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.item.events.PlayerGetItemEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GetItemNotifyHandler implements Listener {

    @Inject private IPlayerInterface iPlayerInterface;

    @EventHandler
    public void onGet(PlayerGetItemEvent event){
        iPlayerInterface.sendPushNotification(event.getPlayer(),
                "+ "+event.getAmount() + " " + ChatColor.RESET+ event.getItem().getItemMeta().getDisplayName(), 0xFF00FF00,
                event.getItem());
    }
}
