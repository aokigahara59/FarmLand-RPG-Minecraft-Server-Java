package me.aokigahara.farmlandrpg.savedata.player.business.buildings;

import java.util.HashMap;

public class FarmingLandSaveModel {
    private int id;
    private HashMap<Integer, BaseBuildingSaveModel> buildings;

    public FarmingLandSaveModel() {
        buildings = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, BaseBuildingSaveModel> getBuildings() {
        return buildings;
    }

    public void setBuildings(HashMap<Integer, BaseBuildingSaveModel> buildings) {
        this.buildings = buildings;
    }
}
