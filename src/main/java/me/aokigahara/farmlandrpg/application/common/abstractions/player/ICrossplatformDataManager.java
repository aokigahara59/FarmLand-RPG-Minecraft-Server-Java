package me.aokigahara.farmlandrpg.application.common.abstractions.player;

import org.bukkit.entity.Player;

public interface ICrossplatformDataManager {

    void setData(Player player, String name,  Object value);

    <T> T geData(Player player, String value, Class<T> clazz);

    boolean hasData(Player player,  String value);

    void removeData(Player player,  String value);
}
