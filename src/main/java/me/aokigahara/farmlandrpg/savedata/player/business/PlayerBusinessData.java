package me.aokigahara.farmlandrpg.savedata.player.business;

import me.aokigahara.farmlandrpg.savedata.player.business.buildings.FarmingLandSaveModel;

import java.util.ArrayList;
import java.util.List;

public class PlayerBusinessData {
    private List<FarmingLandSaveModel> farmingLandSaveModels;

    public PlayerBusinessData() {
        farmingLandSaveModels = new ArrayList<>();
    }

    public PlayerBusinessData(List<FarmingLandSaveModel> farmingLandSaveModels) {
        this.farmingLandSaveModels = farmingLandSaveModels;
    }

    public List<FarmingLandSaveModel> getFarmingLandSaveModels() {
        return farmingLandSaveModels;
    }

    public void setFarmingLandSaveModels(List<FarmingLandSaveModel> farmingLandSaveModels) {
        this.farmingLandSaveModels = farmingLandSaveModels;
    }
}
