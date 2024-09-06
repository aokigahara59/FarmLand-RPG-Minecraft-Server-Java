package me.aokigahara.farmlandrpg.application.item.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerGetItemEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final ItemStack item;
    private final int amount;
    private final GotItemType type;


    public PlayerGetItemEvent(final Player player, final ItemStack item, final int amount, GotItemType type) {
        this.item = item;
        this.amount = amount;
        this.player = player;
        this.type = type;


    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }


    public enum GotItemType{
        Crafted,
        Gained,
        PickedUp,
        Gifted,
        Mined
    }
}
