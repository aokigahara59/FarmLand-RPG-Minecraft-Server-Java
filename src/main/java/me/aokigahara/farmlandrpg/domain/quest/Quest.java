package me.aokigahara.farmlandrpg.domain.quest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Quest {
    private QuestBehaviour questBehaviour;
    private int currentPhase;
    private boolean isDone;
    private HashMap<Integer, List<QuestPhase>> phases;

    public Quest() {
        phases = new HashMap<>();
    }

    public Quest(QuestBehaviour questBehaviour, int currentPhase, boolean isDone, HashMap<Integer, List<QuestPhase>> phases) {
        this.questBehaviour = questBehaviour;
        this.currentPhase = currentPhase;
        this.isDone = isDone;
        this.phases = phases;
    }

    public HashMap<Integer, List<QuestPhase>> getPhases() {
        return phases;
    }

    public void setPhases(Integer phase, List<QuestPhase> phases){
        this.phases.put(phase, phases);
    }

    public void setPhases(HashMap<Integer, List<QuestPhase>> phases) {
        this.phases = phases;
    }

    public List<QuestPhase> getCurrentPhases(){
        return phases.computeIfAbsent(currentPhase, ArrayList::new);
    }

    public List<QuestPhase> getPhases(int phase){
        return phases.get(phase);
    }

    public QuestBehaviour getQuestBehaviour() {
        return questBehaviour;
    }

    public void setQuestBehaviour(QuestBehaviour questBehaviour) {
        this.questBehaviour = questBehaviour;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(int currentPhase) {
        this.currentPhase = currentPhase;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
