package me.aokigahara.farmlandrpg.application.resource.events;

import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;

public class ResourceBreakEvent extends BlockBreakEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Resource resource;

    public ResourceBreakEvent(Block theBlock, Player player, Resource resource) {
        super(theBlock, player);
        this.resource = resource;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Resource getResource() {
        return resource;
    }
}
