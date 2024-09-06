package me.aokigahara.farmlandrpg.application.region.events;

import me.aokigahara.farmlandrpg.application.region.model.Region;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

public class RegionSwapEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final List<Region> regions;

    public RegionSwapEvent(Player player, List<Region> regions) {
        this.player = player;
        this.regions = regions;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public Player getPlayer() {
        return player;
    }
}
