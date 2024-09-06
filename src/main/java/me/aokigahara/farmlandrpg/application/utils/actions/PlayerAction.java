package me.aokigahara.farmlandrpg.application.utils.actions;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface PlayerAction {
    void execute(Player player);
}
