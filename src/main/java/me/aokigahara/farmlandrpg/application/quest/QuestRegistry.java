package me.aokigahara.farmlandrpg.application.quest;

import me.aokigahara.farmlandrpg.application.quest.handlers.QuestDoneEffectsHandler;
import me.aokigahara.farmlandrpg.application.quest.realizations.phases.BreakResourceQuestPhaseHandler;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;

public class QuestRegistry extends AbstractRegistry {
    public void register(){
        registerEvent(BreakResourceQuestPhaseHandler.class);
        registerEvent(QuestDoneEffectsHandler.class);
    }
}

