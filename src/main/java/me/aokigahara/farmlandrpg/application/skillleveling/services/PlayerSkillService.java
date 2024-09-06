package me.aokigahara.farmlandrpg.application.skillleveling.services;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IFPlayerStorage;
import me.aokigahara.farmlandrpg.savedata.player.leveling.ILevelTable;
import me.aokigahara.farmlandrpg.application.skillleveling.abstractions.IPlayerSkillService;
import me.aokigahara.farmlandrpg.application.skillleveling.events.PlayerGainSkillExpEvent;
import me.aokigahara.farmlandrpg.application.skillleveling.events.PlayerNewSkillLevelEvent;
import me.aokigahara.farmlandrpg.savedata.player.leveling.SkillType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerSkillService implements IPlayerSkillService {

    @Inject private IFPlayerStorage playerStorage;

    @Override
    public void addExp(Player player, SkillType skillType, int exp, ILevelTable levelTable) {
        var levelModel = playerStorage.get(player).getSkillLevel(skillType);

        var event = new PlayerGainSkillExpEvent(player, skillType, exp);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (event.isCancelled()) return;

        var level = levelModel.getLevel();
        var newLevel = level;
        var currentExp = levelModel.getExp();

        currentExp += event.getFinalExp();

        while (levelTable.enoughFor(newLevel+1, currentExp)){
            newLevel++;
            if (levelTable.getRequiredExpForLevel(newLevel) != null)
                currentExp -= levelTable.getRequiredExpForLevel(newLevel);
        }

        levelModel.setExp(currentExp);

        if (newLevel != level){
            var newLevelEvent = new PlayerNewSkillLevelEvent(player, skillType, newLevel);
            Bukkit.getServer().getPluginManager().callEvent(newLevelEvent);
            if (!event.isCancelled()){
                levelModel.setLevel(newLevel);
            }
        }


        playerStorage.save();
    }

    @Override
    public int getLevel(Player player, SkillType skillType) {
        return playerStorage.get(player).getSkillLevel(skillType).getLevel();
    }

    @Override
    public int getExp(Player player, SkillType skillType) {
        return playerStorage.get(player).getSkillLevel(skillType).getExp();
    }

    @Override
    public ILevelTable getSkillLevelTable(Player player, SkillType skillType) {
        return playerStorage.get(player).getSkillLevelTable(skillType);
    }
}
