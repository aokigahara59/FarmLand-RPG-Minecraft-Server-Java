package me.aokigahara.farmlandrpg.domain.quest;

import me.aokigahara.farmlandrpg.domain.quest.progress.PhaseProgress;

public class QuestPhase {
    private QuestPhaseBehaviour phaseBehaviour;
    private PhaseProgress phaseProgress;

    public QuestPhase(QuestPhaseBehaviour phaseBehaviour, PhaseProgress phaseProgress) {
        this.phaseBehaviour = phaseBehaviour;
        this.phaseProgress = phaseProgress;
    }

    public PhaseProgress getPhaseProgress() {
        return phaseProgress;
    }

    public void setPhaseProgress(PhaseProgress phaseProgress) {
        this.phaseProgress = phaseProgress;
    }

    public boolean isDone() {
        return phaseProgress.isDone();
    }

    public void setDone(boolean done) {
        phaseProgress.setDone(done);
    }

    public QuestPhaseBehaviour getPhaseBehaviour() {
        return phaseBehaviour;
    }

    public void setPhaseBehaviour(QuestPhaseBehaviour phaseBehaviour) {
        this.phaseBehaviour = phaseBehaviour;
    }
}
