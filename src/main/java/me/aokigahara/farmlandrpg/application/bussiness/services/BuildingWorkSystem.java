package me.aokigahara.farmlandrpg.application.bussiness.services;


import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.bussiness.events.PlaceForBuildingChangeEvent;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IBuildingWorkSystem;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IFarmingLandService;
import me.aokigahara.farmlandrpg.application.utils.Tuple;
import me.aokigahara.farmlandrpg.application.bussiness.models.Recipe;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.BaseBuilding;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.FarmingLand;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.GeneratorBuilding;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.PlaceForBuilding;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.BaseBuildingSaveModel;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.FarmingLandSaveModel;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.GeneratorBuildingSaveModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BuildingWorkSystem implements IBuildingWorkSystem {

    @Inject private IFarmingLandService farmingLandService;


    @Override
    public FarmingLandSaveModel getLendSaveModel(Player player, FarmingLand land){
        FarmingLandSaveModel saveModel = farmingLandService.get(player, FarmingLandRepository.getId(land));

        if (saveModel == null){
            saveModel = new FarmingLandSaveModel();
            saveModel.setId(FarmingLandRepository.getId(land));
        }

        return saveModel;
    }


    private void saveChanges(Player player, FarmingLandSaveModel farmingLand) {
        farmingLandService.saveData(player, farmingLand);
        farmingLandService.save();
    }

    @Override
    public Tuple<BaseBuilding, BaseBuildingSaveModel> getBuildingByPlace(Player player, FarmingLandSaveModel landSaveModel, PlaceForBuilding place){
        var placeId = FarmingLandRepository.getPlaceId(place);
        var buildingSaveModel = landSaveModel.getBuildings().getOrDefault(placeId, null);

        if (buildingSaveModel == null) return null;

        return new Tuple<>(BuildingsLibrary.getBuilding(buildingSaveModel.getType()), buildingSaveModel);
    }

    @Override
    public void setNewBuilding(Player player, FarmingLandSaveModel landSaveModel, PlaceForBuilding place, BaseBuilding building){
        var placeId = FarmingLandRepository.getPlaceId(place);
        BaseBuildingSaveModel buildingSaveModel = null;
        if (building instanceof GeneratorBuilding generatorBuilding){
            buildingSaveModel = new GeneratorBuildingSaveModel();
            buildingSaveModel.setStorageCap(generatorBuilding.getBaseStorageCap());
            ((GeneratorBuildingSaveModel) buildingSaveModel).setMaxPower(building.getPowerLevel(1));
        }
        buildingSaveModel.setType(building.getClass().getSimpleName());


        landSaveModel.getBuildings().put(placeId, buildingSaveModel);
        saveChanges(player, landSaveModel);
        Bukkit.getServer().getPluginManager().
                callEvent(new PlaceForBuildingChangeEvent(player, place,
                        PlaceForBuildingChangeEvent.ChangeType.NewBuilding));

    }

    @Override
    public void removeBuilding(Player player, FarmingLandSaveModel landSaveModel, PlaceForBuilding place){
        var placeId = FarmingLandRepository.getPlaceId(place);

        landSaveModel.getBuildings().remove(placeId);
        saveChanges(player, landSaveModel);

        Bukkit.getServer().getPluginManager().
                callEvent(new PlaceForBuildingChangeEvent(player, place,
                        PlaceForBuildingChangeEvent.ChangeType.RemoveBuilding));
    }


    @Override
    public void tryAddRecipe(Player player, FarmingLandSaveModel landSaveModel,GeneratorBuildingSaveModel buildingSaveModel, Recipe recipe){
        double power = buildingSaveModel.getMaxPower();

        for (var x : buildingSaveModel.getRecipes()){
            power -= (double)  x .getPower() / ((double) x .getTimeInMils()/1000);
        }

        var recipeCost = (double)  recipe.getPower() / ((double) recipe.getTimeInMils()/1000);

        if (power - recipeCost >= 0){
            buildingSaveModel.getRecipes().add(recipe);
        }
        saveChanges(player, landSaveModel);
    }


    @Override
    public void removeRecipe(Player player, FarmingLandSaveModel landSaveModel,GeneratorBuildingSaveModel buildingSaveModel, Recipe recipe){
        buildingSaveModel.getRecipes().removeIf(x-> x.equals(recipe));
        saveChanges(player, landSaveModel);

    }

    @Override
    public void tryAddItemToStorage(Player player, FarmingLandSaveModel landSaveModel,BaseBuildingSaveModel buildingSaveModel, ItemStack item, int amount){
        var storage = buildingSaveModel.getStorage();

        var oldAmount = storage.getOrDefault(item, 0);
        var possibleToAddAmount = buildingSaveModel.getStorageCap() -
                storage.values().stream().mapToInt(Integer::intValue).sum(); 

        var finalAmount = Integer.min(possibleToAddAmount, amount);

        if (possibleToAddAmount > 0){
            storage.put(item, oldAmount + finalAmount);
        }
        saveChanges(player, landSaveModel);

    }

    @Override
    public void setBuildingLevel(Player player, FarmingLandSaveModel landSaveModel,GeneratorBuildingSaveModel buildingSaveModel, int level){
        buildingSaveModel.setLevel(level);
        buildingSaveModel.setMaxPower(BuildingsLibrary.getBuilding(buildingSaveModel.getType()).getPowerLevel(level));
        saveChanges(player, landSaveModel);

    }

    @Override
    public void removeItemFromStorage(Player player, FarmingLandSaveModel landSaveModel,BaseBuildingSaveModel buildingSaveModel, ItemStack item, int amount){
        var storage = buildingSaveModel.getStorage();

        var oldAmount = storage.getOrDefault(item, 0);

        var amountToGet = Integer.min(oldAmount, amount);

        if (oldAmount > 0){
            if (oldAmount - amountToGet == 0)
                storage.remove(item);
            storage.put(item, oldAmount - amountToGet);
        }
        saveChanges(player, landSaveModel);

    }

    @Override
    public void proceedRecipesForBuilding(Player player, FarmingLandSaveModel landSaveModel,GeneratorBuildingSaveModel buildingSaveModel){
        var timeElapsed = System.currentTimeMillis() - buildingSaveModel.getLastTimeInteractedInSystemMills();

        for (var x : buildingSaveModel.getRecipes()){
            int recipeCraftedAmount = (int) (timeElapsed / x.getTimeInMils());


            for (var z : x.getResults()){
                tryAddItemToStorage(player,landSaveModel,buildingSaveModel, z.getFirst(), recipeCraftedAmount*z.getSecond());
            }
        }

        buildingSaveModel.setLastTimeInteractedInSystemMills(System.currentTimeMillis());
        saveChanges(player, landSaveModel);

    }
}
