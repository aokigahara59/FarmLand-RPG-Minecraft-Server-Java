package me.aokigahara.farmlandrpg.application.quest.abstractions;

import me.aokigahara.farmlandrpg.application.utils.actionresult.IActionResult;
import me.aokigahara.farmlandrpg.domain.quest.Quest;
import me.aokigahara.farmlandrpg.domain.quest.QuestBehaviour;
import me.aokigahara.farmlandrpg.domain.quest.QuestPhase;
import org.bukkit.entity.Player;

import java.util.List;

public interface IQuestService {
    IActionResult execute(Player player, QuestBehaviour questBehaviour);
    Quest getByBehaviour(Player player, QuestBehaviour questBehaviour);
    List<Quest> getAll(Player player);
    List<String> getCompleted(Player player);
    Quest getMainQuest(Player player);
    void removeQuest(Player player, QuestBehaviour behaviour);
    void completeQuest(Player player, QuestBehaviour behaviour);
    void updatePhase(Player player, Quest quest, QuestPhase phase);
}