package me.aokigahara.farmlandrpg.application.bussiness.models.buildings;

import me.aokigahara.farmlandrpg.application.bussiness.models.Requirement;

public abstract class BaseBuilding {
    public abstract String getName();
    public abstract int getPrice();
    public abstract int getBaseStorageCap();
    public abstract BuildingType getType();
    public abstract Requirement getRequirements(int level);

    public abstract int getPowerLevel(int level);


    public enum BuildingType{
        Generator,
    }
}
