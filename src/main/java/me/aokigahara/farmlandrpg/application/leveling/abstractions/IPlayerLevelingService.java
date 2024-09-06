package me.aokigahara.farmlandrpg.application.leveling.abstractions;

import me.aokigahara.farmlandrpg.savedata.player.leveling.ILevelTable;
import org.bukkit.entity.Player;

public interface IPlayerLevelingService {
    void addExp(Player player, int exp, ILevelTable levelTable);
    int getLevel(Player player);
    int getExp(Player player);
    ILevelTable getLevelTable(Player player);
}
