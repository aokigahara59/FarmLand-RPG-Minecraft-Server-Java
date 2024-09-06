package me.aokigahara.farmlandrpg.application.bussiness.handlers.buildings;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.bussiness.events.FarmingLandPlaceClick;
import me.aokigahara.farmlandrpg.application.bussiness.services.FarmingLandRepository;
import me.aokigahara.farmlandrpg.application.region.abstractions.IRegionService;
import me.aokigahara.farmlandrpg.application.region.model.RegionPriority;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlaceForBuildingClickHandleEvent implements Listener {

    @Inject private IRegionService regionService;

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) return;

        var player = event.getPlayer();

        var region = regionService.getPlayerRegions(player).stream()
                .filter(x -> x.getPriority().equals(RegionPriority.FarmLand)).findFirst();
        if (region.isEmpty()) return;



        var farmingLand = FarmingLandRepository.getAll().stream().
                filter(x -> x.getRegion().equals(region.get())).findFirst();

        if (farmingLand.isEmpty()) return;


        var place = farmingLand.get().places.stream().filter(x -> x.getBound().
                isWithinBounds(event.getClickedBlock().getLocation())).findFirst();

        if (place.isEmpty()) return;



        Bukkit.getServer().getPluginManager().callEvent(new FarmingLandPlaceClick(player, place.get()));
    }
}
