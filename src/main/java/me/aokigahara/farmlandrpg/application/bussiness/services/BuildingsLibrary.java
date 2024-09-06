package me.aokigahara.farmlandrpg.application.bussiness.services;

import me.aokigahara.farmlandrpg.application.bussiness.realizations.buildings.SimpleWoodGenerator;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.BaseBuilding;

import java.util.HashMap;

public class BuildingsLibrary {

    private static final HashMap<String, BaseBuilding> data = new HashMap<>();
    public static BaseBuilding getBuilding(String name){
        if (data.isEmpty()){
            data.put(SimpleWoodGenerator.class.getSimpleName(), new SimpleWoodGenerator());
        }

        return data.get(name);
    }
}
