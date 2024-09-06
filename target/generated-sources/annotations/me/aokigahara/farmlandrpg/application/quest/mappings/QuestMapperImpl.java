package me.aokigahara.farmlandrpg.application.quest.mappings;

import javax.annotation.processing.Generated;
import me.aokigahara.farmlandrpg.domain.quest.Quest;
import me.aokigahara.farmlandrpg.savedata.player.quest.QuestSaveModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-19T21:18:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class QuestMapperImpl implements QuestMapper {

    @Override
    public Quest toQuest(QuestSaveModel saveModel) {
        if ( saveModel == null ) {
            return null;
        }

        Quest quest = new Quest();

        quest.setCurrentPhase( saveModel.getCurrentPhase() );
        quest.setDone( saveModel.isDone() );

        quest.setPhases( mapPhases(saveModel.getPhases(), saveModel.getId()) );

        setBehaviour( quest, saveModel );

        return quest;
    }

    @Override
    public QuestSaveModel toQuestSaveModel(Quest quest) {
        if ( quest == null ) {
            return null;
        }

        QuestSaveModel questSaveModel = new QuestSaveModel();

        questSaveModel.setCurrentPhase( quest.getCurrentPhase() );
        questSaveModel.setDone( quest.isDone() );

        questSaveModel.setId( quest.getQuestBehaviour().getClass().getSimpleName() );
        questSaveModel.setPhases( mapSavePhases(quest.getPhases()) );

        return questSaveModel;
    }
}
