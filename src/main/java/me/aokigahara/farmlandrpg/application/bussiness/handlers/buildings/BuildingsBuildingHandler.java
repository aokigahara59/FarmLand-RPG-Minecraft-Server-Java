package me.aokigahara.farmlandrpg.application.bussiness.handlers.buildings;

import me.aokigahara.farmlandrpg.application.bussiness.events.PlaceForBuildingChangeEvent;
import me.aokigahara.farmlandrpg.application.common.generators.builder.TestBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BuildingsBuildingHandler implements Listener {

    private TestBuilder builder;

    public BuildingsBuildingHandler() {
        this.builder = new TestBuilder();
    }

    @EventHandler
    public void onBuild(PlaceForBuildingChangeEvent event){
        if (event.getChangeType().equals(PlaceForBuildingChangeEvent.ChangeType.NewBuilding)){
            builder.buildStructure(event.getPlace().getBound(), event.getPlayer());
        }

        if (event.getChangeType().equals(PlaceForBuildingChangeEvent.ChangeType.RemoveBuilding)){
            builder.destroyStructure(event.getPlace().getBound(), event.getPlayer());
            builder.buildHollowSquare(event.getPlace().getBound(), event.getPlayer());
        }
    }
}
