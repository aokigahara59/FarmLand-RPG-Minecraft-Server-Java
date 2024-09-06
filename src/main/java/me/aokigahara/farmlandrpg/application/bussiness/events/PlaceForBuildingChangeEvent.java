package me.aokigahara.farmlandrpg.application.bussiness.events;

import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.PlaceForBuilding;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlaceForBuildingChangeEvent  extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final PlaceForBuilding place;
    private final ChangeType changeType;


    public PlaceForBuildingChangeEvent(Player player, PlaceForBuilding place, ChangeType changeType) {
        this.player = player;
        this.place = place;
        this.changeType = changeType;
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

    public ChangeType getChangeType() {
        return changeType;
    }


    public enum ChangeType {
        NewBuilding,
        RemoveBuilding,
        NewBuildingLevel
    }
}
