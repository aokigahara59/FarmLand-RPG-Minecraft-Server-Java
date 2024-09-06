package me.aokigahara.farmlandrpg.application.combat.abstractions;

import me.aokigahara.farmlandrpg.application.utils.actionresult.IActionResult;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ICombatSystem {
    IActionResult getMana(Player player, int amount);
    IActionResult tryCooldown(Player player, String name);

    void reloadGun(Player player, ItemStack itemStack);

}
