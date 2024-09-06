package me.aokigahara.farmlandrpg.application.resource.abstractions;

import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import org.bukkit.entity.TextDisplay;

import java.util.List;

public interface IResourceHologramService {
    boolean hasHolograms(Resource resource);

    List<TextDisplay> getHolograms(Resource resource);

    void addHologram(Resource resource, TextDisplay item);

    void addHologram(Resource resource, List<TextDisplay> items);

    void deleteHologram(Resource resource);
}
