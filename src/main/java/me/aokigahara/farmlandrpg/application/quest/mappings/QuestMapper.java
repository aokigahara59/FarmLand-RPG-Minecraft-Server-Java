package me.aokigahara.farmlandrpg.application.quest.mappings;

import me.aokigahara.farmlandrpg.application.quest.services.QuestBehaviours;
import me.aokigahara.farmlandrpg.domain.quest.Quest;
import me.aokigahara.farmlandrpg.domain.quest.QuestBehaviour;
import me.aokigahara.farmlandrpg.domain.quest.QuestPhase;
import me.aokigahara.farmlandrpg.savedata.player.quest.QuestPhaseSaveModel;
import me.aokigahara.farmlandrpg.savedata.player.quest.QuestSaveModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface QuestMapper {
    QuestMapper INSTANCE = Mappers.getMapper(QuestMapper.class);

    @Mapping(target = "questBehaviour", ignore = true)
    @Mapping(target = "phases", expression = "java(mapPhases(saveModel.getPhases(), saveModel.getId()))")
    Quest toQuest(QuestSaveModel saveModel);

    @Mapping(target = "id", expression = "java(quest.getQuestBehaviour().getClass().getSimpleName())")
    @Mapping(target = "phases", expression = "java(mapSavePhases(quest.getPhases()))")
    QuestSaveModel toQuestSaveModel(Quest quest);

    default HashMap<Integer, List<QuestPhase>> mapPhases(
            Map<Integer, List<QuestPhaseSaveModel>> questSaveModels,
            String questId) {
        QuestBehaviour behaviour = QuestBehaviours.getById(questId);
        HashMap<Integer, List<QuestPhase>> phases = new HashMap<>();
        questSaveModels.forEach((phaseNumber, phaseSaveModelList) -> {
            List<QuestPhase> phaseList = new ArrayList<>();
            for (int i = 0; i < phaseSaveModelList.size(); i++) {
                QuestPhaseSaveModel saveModel = phaseSaveModelList.get(i);
                QuestPhase phase = new QuestPhase(
                        behaviour.getPhasesBehaviour().get(phaseNumber).get(i),
                        saveModel.getProgress()
                );
                phaseList.add(phase);
            }
            phases.put(phaseNumber, phaseList);
        });
        return phases;
    }

    default HashMap<Integer, List<QuestPhaseSaveModel>> mapSavePhases(Map<Integer, List<QuestPhase>> phases) {
        HashMap<Integer, List<QuestPhaseSaveModel>> saveModels = new HashMap<>();

        phases.forEach((phaseNumber, questPhases) -> {
            List<QuestPhaseSaveModel> currentSaveModels = new ArrayList<>();
            for (int i = 0; i < phases.size(); i++) {
                var x = questPhases.get(i);

                QuestPhaseSaveModel questSaveModel = new QuestPhaseSaveModel();
                questSaveModel.setDone(x.isDone());
                questSaveModel.setProgress(x.getPhaseProgress());

                currentSaveModels.add(questSaveModel);
            }
            saveModels.put(phaseNumber, currentSaveModels);
        });

        return saveModels;
    }

    @AfterMapping
    default void setBehaviour(@MappingTarget Quest quest, QuestSaveModel saveModel) {
        quest.setQuestBehaviour(QuestBehaviours.getById(saveModel.getId()));
    }

    default List<Quest> toQuestList(List<QuestSaveModel> saveModelList) {
        return saveModelList.stream()
                .map(this::toQuest)
                .collect(Collectors.toList());
    }
}
