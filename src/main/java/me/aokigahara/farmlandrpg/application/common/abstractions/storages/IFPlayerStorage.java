package me.aokigahara.farmlandrpg.application.common.abstractions.storages;

import me.aokigahara.farmlandrpg.savedata.player.FPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface IFPlayerStorage {
    FPlayer get(Player player);
    void add(FPlayer player);
    boolean contains(FPlayer player);
    List<FPlayer> getAll();

    void remove(FPlayer player);
    void remove(Player player);

    void save();
}
