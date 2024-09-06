package me.aokigahara.farmlandrpg.application.combat.abstractions;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IGunshotEffect {
    void makeShootEffects(Player player, Location hitLocation);
}
