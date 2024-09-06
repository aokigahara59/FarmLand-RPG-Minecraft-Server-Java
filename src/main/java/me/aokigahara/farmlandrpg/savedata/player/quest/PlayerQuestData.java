package me.aokigahara.farmlandrpg.savedata.player.quest;

import java.util.ArrayList;
import java.util.List;

public class PlayerQuestData {
    private List<QuestSaveModel> quests;
    private List<String> completedQuests;
    private String mainQuestId;

    public PlayerQuestData() {
        quests = new ArrayList<>();
        completedQuests = new ArrayList<>();
        mainQuestId = null;
    }

    public PlayerQuestData(List<QuestSaveModel> quests, List<String> completedQuests, String mainQuestId) {
        this.quests = quests;
        this.completedQuests = completedQuests;
        this.mainQuestId = mainQuestId;
    }

    public List<QuestSaveModel> getQuests() {
        return quests;
    }

    public void setQuests(List<QuestSaveModel> quests) {
        this.quests = quests;
    }

    public List<String> getCompletedQuests() {
        return completedQuests;
    }

    public void setCompletedQuests(List<String> completedQuests) {
        this.completedQuests = completedQuests;
    }

    public String getMainQuestId() {
        return mainQuestId;
    }

    public void setMainQuestId(String mainQuestId) {
        this.mainQuestId = mainQuestId;
    }
}
