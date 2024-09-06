package me.aokigahara.farmlandrpg.infrastructure.eventhandlers.questtabworkes;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.ICrossplatformDataManager;
import me.aokigahara.farmlandrpg.application.quest.abstractions.IQuestService;
import me.aokigahara.farmlandrpg.application.quest.events.PlayerUpdateQuestsEvent;
import me.aokigahara.farmlandrpg.application.quest.realizations.phases.common.CounterQuestPhaseBehaviour;
import me.aokigahara.farmlandrpg.domain.quest.progress.CounterPhaseProgress;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class QuestTabInfoUpdateHandler implements Listener {

    @Inject private IQuestService questService;
    @Inject private ICrossplatformDataManager dataManager;

    @EventHandler
    public void onQuestUpdate(PlayerUpdateQuestsEvent event){
        var player = event.getPlayer();

        if (questService.getAll(player).isEmpty()){
            dataManager.removeData(player, "quest_tab_data");
            return;
        }

        var quest = event.getQuest();

        var data = new QuestTabQuestData();
        data.title = quest.getQuestBehaviour().getTitle();

        for (var x : quest.getCurrentPhases()){

            var line = x.getPhaseBehaviour().getTitle();
            var secondLine = "";

            if (x.getPhaseBehaviour() instanceof CounterQuestPhaseBehaviour counterQuestPhase){
                secondLine = ((CounterPhaseProgress)(x.getPhaseProgress())).getCurrent()
                        + " из " + counterQuestPhase.getAmount();
            }

            data.phases.add(new QuestTabQuestData.QuestTabQuestPhase(line,secondLine, x.isDone()));
        }

        dataManager.setData(player, "quest_tab_data", data);
    }
}
