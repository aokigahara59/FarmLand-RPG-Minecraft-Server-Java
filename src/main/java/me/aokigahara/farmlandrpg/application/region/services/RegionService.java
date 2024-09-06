package me.aokigahara.farmlandrpg.application.region.services;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.IPlayerRegionStorage;
import me.aokigahara.farmlandrpg.application.region.abstractions.IRegionService;
import me.aokigahara.farmlandrpg.application.region.abstractions.IRegionStorage;
import me.aokigahara.farmlandrpg.application.region.events.RegionSwapEvent;
import me.aokigahara.farmlandrpg.application.region.model.Region;
import me.aokigahara.farmlandrpg.application.region.model.RegionPriority;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RegionService implements IRegionService {

    @Inject private IRegionStorage regionStorage;
    @Inject private IPlayerRegionStorage playerRegionStorage;

    private final Region defaultRegion;

    public RegionService() {
        defaultRegion = new Region();
        defaultRegion.setName(ChatColor.GRAY + "Земли FarmLend`а");
        defaultRegion.setDescription("...");
        defaultRegion.setBound(null);
        defaultRegion.setPriority(RegionPriority.Default);
    }


    @Override
    public void updateRegions(Player player) {
        var regions = regionStorage.getAll();
        var playerRegions = playerRegionStorage.getPlayerRegions(player);
        List<Region> oldRegions = new ArrayList<>();
        oldRegions.addAll(playerRegions);

        for (var region : regions){
            if (!region.getBound().isWithinBounds(player.getLocation())) playerRegions.remove(region);
            else {
                if (!playerRegions.contains(region)) playerRegions.add(region);
            }
        }

        if (!playerRegions.contains(defaultRegion)){
            playerRegions.add(defaultRegion);
        }

        playerRegions.sort((x, y) -> Integer.compare(y.getPriority().getValue(), x.getPriority().getValue()));
        if (!new HashSet<>(playerRegions).equals(new HashSet<>(oldRegions))) Bukkit.getServer().getPluginManager().callEvent(new RegionSwapEvent(player, playerRegions));
    }

    @Override
    public List<Region> getPlayerRegions(Player player) {
        return playerRegionStorage.getPlayerRegions(player);
    }

    @Override
    public void addNewRegion(Region region) {
        regionStorage.add(region);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionStorage.getAll();
    }
}
