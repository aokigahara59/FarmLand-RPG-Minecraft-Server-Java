package me.aokigahara.farmlandrpg.application.common.abstractions;

import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

import java.util.List;

public interface IHologramService {

    TextDisplay getByLocation(Location location);

    TextDisplay getNearestInLocation(Location location, int radius);

    void addHologram(TextDisplay item);
    void deleteHologram(TextDisplay item);
    void deleteAllHolograms();
    boolean isHologram(TextDisplay item);
    List<TextDisplay> getAllHologram();
}
