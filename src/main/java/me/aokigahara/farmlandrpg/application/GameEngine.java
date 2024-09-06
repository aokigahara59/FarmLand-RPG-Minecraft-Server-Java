package me.aokigahara.farmlandrpg.application;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.IHologramService;
import me.aokigahara.farmlandrpg.application.region.abstractions.IRegionService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final IRegionService regionService;
    private final IResourceService resourceService;



    private final IHologramService hologramService;
    private static GameEngine instance;

    public static GameEngine getInstance() {
        if (instance == null){
            var services = FarmLandRpg.getInjector();

            instance = new GameEngine(services.getInstance(IRegionService.class),
                    services.getInstance(IResourceService.class),
                    services.getInstance(IHologramService.class));
        }

        return instance;
    }


    private GameEngine(IRegionService regionService, IResourceService resourceService, IHologramService hologramService) {
        this.regionService = regionService;

        this.resourceService = resourceService;
        this.hologramService = hologramService;
    }

    public IHologramService getHologramService() {
        return hologramService;
    }

    public IRegionService getRegionService() {
        return regionService;
    }

    public IResourceService getResourceService() {
        return resourceService;
    }

    public void stoutDown() {
        if (resourceService.getAllResources() != null){
            List<Resource> recourseListCopy = new ArrayList<>(resourceService.getAllResources());
            recourseListCopy.forEach(resourceService::removeResource);
        }

        if (hologramService.getAllHologram() != null)
            hologramService.deleteAllHolograms();
    }
}
