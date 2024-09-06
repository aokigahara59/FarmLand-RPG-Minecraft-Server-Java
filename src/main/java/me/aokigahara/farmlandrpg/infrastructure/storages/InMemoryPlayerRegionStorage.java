package me.aokigahara.farmlandrpg.infrastructure.storages;

import me.aokigahara.farmlandrpg.application.common.abstractions.player.IPlayerRegionStorage;
import me.aokigahara.farmlandrpg.application.region.model.Region;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryPlayerRegionStorage implements IPlayerRegionStorage {

    private final HashMap<Player, List<Region>> data;

    public InMemoryPlayerRegionStorage() {
        this.data = new HashMap<>();
    }

    @Override
    public List<Region> getPlayerRegions(Player player) {
        if (!data.containsKey(player)){
            data.put(player, new ArrayList<>());
        }
        return data.get(player);
    }

    @Override
    public void add(Player player, Region region) {
        if (!containsPlayer(player)) data.put(player, new ArrayList<>());
        data.get(player).add(region);
    }

    @Override
    public void remove(Player player, Region region) {
        data.get(player).remove(region);
    }

    @Override
    public boolean containsPlayer(Player player) {
        return data.containsKey(player);
    }

    @Override
    public boolean containsRegion(Player player, Region region) {
        return data.get(player).contains(region);
    }
}
