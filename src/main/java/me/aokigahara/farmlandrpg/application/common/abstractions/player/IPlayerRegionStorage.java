package me.aokigahara.farmlandrpg.application.common.abstractions.player;

import me.aokigahara.farmlandrpg.application.region.model.Region;
import org.bukkit.entity.Player;

import java.util.List;

public interface IPlayerRegionStorage {

    List<Region> getPlayerRegions(Player player);
    void add(Player player, Region region);
    void remove(Player player, Region region);
    boolean containsPlayer(Player player);
    boolean containsRegion(Player player, Region region);
}
