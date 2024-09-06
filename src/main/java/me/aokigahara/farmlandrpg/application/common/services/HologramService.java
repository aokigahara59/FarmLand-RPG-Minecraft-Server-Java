package me.aokigahara.farmlandrpg.application.common.services;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IHologramService;
import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IHologramStorage;
import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

import java.util.List;
import java.util.Objects;

public class HologramService implements IHologramService {

    @Inject private IHologramStorage hologramStorage;

    @Override
    public TextDisplay getByLocation(Location location) {
        return hologramStorage.get(location);
    }

    @Override
    public TextDisplay getNearestInLocation(Location location, int radius) {
        try {
            var entities = Objects.requireNonNull(location.getWorld()).getNearbyEntities(location, radius, radius, radius);

            for(var x : entities){
                if (x instanceof TextDisplay){
                    return (TextDisplay) x;
                }
            }
            return null;
        }
        catch (NullPointerException ex){
            return null;
        }
    }

    @Override
    public void addHologram(TextDisplay item) {
        hologramStorage.add(item);
    }

    @Override
    public void deleteHologram(TextDisplay item) {
        hologramStorage.delete(item);
        item.remove();
    }

    @Override
    public void deleteAllHolograms() {
        for (var x : hologramStorage.getAll()) {
            hologramStorage.delete(x);
            x.remove();
        }
    }

    @Override
    public boolean isHologram(TextDisplay item) {
        return hologramStorage.contains(item);
    }

    @Override
    public List<TextDisplay> getAllHologram() {
        return hologramStorage.getAll();
    }
}
