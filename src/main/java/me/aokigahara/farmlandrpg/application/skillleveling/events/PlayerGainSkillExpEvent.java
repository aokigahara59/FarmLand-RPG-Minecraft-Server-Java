package me.aokigahara.farmlandrpg.application.skillleveling.events;

import me.aokigahara.farmlandrpg.savedata.player.leveling.SkillType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerGainSkillExpEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;
    private final Player player;
    private final SkillType skillType;

    private int baseExp;
    private int percentage;

    public PlayerGainSkillExpEvent(Player player, SkillType skillType, int baseExp) {
        this.player = player;
        this.skillType = skillType;
        this.baseExp = baseExp;
    }

    public PlayerGainSkillExpEvent(Player player,SkillType skillType, int baseExp, int percentage) {
        this.player = player;
        this.skillType = skillType;
        this.baseExp = baseExp;
        this.percentage = percentage;
    }

    public SkillType getSkillType() {
        return skillType;
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
