package me.aokigahara.farmlandrpg.application.common.events;

import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.List;

public class HologramPreSpawnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private List<TextDisplay> hologramList = new ArrayList<>();
    private Location location;
    private Resource resource;

    public HologramPreSpawnEvent(Location location, Resource resource) {
        this.location = location;
        this.resource = resource;
    }

    public Location getNewLineLocation(){
        return location.add(0, 0.5, 0);
    }

    public Resource getResource() {
        return resource;
    }

    public void addHologram(TextDisplay textDisplay){
        hologramList.add(textDisplay);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void deleteHologram(TextDisplay textDisplay){
        hologramList.remove(textDisplay);
    }

    public List<TextDisplay> getHologramList() {
        return hologramList;
    }

    public Location getLocation() {
        return location;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
