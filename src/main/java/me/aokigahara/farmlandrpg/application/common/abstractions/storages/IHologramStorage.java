package me.aokigahara.farmlandrpg.application.common.abstractions.storages;

import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

public interface IHologramStorage extends IStorage<TextDisplay> {
    TextDisplay get(Location location);
}
