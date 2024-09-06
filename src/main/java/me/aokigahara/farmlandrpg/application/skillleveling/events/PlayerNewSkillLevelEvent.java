package me.aokigahara.farmlandrpg.application.skillleveling.events;

import me.aokigahara.farmlandrpg.savedata.player.leveling.SkillType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerNewSkillLevelEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;
    private final Player player;
    private final SkillType skillType;
    private final int level;


    public PlayerNewSkillLevelEvent(Player player, SkillType skillType, int level) {
        this.player = player;
        this.skillType = skillType;
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public int getLevel() {
        return level;
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
