package me.aokigahara.farmlandrpg.application.combat.events;

import lombok.Getter;
import me.aokigahara.farmlandrpg.application.combat.models.GunSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ShootEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;

    @Getter
    private final Player player;
    @Getter
    private final ItemStack gun;
    @Getter
    private final GunSettings gunSettings;

    public ShootEvent(Player player, ItemStack gun, GunSettings gunSettings) {
        this.player = player;
        this.gun = gun;
        this.gunSettings = gunSettings;
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
