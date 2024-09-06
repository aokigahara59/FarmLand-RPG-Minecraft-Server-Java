package me.aokigahara.farmlandrpg.application.combat.events;

import lombok.Getter;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.Key;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CombatCombinationHitEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;
    @Getter
    private final Player player;
    @Getter
    private final List<Key> keys;


    public CombatCombinationHitEvent(Player player, List<Key> keys) {
        this.player = player;
        this.keys = keys;
    }


    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}
