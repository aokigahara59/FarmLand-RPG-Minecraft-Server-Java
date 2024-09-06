package me.aokigahara.farmlandrpg.application.bussiness.abstractions;

import me.aokigahara.farmlandrpg.application.utils.Tuple;
import me.aokigahara.farmlandrpg.application.bussiness.models.Recipe;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.BaseBuilding;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.FarmingLand;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.PlaceForBuilding;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.BaseBuildingSaveModel;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.FarmingLandSaveModel;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.GeneratorBuildingSaveModel;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IBuildingWorkSystem {
    FarmingLandSaveModel getLendSaveModel(Player player, FarmingLand land);

    Tuple<BaseBuilding, BaseBuildingSaveModel> getBuildingByPlace(Player player, FarmingLandSaveModel landSaveModel, PlaceForBuilding place);

    void setNewBuilding(Player player, FarmingLandSaveModel landSaveModel, PlaceForBuilding place, BaseBuilding building);

    void removeBuilding(Player player, FarmingLandSaveModel landSaveModel, PlaceForBuilding place);

    void tryAddRecipe(Player player, FarmingLandSaveModel landSaveModel,GeneratorBuildingSaveModel buildingSaveModel, Recipe recipe);

    void setBuildingLevel(Player player, FarmingLandSaveModel landSaveModel,GeneratorBuildingSaveModel buildingSaveModel, int level);

    void tryAddItemToStorage(Player player, FarmingLandSaveModel landSaveModel,BaseBuildingSaveModel buildingSaveModel, ItemStack item, int amount);

    void proceedRecipesForBuilding(Player player, FarmingLandSaveModel landSaveModel,GeneratorBuildingSaveModel buildingSaveModel);
    void removeItemFromStorage(Player player, FarmingLandSaveModel landSaveModel,BaseBuildingSaveModel buildingSaveModel, ItemStack item, int amount);
    void removeRecipe(Player player, FarmingLandSaveModel landSaveModel,GeneratorBuildingSaveModel buildingSaveModel, Recipe recipe);
}
