package me.aokigahara.farmlandrpg.application.bussiness.models.buildings;

import me.aokigahara.farmlandrpg.application.region.model.Bound;

public class PlaceForBuilding {
    private Bound bound;
    private FarmingLand land;

    public PlaceForBuilding(Bound bound, FarmingLand land) {
        this.bound = bound;
        this.land = land;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    public void setLand(FarmingLand land) {
        this.land = land;
    }

    public Bound getBound() {
        return bound;
    }

    public FarmingLand getLand() {
        return land;
    }
}
