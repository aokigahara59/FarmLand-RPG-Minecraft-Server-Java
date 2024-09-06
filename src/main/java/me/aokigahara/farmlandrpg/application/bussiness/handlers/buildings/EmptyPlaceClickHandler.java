package me.aokigahara.farmlandrpg.application.bussiness.handlers.buildings;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.bussiness.events.FarmingLandPlaceClick;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IBuildingWorkSystem;
import me.aokigahara.farmlandrpg.application.common.realizations.gui.business.defaults.DefaultNewBuildingPickMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EmptyPlaceClickHandler implements Listener {

    @Inject private IBuildingWorkSystem buildingWorkSystem;


    @EventHandler
    public void onClick(FarmingLandPlaceClick event){
        var place = event.getPlace();
        var player = event.getPlayer();

        var building = buildingWorkSystem.getBuildingByPlace(player,
                buildingWorkSystem.getLendSaveModel(player, place.getLand()), place);

        if (building != null) return;

        new DefaultNewBuildingPickMenu(event).displayTo(player);
    }
}
