package me.aokigahara.farmlandrpg.application.combat.events;

import lombok.Getter;
import me.aokigahara.farmlandrpg.application.utils.Tuple;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerHitEntityEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;


    @Getter
    private final Player player;
    @Getter
    private final List<Tuple<LivingEntity, Double>> entitiesWithDamage;


    public PlayerHitEntityEvent(Player player, List<Tuple<LivingEntity, Double>> entitiesWithDamage) {
        this.player = player;
        this.entitiesWithDamage = entitiesWithDamage;
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
