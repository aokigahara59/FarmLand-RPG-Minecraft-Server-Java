package me.aokigahara.farmlandrpg.application.bussiness.handlers.buildings;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.bussiness.events.FarmingLandPlaceClick;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IBuildingWorkSystem;
import me.aokigahara.farmlandrpg.application.common.realizations.gui.business.defaults.DefaultBuildingMenu;
import me.aokigahara.farmlandrpg.application.common.realizations.gui.business.defaults.DefaultGeneratorBuildingMenu;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.GeneratorBuilding;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.GeneratorBuildingSaveModel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OpenPlaceMenuHandler implements Listener {

    @Inject private IBuildingWorkSystem buildingWorkSystem;


    @EventHandler
    public void onClick(FarmingLandPlaceClick event){
        var place = event.getPlace();
        var player = event.getPlayer();
        var land = buildingWorkSystem.getLendSaveModel(player, place.getLand());
        var buildingTuple = buildingWorkSystem.getBuildingByPlace(player, land, place);

        if (buildingTuple == null) return;
        if (buildingTuple.getFirst() instanceof GeneratorBuilding){
            buildingWorkSystem.proceedRecipesForBuilding(player,land,(GeneratorBuildingSaveModel) buildingTuple.getSecond());

            new DefaultGeneratorBuildingMenu((GeneratorBuilding) buildingTuple.getFirst(),
                    (GeneratorBuildingSaveModel) buildingTuple.getSecond(), place.getLand(), place).displayTo(player);
        } else new DefaultBuildingMenu(buildingTuple.getFirst(), buildingTuple.getSecond()).displayTo(player);
    }

}
