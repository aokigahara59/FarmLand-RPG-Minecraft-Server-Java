package me.aokigahara.farmlandrpg.application.quest.events;

import lombok.Getter;
import me.aokigahara.farmlandrpg.domain.quest.Quest;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class QuestDoneEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Quest quest;

    public QuestDoneEvent(Player player, Quest quest) {
        this.player = player;
        this.quest = quest;
    }

    @NotNull
    @Override
    public  HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
