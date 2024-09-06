package me.aokigahara.farmlandrpg.application.bussiness;

import me.aokigahara.farmlandrpg.application.bussiness.handlers.buildings.EmptyPlaceClickHandler;
import me.aokigahara.farmlandrpg.application.bussiness.handlers.buildings.OpenPlaceMenuHandler;
import me.aokigahara.farmlandrpg.application.bussiness.handlers.buildings.PlaceForBuildingClickHandleEvent;
import me.aokigahara.farmlandrpg.application.bussiness.services.FarmingLandRepository;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;
import org.bukkit.Bukkit;

public class BusinessRegistry extends AbstractRegistry {
    public void register(){
        registerEvent(PlaceForBuildingClickHandleEvent.class);
        registerEvent(EmptyPlaceClickHandler.class);
        registerEvent(OpenPlaceMenuHandler.class);


        // Initializing all in repo cause each 'x' creating region and enums working like lazy loading
        FarmingLandRepository.getAll().forEach(x -> Bukkit.getLogger().warning(x.getName()));
    }
}
