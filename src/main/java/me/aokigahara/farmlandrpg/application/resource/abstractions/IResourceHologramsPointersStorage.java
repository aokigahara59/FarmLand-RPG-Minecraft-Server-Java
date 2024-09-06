package me.aokigahara.farmlandrpg.application.resource.abstractions;

import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;

import java.util.List;
import java.util.UUID;

public interface IResourceHologramsPointersStorage {
    boolean hasHolograms(Resource resource);

    List<UUID> getHologramsPointers(Resource resource);

    void removeHolograms(Resource resource);

    void addHolograms(Resource resource, UUID id);

    void addHolograms(Resource resource, List<UUID> ids);
}
