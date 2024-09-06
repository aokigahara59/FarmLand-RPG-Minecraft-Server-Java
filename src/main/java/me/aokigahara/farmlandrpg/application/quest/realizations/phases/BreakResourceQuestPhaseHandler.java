package me.aokigahara.farmlandrpg.application.quest.realizations.phases;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.quest.abstractions.IQuestService;
import me.aokigahara.farmlandrpg.application.quest.events.PlayerUpdateQuestsEvent;
import me.aokigahara.farmlandrpg.application.quest.events.QuestDoneEvent;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import me.aokigahara.farmlandrpg.application.utils.DualKeyMap;
import me.aokigahara.farmlandrpg.domain.quest.Quest;
import me.aokigahara.farmlandrpg.domain.quest.QuestPhase;
import me.aokigahara.farmlandrpg.domain.quest.QuestPhaseBehaviour;
import me.aokigahara.farmlandrpg.domain.quest.progress.CounterPhaseProgress;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class BreakResourceQuestPhaseHandler implements Listener {

    @SuppressWarnings("all")
    @Inject private IQuestService questService;

    private final DualKeyMap<Player, Class<? extends Resource>, Map<Quest, QuestPhase>> table;

    public BreakResourceQuestPhaseHandler() {
        table = new DualKeyMap<>();
    }


    public void updateTableForPlayer(Player player){
        table.remove(player);

        var quests = questService.getAll(player);

        for (var quest : quests){
            for (var i = 0; i < quest.getCurrentPhases().size(); i++){
                QuestPhaseBehaviour phaseBehaviour = quest
                        .getQuestBehaviour()
                        .getPhasesBehaviour(quest.getCurrentPhase())
                        .get(i);

                if (phaseBehaviour instanceof BreakResourcePhaseBehaviour phase){
                    var map = table.get(player, phase.getResource());
                    if (map == null) map = new HashMap<>();

                    map.put(quest, quest.getCurrentPhases().get(i));

                    table.put(player, phase.getResource(), map);
                }
            }
        }
    }



    @EventHandler
    public void onResourceBreak(ResourceBreakEvent event){
        var player = event.getPlayer();


        var values = table.get(player, event.getResource().getClass());
        if (values == null) return;



        var mainQuest = questService.getMainQuest(player);

        Quest quest ;
        QuestPhase stage ;

        if (values.containsKey(mainQuest)){
            quest = mainQuest;
            stage = values.get(mainQuest);
        }
        else {
            var iterator = values.entrySet().iterator();
            var first = iterator.next();
            quest = first.getKey();
            stage = first.getValue();
        }


        if (!(stage.getPhaseProgress() instanceof CounterPhaseProgress progress)) return;
        if (!(stage.getPhaseBehaviour() instanceof BreakResourcePhaseBehaviour behaviour)) return;

        var currentCount = progress.getCurrent();
        currentCount++;
        var finalCount = behaviour.getAmount();
        currentCount = Math.min(currentCount, finalCount);
        progress.setCurrent(currentCount);
        if (currentCount == finalCount){
            progress.setDone(true);
        }

        questService.updatePhase(player, quest, stage);
    }








    @EventHandler
    public void addData(PlayerUpdateQuestsEvent event){

        updateTableForPlayer(event.getPlayer());
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event){ updateTableForPlayer(event.getPlayer());}

    @EventHandler
    public void onLeave(PlayerQuitEvent event){ updateTableForPlayer(event.getPlayer());}

    @EventHandler
    public void onDone(QuestDoneEvent event){ updateTableForPlayer(event.getPlayer());}

}
