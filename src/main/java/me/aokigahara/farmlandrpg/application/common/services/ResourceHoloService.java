package me.aokigahara.farmlandrpg.application.common.services;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IHologramService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceHologramService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceHologramsPointersStorage;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import org.bukkit.Bukkit;
import org.bukkit.entity.TextDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResourceHoloService implements IResourceHologramService {

    @Inject
    private IResourceHologramsPointersStorage hologramsPointersStorage;
    @Inject private IHologramService hologramService;

    @Override
    public boolean hasHolograms(Resource resource) {
        return hologramsPointersStorage.hasHolograms(resource);
    }

    @Override
    public List<TextDisplay> getHolograms(Resource resource) {
        var ids = hologramsPointersStorage.getHologramsPointers(resource);
        List<TextDisplay> result = new ArrayList<>();
        for (var id : ids){
            result.add((TextDisplay) Bukkit.getServer().getEntity(id));
        }

        return result;
    }

    @Override
    public void addHologram(Resource resource, TextDisplay item) {
        hologramService.addHologram(item);
        hologramsPointersStorage.addHolograms(resource, item.getUniqueId());
    }

    @Override
    public void addHologram(Resource resource, List<TextDisplay> items) {
        List<UUID> ids = new ArrayList<>();
        for (var x : items){
            hologramService.addHologram(x);
            ids.add(x.getUniqueId());
        }
        hologramsPointersStorage.addHolograms(resource, ids);
    }

    @Override
    public void deleteHologram(Resource resource) {
        if (resource == null) return;

        List<TextDisplay> holograms = getHolograms(resource);
        if (holograms == null) return;
        for (var x : holograms) {
            hologramService.deleteHologram(x);
        }
        hologramsPointersStorage.removeHolograms(resource);
    }

}
