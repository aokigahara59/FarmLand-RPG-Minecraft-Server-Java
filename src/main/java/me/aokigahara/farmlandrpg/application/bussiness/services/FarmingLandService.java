package me.aokigahara.farmlandrpg.application.bussiness.services;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IFPlayerStorage;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IFarmingLandService;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.FarmingLandSaveModel;
import org.bukkit.entity.Player;

import java.util.List;

public class FarmingLandService implements IFarmingLandService {

    @Inject private IFPlayerStorage playerStorage;

    @Override
    public void saveData(Player player, FarmingLandSaveModel landSaveModel) {
        var data = playerStorage.get(player).getBusinessData();

        var land = get(player, landSaveModel.getId());
        if (land != null){
            land.setBuildings(landSaveModel.getBuildings());
        } else {
            data.getFarmingLandSaveModels().add(landSaveModel);
        }
    }

    @Override
    public FarmingLandSaveModel get(Player player, int id) {
        return playerStorage.get(player).getBusinessData().getFarmingLandSaveModels()
                .stream().filter(x-> x.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<FarmingLandSaveModel> getAll(Player player) {
        return playerStorage.get(player).getBusinessData().getFarmingLandSaveModels();
    }

    @Override
    public void remove(Player player, int id) {
        playerStorage.get(player).getBusinessData().getFarmingLandSaveModels().remove(get(player, id));
    }

    @Override
    public void save() {
        playerStorage.save();
    }
}
