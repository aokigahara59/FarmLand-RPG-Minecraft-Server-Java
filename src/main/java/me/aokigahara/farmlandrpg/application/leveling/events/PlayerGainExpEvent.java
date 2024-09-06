package me.aokigahara.farmlandrpg.application.leveling.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerGainExpEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;
    private final Player player;

    private int baseExp;
    private int percentage;

    public PlayerGainExpEvent(Player player, int baseExp) {
        this.player = player;
        this.baseExp = baseExp;
    }

    public PlayerGainExpEvent(Player player, int baseExp, int percentage) {
        this.player = player;
        this.baseExp = baseExp;
        this.percentage = percentage;
    }

    public int getFinalExp(){
        return (int) (baseExp + (baseExp* (double)percentage/100d));
    }

    public Player getPlayer() {
        return player;
    }

    public int getBaseExp() {
        return baseExp;
    }

    public void setBaseExp(int baseExp) {
        this.baseExp = baseExp;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
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
