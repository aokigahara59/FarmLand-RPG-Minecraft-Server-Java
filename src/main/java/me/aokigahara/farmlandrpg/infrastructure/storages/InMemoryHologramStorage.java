package me.aokigahara.farmlandrpg.infrastructure.storages;

import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IHologramStorage;
import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

import java.util.HashMap;
import java.util.List;

public class InMemoryHologramStorage implements IHologramStorage {

    private final HashMap<Location, TextDisplay> data = new HashMap<>();

    @Override
    public void add(TextDisplay item) {
        data.put(item.getLocation(), item);
    }

    @Override
    public void delete(TextDisplay item) {
        data.remove(item.getLocation());
    }

    @Override
    public List<TextDisplay> getAll() {
        return data.values().stream().toList();
    }

    @Override
    public boolean contains(TextDisplay item) {
        return data.containsKey(item.getLocation());
    }

    @Override
    public TextDisplay get(Location location) {
        if (!data.containsKey(location)) return null;

        return data.get(location);
    }
}
