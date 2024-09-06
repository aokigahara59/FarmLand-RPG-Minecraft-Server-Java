package me.aokigahara.farmlandrpg.application.quest.realizations.phases.common;

import me.aokigahara.farmlandrpg.domain.quest.QuestPhaseBehaviour;
import me.aokigahara.farmlandrpg.domain.quest.progress.CounterPhaseProgress;
import org.bukkit.entity.Player;
import org.bukkit.util.Consumer;

public abstract class CounterQuestPhaseBehaviour extends QuestPhaseBehaviour {
    private final int amount;

    public CounterQuestPhaseBehaviour(String title, String description, int amount) {
        super(title, description, player -> {}, CounterPhaseProgress::new);
        this.amount = amount;
    }

    public CounterQuestPhaseBehaviour(String title, String description, Consumer<Player> playerAction, int amount) {
        super(title, description, playerAction, CounterPhaseProgress::new);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
