package me.aokigahara.farmlandrpg.application.bussiness.events;

import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.PlaceForBuilding;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FarmingLandPlaceClick extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final PlaceForBuilding place;

    public FarmingLandPlaceClick(Player player, PlaceForBuilding place) {
        this.player = player;
        this.place = place;
    }



    public Player getPlayer() {
        return player;
    }

    public PlaceForBuilding getPlace() {
        return place;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
