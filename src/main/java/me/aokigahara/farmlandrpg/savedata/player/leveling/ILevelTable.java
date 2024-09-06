package me.aokigahara.farmlandrpg.savedata.player.leveling;

public interface ILevelTable {

    Integer getRequiredExpForLevel(int level);

    boolean enoughFor(int level, int exp);
}
