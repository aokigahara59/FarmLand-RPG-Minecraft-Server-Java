package me.aokigahara.farmlandrpg.application.item.handlers;

import me.aokigahara.farmlandrpg.application.item.events.PlayerGetItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class PickupItemEventHandler implements Listener {
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event){
        if (!(event.getEntity() instanceof Player player)) return;

        var getEvent = new PlayerGetItemEvent(player, event.getItem().getItemStack(),
                event.getItem().getItemStack().getAmount(), PlayerGetItemEvent.GotItemType.PickedUp);

        Bukkit.getPluginManager().callEvent(getEvent);
    }


    @EventHandler
    public void onInventory(InventoryMoveItemEvent event){
        if (event.getDestination() == event.getSource()) return;
        if (event.getDestination().getType() != InventoryType.PLAYER) return;

        if (event.getDestination().getHolder() instanceof Player player){
           // Bukkit.getPluginManager().callEvent(new PlayerGetItemEvent(player, event.getItem(), event.getItem().getAmount()));
        }

    }
}
