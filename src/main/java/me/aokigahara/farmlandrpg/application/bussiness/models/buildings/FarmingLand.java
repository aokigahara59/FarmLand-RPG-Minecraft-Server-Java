package me.aokigahara.farmlandrpg.application.bussiness.models.buildings;

import me.aokigahara.farmlandrpg.application.region.model.Bound;
import me.aokigahara.farmlandrpg.application.region.model.Region;
import me.aokigahara.farmlandrpg.application.region.model.RegionPriority;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public abstract class FarmingLand {
    public abstract String getName();
    public abstract String getDescription();

    public abstract Bound getBound();

    public Region getRegion() {
        return region;
    }

    private final Region region;

    public List<PlaceForBuilding> places = new ArrayList<>();

    protected void addPlace(PlaceForBuilding place){
        places.add(place);
    }

    public FarmingLand() {
        region = new Region();
        region.setBound(getBound());
        region.setName(ChatColor.GREEN + getName());
        region.setDescription(getDescription());
        region.setSafe(true);
        region.setPriority(RegionPriority.FarmLand);


    }
}
