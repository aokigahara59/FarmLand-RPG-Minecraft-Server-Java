package me.aokigahara.farmlandrpg.application.quest.realizations.phases;

import lombok.Getter;
import me.aokigahara.farmlandrpg.application.quest.realizations.phases.common.CounterQuestPhaseBehaviour;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import org.bukkit.entity.Player;
import org.bukkit.util.Consumer;

@Getter
public class BreakResourcePhaseBehaviour extends CounterQuestPhaseBehaviour {
    private final int amount;
    private final Class<? extends Resource> resource;


    public BreakResourcePhaseBehaviour(String title, String description, int amount, Class<? extends Resource> resourceClass, Consumer<Player> playerAction) {
        super(title, description, playerAction, amount);
        this.amount = amount;
        resource = resourceClass;
    }

}
