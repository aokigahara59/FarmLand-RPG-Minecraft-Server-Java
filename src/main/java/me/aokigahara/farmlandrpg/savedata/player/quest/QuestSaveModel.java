package me.aokigahara.farmlandrpg.savedata.player.quest;

import java.util.HashMap;
import java.util.List;

public class QuestSaveModel {
    private int currentPhase;
    private HashMap<Integer, List<QuestPhaseSaveModel>> phases;
    private boolean isDone;
    private String id;

    public QuestSaveModel() {
        phases = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(int currentPhase) {
        this.currentPhase = currentPhase;
    }

    public HashMap<Integer, List<QuestPhaseSaveModel>> getPhases() {
        return phases;
    }

    public void setPhases(HashMap<Integer, List<QuestPhaseSaveModel>> phases) {
        this.phases = phases;
    }

    public void setPhases(int phase, List<QuestPhaseSaveModel> phaseSaveModels){
        phases.put(phase, phaseSaveModels);
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
