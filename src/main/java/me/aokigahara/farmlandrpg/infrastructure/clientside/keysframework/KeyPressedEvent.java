package me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KeyPressedEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean isCanceled = false;

    private final Player player;
    private final Key key;
    private final KeyState state;

    public KeyPressedEvent(Player player, Key key, KeyState state) {
        this.player = player;
        this.key = key;
        this.state = state;
    }


    public Player getPlayer() {
        return player;
    }

    public Key getKey() {
        return key;
    }


    public KeyState getState() {
        return state;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


    @Override
    public boolean isCancelled() {
        return isCanceled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCanceled = b;
    }
}
