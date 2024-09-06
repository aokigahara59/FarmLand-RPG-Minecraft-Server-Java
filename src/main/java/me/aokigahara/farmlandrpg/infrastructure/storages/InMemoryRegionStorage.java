package me.aokigahara.farmlandrpg.infrastructure.storages;

import me.aokigahara.farmlandrpg.application.region.abstractions.IRegionStorage;
import me.aokigahara.farmlandrpg.application.region.model.Region;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRegionStorage implements IRegionStorage {

    private final List<Region> regions;

    public InMemoryRegionStorage() {
        this.regions = new ArrayList<>();
    }

    @Override
    public void add(Region item) {
        regions.add(item);
    }

    @Override
    public void delete(Region item) {
        regions.remove(item);
    }

    @Override
    public List<Region> getAll() {
        return regions;
    }

    @Override
    public boolean contains(Region item) {
        return regions.contains(item);
    }
}
