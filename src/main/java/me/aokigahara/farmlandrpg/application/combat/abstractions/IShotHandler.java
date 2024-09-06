package me.aokigahara.farmlandrpg.application.combat.abstractions;

import me.aokigahara.farmlandrpg.application.combat.models.GunSettings;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IShotHandler {
    void shoot(Player player, ItemStack gun, GunSettings settings);
}
