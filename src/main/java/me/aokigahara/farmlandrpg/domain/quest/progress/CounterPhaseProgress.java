package me.aokigahara.farmlandrpg.domain.quest.progress;

public class CounterPhaseProgress extends PhaseProgress {
    private int current;

    public CounterPhaseProgress() {
    }

    public CounterPhaseProgress(int current) {
        this.current = current;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Counter_"+current;
    }
}
