package me.aokigahara.farmlandrpg.application.bussiness.abstractions;

import me.aokigahara.farmlandrpg.savedata.player.business.buildings.FarmingLandSaveModel;
import org.bukkit.entity.Player;

import java.util.List;

public interface IFarmingLandService {
    void saveData(Player player, FarmingLandSaveModel landSaveModel);
    FarmingLandSaveModel get(Player player, int id);
    List<FarmingLandSaveModel> getAll(Player player);
    void remove(Player player, int id);
    void save();
}
