package me.aokigahara.farmlandrpg.infrastructure.eventhandlers.questtabworkes;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestTabQuestData implements Serializable {
    public String title;

    public ArrayList<QuestTabQuestPhase> phases = new ArrayList<>();

    public static class QuestTabQuestPhase implements Serializable{
        public String line = "";
        public String secondLine = "";
        public boolean isDone = false;

        public QuestTabQuestPhase(String line, String secondLine, boolean isDone) {
            this.line = line;
            this.secondLine = secondLine;
            this.isDone = isDone;
        }

        public QuestTabQuestPhase(String line, boolean isDone) {
            this.line = line;
            this.isDone = isDone;
        }

        public QuestTabQuestPhase() {
        }
    }
}
