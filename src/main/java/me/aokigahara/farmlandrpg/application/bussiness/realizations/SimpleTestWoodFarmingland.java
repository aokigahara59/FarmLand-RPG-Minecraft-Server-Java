package me.aokigahara.farmlandrpg.application.bussiness.realizations;

import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.FarmingLand;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.PlaceForBuilding;
import me.aokigahara.farmlandrpg.application.region.model.Bound;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SimpleTestWoodFarmingland extends FarmingLand {
    @Override
    public String getName() {
        return "Захолустье";
    }

    @Override
    public String getDescription() {
        return "Ну и дыра...";
    }

    @Override
    public Bound getBound() {
        var world = Bukkit.getWorld("world");
        return new Bound(new Location(world, 578, 95, -342),
                new Location(world, 543, 130, -310));
    }

    public SimpleTestWoodFarmingland() {
        var world = Bukkit.getWorld("world");
        addPlace(new PlaceForBuilding(new Bound(new Location(world, 576, 96, -312), new Location(world, 572, 101, -317)) , this));
        addPlace(new PlaceForBuilding(new Bound(new Location(world, 568, 96, -312), new Location(world, 562, 105, -317)) , this));
        addPlace(new PlaceForBuilding(new Bound(new Location(world, 572, 96, -328), new Location(world, 567, 100, -321)) , this));
        addPlace(new PlaceForBuilding(new Bound(new Location(world, 563, 96, -329), new Location(world, 552, 107, -321)) , this));

    }
}
