package me.aokigahara.farmlandrpg.infrastructure.storages;

import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceHologramsPointersStorage;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;

import java.util.*;

public class InMemoryResourceHologramsPointersStorage implements IResourceHologramsPointersStorage {

    private final HashMap<Resource, List<UUID>> data;

    public InMemoryResourceHologramsPointersStorage() {
        data = new HashMap<>();
    }

    @Override
    public boolean hasHolograms(Resource resource) {
        return data.containsKey(resource);
    }

    @Override
    public List<UUID> getHologramsPointers(Resource resource) {
        if (!data.containsKey(resource)) return Collections.emptyList();

        return data.get(resource);
    }

    @Override
    public void removeHolograms(Resource resource) {
        data.remove(resource);
    }

    @Override
    public void addHolograms(Resource resource, UUID id) {
        var list = new ArrayList<UUID>();
        list.add(id);
        data.put(resource, list);
    }

    @Override
    public void addHolograms(Resource resource, List<UUID> ids) {
        data.put(resource, new ArrayList<>(ids));
    }
}
