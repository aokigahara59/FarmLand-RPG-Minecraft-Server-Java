package me.aokigahara.farmlandrpg.domain.quest;

import me.aokigahara.farmlandrpg.domain.quest.progress.PhaseProgress;
import org.bukkit.entity.Player;
import org.bukkit.util.Consumer;

import java.util.function.Supplier;

public abstract class QuestPhaseBehaviour {
    private String title;
    private String description;
    private Consumer<Player> playerAction;
    private Supplier<PhaseProgress> progressFactory;

    public QuestPhaseBehaviour(String title, String description) {
        this.title = title;
        this.description = description;
        playerAction = player -> {};
        progressFactory = PhaseProgress::new;
    }

    public QuestPhaseBehaviour(String title, String description, Consumer<Player> playerAction, Supplier<PhaseProgress> progressFactory) {
        this.title = title;
        this.description = description;
        this.playerAction = playerAction;
        this.progressFactory = progressFactory;
    }

    public Supplier<PhaseProgress> getProgressFactory() {
        return progressFactory;
    }

    public Consumer<Player> getRunnable(){
        return playerAction;
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
