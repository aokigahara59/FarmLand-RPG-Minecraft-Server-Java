package me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DefaultMenuWorkHandler implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        var player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (!player.hasMetadata("DefaultMenuInformation")) return;
        event.setCancelled(true);

        DefaultMenu menu = (DefaultMenu) player.getMetadata("DefaultMenuInformation").get(0).value();

        for (var x : menu.getButtons()){
            if (x.getSlot() == slot){
                x.onClick(player);
            }
        }
    }


    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        deleteMenu((Player) event.getPlayer());
    }

    @EventHandler
    public void onGameLeave(PlayerQuitEvent event){
        deleteMenu(event.getPlayer());
    }


    private void deleteMenu(Player player){
        player.removeMetadata("DefaultMenuInformation", FarmLandRpg.getInstance());
    }
}
