package me.aokigahara.farmlandrpg.infrastructure.storages;

import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IFPlayerStorage;
import me.aokigahara.farmlandrpg.savedata.player.FPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InMemoryFPlayerStorage implements IFPlayerStorage {

    private final List<FPlayer> data;

    public InMemoryFPlayerStorage() {
        data = new ArrayList<>();
    }

    @Override
    public FPlayer get(Player player) {
        var optionalPlayer = data.stream().filter(x -> x.getPlayerUUID().equals(player.getUniqueId())).findFirst();
        if (optionalPlayer.isPresent()) return optionalPlayer.get();
        else {
            var fplayer = FPlayer.create(player);
            add(fplayer);
            return fplayer;
        }
    }

    @Override
    public void add(FPlayer player) {
        data.add(player);
    }

    @Override
    public boolean contains(FPlayer player) {
        return data.contains(player);
    }

    @Override
    public List<FPlayer> getAll() {
        return data;
    }

    @Override
    public void remove(FPlayer player) {
        data.remove(player);
    }

    @Override
    public void remove(Player player) {
        data.remove(get(player));
    }

    @Override
    public void save() {
    }
}
