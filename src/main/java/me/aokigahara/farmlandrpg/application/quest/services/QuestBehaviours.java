package me.aokigahara.farmlandrpg.application.quest.services;


import me.aokigahara.farmlandrpg.application.quest.realizations.TestQuestBehaviour;
import me.aokigahara.farmlandrpg.domain.quest.QuestBehaviour;

import java.util.HashMap;

public enum QuestBehaviours {
    TestQuestBehaviour(new TestQuestBehaviour());


    private static HashMap<String, QuestBehaviour> data;
    private final QuestBehaviour questBehaviour;

    QuestBehaviours(QuestBehaviour questBehaviour) {
        this.questBehaviour = questBehaviour;
        addQuest(questBehaviour);
    }

    public static String getId(QuestBehaviour questBehaviour) {
        return data.entrySet().stream().filter(x -> x.getValue().equals(questBehaviour)).findFirst().get().getKey();
    }

    public QuestBehaviour getQuest() {
        return questBehaviour;
    }

    public void addQuest(QuestBehaviour questBehaviour){
        if (data == null) data = new HashMap<>();
        data.put(questBehaviour.getClass().getSimpleName(), questBehaviour);
    }

    public static QuestBehaviour getById(String id){
        return data.get(id);
    }
}
