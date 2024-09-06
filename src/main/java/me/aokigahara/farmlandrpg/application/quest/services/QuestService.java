package me.aokigahara.farmlandrpg.application.quest.services;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IFPlayerStorage;
import me.aokigahara.farmlandrpg.application.quest.abstractions.IQuestService;
import me.aokigahara.farmlandrpg.application.quest.events.PlayerUpdateQuestsEvent;
import me.aokigahara.farmlandrpg.application.quest.events.QuestDoneEvent;
import me.aokigahara.farmlandrpg.application.quest.mappings.QuestMapper;
import me.aokigahara.farmlandrpg.application.utils.actionresult.IActionResult;
import me.aokigahara.farmlandrpg.application.utils.actionresult.QuestDoneResult;
import me.aokigahara.farmlandrpg.application.utils.actionresult.Success;
import me.aokigahara.farmlandrpg.domain.quest.Quest;
import me.aokigahara.farmlandrpg.domain.quest.QuestBehaviour;
import me.aokigahara.farmlandrpg.domain.quest.QuestPhase;
import me.aokigahara.farmlandrpg.savedata.player.quest.PlayerQuestData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class QuestService implements IQuestService {

    @Inject private IFPlayerStorage playerStorage;

    public void saveData(Player player, Quest quest) {
        var data = playerStorage.get(player).getQuestData();

        if (quest.isDone()){
            completeQuest(player, quest.getQuestBehaviour());

            if (data.getMainQuestId().equals(QuestBehaviours.getId(quest.getQuestBehaviour()))){
                if (data.getQuests().isEmpty()){
                    data.setMainQuestId(null);
                } else {
                    data.setMainQuestId(data.getQuests().get(0).getId());
                }
            }

            Bukkit.getServer().getPluginManager().callEvent(new PlayerUpdateQuestsEvent(player, quest));
            Bukkit.getServer().getPluginManager().callEvent(new QuestDoneEvent(player, quest));
            playerStorage.save();
            return;
        }

        var saveModel = QuestMapper.INSTANCE.toQuestSaveModel(quest);

        var quests = data.getQuests();
        var savedModel = quests.stream()
                .filter(x -> Objects.equals(x.getId(), QuestBehaviours.getId(quest.getQuestBehaviour())))
                .findFirst();

        if (savedModel.isPresent()) {
            savedModel.get().setCurrentPhase(quest.getCurrentPhase());
            savedModel.get().setPhases(savedModel.get().getPhases());
        } else {
            quests.add(saveModel);
        }

        if (data.getMainQuestId() == null){
            setMainId(data, quest);
        }

        playerStorage.save();
        Bukkit.getServer().getPluginManager().callEvent(new PlayerUpdateQuestsEvent(player, quest));
    }

    @Override
    public IActionResult execute(Player player, QuestBehaviour questBehaviour) {
        Quest quest = getByBehaviour(player, questBehaviour); // поиск
        if (quest == null){
            quest = generateQuest(questBehaviour, 0);
            setMainId(playerStorage.get(player).getQuestData(), quest);
        }

        if (quest.getCurrentPhases().stream().allMatch(QuestPhase::isDone)){
            quest = generateQuest(questBehaviour, quest.getCurrentPhase()+1);
        }

        if (quest.isDone()) {
            saveData(player, quest);
            return new QuestDoneResult();
        }

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {});

        for (var x : questBehaviour.getPhasesBehaviour(quest.getCurrentPhase())){
            future = future.thenRunAsync(()->x.getRunnable().accept(player));
        }


        saveData(player, quest);

        return new Success("Quest executed.");
    }

    @Override
    public Quest getByBehaviour(Player player, QuestBehaviour questBehaviour) {
        var saveModel = playerStorage.get(player).getQuestData()
                .getQuests().stream().
                filter(x -> x.getId().equals(questBehaviour.getClass().getSimpleName()))
                .findFirst().orElse(null);

        return QuestMapper.INSTANCE.toQuest(saveModel);
    }

    @Override
    public List<Quest> getAll(Player player) {
        if (playerStorage.get(player).getQuestData().getQuests().isEmpty()) return new ArrayList<>();
        return QuestMapper.INSTANCE.toQuestList(playerStorage.get(player).getQuestData().getQuests());
    }

    @Override
    public List<String> getCompleted(Player player) {
        return playerStorage.get(player).getQuestData().getCompletedQuests();
    }



    @Override
    public Quest getMainQuest(Player player) {
        var id = playerStorage.get(player).getQuestData().getMainQuestId();
        var behaviour = QuestBehaviours.getById(id);
        return getByBehaviour(player, behaviour);
    }

    @Override
    public void removeQuest(Player player, QuestBehaviour behaviour) {
        playerStorage.get(player).getQuestData()
                .getQuests()
                .removeIf(x -> x.getId().equals(QuestBehaviours.getId(behaviour)));


        playerStorage.save();
    }

    @Override
    public void completeQuest(Player player, QuestBehaviour behaviour) {
        if (!getCompleted(player).contains(QuestBehaviours.getId(behaviour)))
            getCompleted(player).add(QuestBehaviours.getId(behaviour));

        removeQuest(player, behaviour);
    }

    @Override
    public void updatePhase(Player player, Quest quest, QuestPhase phase) {
        var optionalPhase = quest.getCurrentPhases()
                .stream()
                .filter(x -> x.getPhaseBehaviour() == phase.getPhaseBehaviour())
                .findFirst();

        if (optionalPhase.isEmpty()) return;
        var currentPhase = optionalPhase.get();
        currentPhase.setDone(phase.isDone());
        currentPhase.setPhaseProgress(phase.getPhaseProgress());

        saveData(player, quest);
    }

    public void setMainId(PlayerQuestData questData, Quest quest){
        questData.setMainQuestId(QuestBehaviours.getId(quest.getQuestBehaviour()));
    }

    public Quest generateQuest(QuestBehaviour questBehaviour, int phase) {
        var result = new Quest();
        result.setCurrentPhase(phase);
        result.setQuestBehaviour(questBehaviour);

        if (questBehaviour.getPhasesBehaviour(phase) == null){
            result.setDone(true);
            return result;
        }

        List<QuestPhase> phases = new ArrayList<>();
        for (int i = 0; i < questBehaviour.getPhasesBehaviour().get(phase).size(); i++){
            var questPhase = new QuestPhase(
                    questBehaviour.getPhasesBehaviour(phase).get(i),
                    questBehaviour.getPhasesBehaviour(phase).get(i).getProgressFactory().get()
            );
            phases.add(questPhase);
        }
        result.setPhases(phase, phases);

        return result;
    }
}
