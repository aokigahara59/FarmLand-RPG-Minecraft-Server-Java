package me.aokigahara.farmlandrpg.savedata.player.leveling;

import java.util.HashMap;

public class DefaultLevelTable implements ILevelTable {

    private final HashMap<Integer, Integer> table;


    public DefaultLevelTable() {
        table = new HashMap<>();

        table.put(2, 100);
        table.put(3, 300);
        table.put(4, 600);
        table.put(5, 1000);
        table.put(6, 1400);
    }

    @Override
    public Integer getRequiredExpForLevel(int level) {
        return table.get(level);
    }

    @Override
    public boolean enoughFor(int level, int exp) {
        if (!table.containsKey(level)) return false;
        return table.get(level) <= exp;
    }
}
