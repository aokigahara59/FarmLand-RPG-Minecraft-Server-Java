package me.aokigahara.farmlandrpg.application.resource.services;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceStorage;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.List;

public class ResourceService implements IResourceService {

    @Inject private IResourceStorage resourceStorage;

    private boolean isResource(Block block){
        List<Material> materials = List.of(Material.COAL_ORE);
        return materials.contains(block.getType());
    }

    @Override
    public Resource getResource(Block block) {
        for (var x: resourceStorage.getAll()) {
            if (x.getAllBlockList().contains(block)){
                return x;
            }
        }
        return null;
    }

    @Override
    public void addResource(Resource resource) {
        resourceStorage.add(resource);
    }

    @Override
    public void removeResource(Resource resource) {
        resource.remove();
        resourceStorage.delete(resource);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceStorage.getAll();
    }
}
