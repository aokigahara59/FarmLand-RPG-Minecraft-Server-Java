package me.aokigahara.farmlandrpg.application.common.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerUpdateMoneyEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final long newBalance;

    public PlayerUpdateMoneyEvent(Player player, long newBalance) {
        this.player = player;
        this.newBalance = newBalance;
    }

    public Player getPlayer() {
        return player;
    }

    public long getNewBalance() {
        return newBalance;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
