package me.aokigahara.farmlandrpg.application.leveling.tables;

import me.aokigahara.farmlandrpg.savedata.player.leveling.DefaultLevelTable;
import me.aokigahara.farmlandrpg.savedata.player.leveling.ILevelTable;

public class LevelTables {

    private static DefaultLevelTable defaultTable;



    public static ILevelTable getDefaultTable(){
        if (defaultTable == null){
            defaultTable = new DefaultLevelTable();
        }

        return defaultTable;
    }
}
