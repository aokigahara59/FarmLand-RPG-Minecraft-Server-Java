package me.aokigahara.farmlandrpg.application.resource.abstractions;

import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import org.bukkit.block.Block;

import java.util.List;

public interface IResourceService {
    Resource getResource(Block block);
    void addResource(Resource resource);
    void removeResource(Resource resource);
    List<Resource> getAllResources();
}
