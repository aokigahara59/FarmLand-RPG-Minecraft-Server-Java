package me.aokigahara.farmlandrpg.domain.quest.progress;

public class PhaseProgress {
    private boolean isDone = false;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
