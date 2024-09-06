package me.aokigahara.farmlandrpg.application.bussiness.services;

import me.aokigahara.farmlandrpg.application.GameEngine;
import me.aokigahara.farmlandrpg.application.bussiness.realizations.SimpleTestWoodFarmingland;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.FarmingLand;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.PlaceForBuilding;

import java.util.Collection;
import java.util.HashMap;

public enum FarmingLandRepository {
    SimpleTestWoodFarmingLand(new SimpleTestWoodFarmingland()),
    ;

    private static HashMap<Integer, FarmingLand> data;
    private final FarmingLand farmingLand;

    FarmingLandRepository(FarmingLand farmingLand) {
        this.farmingLand = farmingLand;
        addFarmingLand(farmingLand);
    }

    public static Collection<FarmingLand> getAll() {
        return data.values();
    }

    public void addFarmingLand(FarmingLand farmingLand){
        if (data == null) data = new HashMap<>();
        data.put(data.keySet().size()+1, farmingLand);
        GameEngine.getInstance().getRegionService().addNewRegion(farmingLand.getRegion());
    }

    public FarmingLand getFarmingLand() {
        return farmingLand;
    }


    public static int getId(FarmingLand farmingLand) {
        return data.entrySet().stream().filter(x -> x.getValue().equals(farmingLand)).findFirst().get().getKey();
    }

    public static int getPlaceId(PlaceForBuilding place){
        return place.getLand().places.indexOf(place);
    }

    public static PlaceForBuilding getPlaceById(int farmingLandId,int placeId){
        return getById(farmingLandId).places.get(placeId);
    }
    public static FarmingLand getById(int id){
        return data.get(id);
    }
}
