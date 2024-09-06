package me.aokigahara.farmlandrpg.application.combat.handlers;

import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ReloadSwitchPreventHandler implements Listener {

    public final ItemFeaturesHelper featuresHelper;

    public ReloadSwitchPreventHandler() {
        featuresHelper = new ItemFeaturesHelper();
    }

    @EventHandler
    public void onItemSwitch(PlayerItemHeldEvent event){
        var player = event.getPlayer();
        var item = player.getInventory().getItem(event.getPreviousSlot());
        if (item == null || item.getType() == Material.AIR) return;
        var type = featuresHelper.getItemType(item);
        if (type == null) return;
        if (!type.equals(ItemType.Gun)) return;
        if (featuresHelper.getGunSettings(item).isReloading()) event.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        var player = event.getPlayer();
        var item = event.getItemDrop().getItemStack();
        if (item == null || item.getType() == Material.AIR) return;
        var type = featuresHelper.getItemType(item);
        if (type == null) return;
        if (!type.equals(ItemType.Gun)) return;
        if (featuresHelper.getGunSettings(item).isReloading()) event.setCancelled(true);
    }
}
