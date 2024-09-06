package me.aokigahara.farmlandrpg.domain.quest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class QuestBehaviour {

    private HashMap<Integer, List<QuestPhaseBehaviour>> phases;

    public abstract String getTitle();

    public abstract String getDescription();
    public abstract boolean repeatable();

    public QuestBehaviour() {
        phases = new HashMap<>();
    }

    public HashMap<Integer, List<QuestPhaseBehaviour>> getPhasesBehaviour() {
        return phases;
    }

    public List<QuestPhaseBehaviour> getPhasesBehaviour(int phase) {
        return phases.get(phase);
    }

    protected void addQuestPhaseBehaviour(int phase, QuestPhaseBehaviour questPhaseBehaviour){
        if (!phases.containsKey(phase)) phases.put(phase, new ArrayList<>());

        phases.get(phase).add(questPhaseBehaviour);
    }
}
