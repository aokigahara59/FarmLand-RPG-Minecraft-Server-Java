package me.aokigahara.farmlandrpg.application.region.abstractions;

import me.aokigahara.farmlandrpg.application.region.model.Region;
import org.bukkit.entity.Player;

import java.util.List;

public interface IRegionService {
    void updateRegions(Player player);
    List<Region> getPlayerRegions(Player player);
    void addNewRegion(Region region);

    List<Region> getAllRegions();
}
