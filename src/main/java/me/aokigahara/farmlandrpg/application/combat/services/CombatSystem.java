package me.aokigahara.farmlandrpg.application.combat.services;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.combat.abstractions.ICombatSystem;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import me.aokigahara.farmlandrpg.application.utils.actionresult.IActionResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CombatSystem implements ICombatSystem {

    private ItemFeaturesHelper itemFeaturesHelper;


    public CombatSystem() {
        itemFeaturesHelper = new ItemFeaturesHelper();
    }

    @Override
    public IActionResult getMana(Player player, int amount) {
        return null;
    }

    @Override
    public IActionResult tryCooldown(Player player, String name) {
        return null;
    }

    @Override
    public void reloadGun(Player player, ItemStack itemStack) {
        var settings = itemFeaturesHelper.getGunSettings(itemStack);

        if (settings.isReloading()) return;

        settings.setReloading(true);
        itemFeaturesHelper.setGunSettings(itemStack, settings);

        Bukkit.getServer().getScheduler().runTaskLater(FarmLandRpg.getInstance(), ()->{
            settings.setClip(settings.getClipSize());
            settings.setReloading(false);
            itemFeaturesHelper.setGunSettings(itemStack, settings);

        }, settings.getReloadTimeMs() / 50);
    }
}
