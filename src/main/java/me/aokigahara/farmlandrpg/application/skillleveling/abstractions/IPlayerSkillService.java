package me.aokigahara.farmlandrpg.application.skillleveling.abstractions;

import me.aokigahara.farmlandrpg.savedata.player.leveling.ILevelTable;
import me.aokigahara.farmlandrpg.savedata.player.leveling.SkillType;
import org.bukkit.entity.Player;

public interface IPlayerSkillService {
    void addExp(Player player, SkillType skillType, int exp, ILevelTable levelTable);
    int getLevel(Player player, SkillType skillType);
    int getExp(Player player, SkillType skillType);
    ILevelTable getSkillLevelTable(Player player, SkillType skillType);
}
