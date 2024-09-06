package me.aokigahara.farmlandrpg.savedata.player.quest;

import me.aokigahara.farmlandrpg.domain.quest.progress.PhaseProgress;

public class QuestPhaseSaveModel {
    private PhaseProgress progress = new PhaseProgress();
    private boolean done = false;

    public PhaseProgress getProgress() {
        return progress;
    }

    public void setProgress(PhaseProgress progress) {
        this.progress = progress;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
