package me.aokigahara.farmlandrpg;

import me.aokigahara.farmlandrpg.application.GameEngine;
import me.aokigahara.farmlandrpg.application.resource.realizations.SimpleCoalResource;
import me.aokigahara.farmlandrpg.application.resource.realizations.SimpleTestOakTree;
import me.aokigahara.farmlandrpg.application.region.model.Bound;
import me.aokigahara.farmlandrpg.application.region.model.Region;
import me.aokigahara.farmlandrpg.application.region.model.RegionPriority;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class TestData {
    public static void enable(){
        var regionService = GameEngine.getInstance().getRegionService();
        var region = new Region();

        var injector = FarmLandRpg.getInjector();
        region.setName(ChatColor.GREEN + "Biiki");
        region.setDescription("Asdf");
        region.setBound(new Bound(new Location(Bukkit.getWorld("world"), 604, 116, -286),
                new Location(Bukkit.getWorld("world"), 660, 97, -337)));
        region.setPriority(RegionPriority.City);

        regionService.addNewRegion(region);


        /////////////////////////////////////////////
        var resourceService = GameEngine.getInstance().getResourceService();


        var resource = new SimpleCoalResource();
        resource.setLocation(new Location(Bukkit.getWorld("world"), 632, 103, -306));

        resourceService.addResource(resource);
        resource.build();


        var testTree = injector.getInstance(SimpleTestOakTree.class);
        testTree.setLocation(new Location(Bukkit.getWorld("world"), 568, 96, -283));
        resourceService.addResource(testTree);
        testTree.build();
    }
}
