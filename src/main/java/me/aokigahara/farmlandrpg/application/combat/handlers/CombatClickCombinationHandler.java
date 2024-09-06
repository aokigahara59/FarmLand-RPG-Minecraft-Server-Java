package me.aokigahara.farmlandrpg.application.combat.handlers;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.combat.events.CombatCombinationHitEvent;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.Key;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.KeyPressedEvent;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.KeyState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombatClickCombinationHandler implements Listener {

    private Map<Player, List<Key>> buffer;

    public CombatClickCombinationHandler() {
        buffer = new HashMap<>();
    }

    @EventHandler
    public void onClick(KeyPressedEvent event){
        if (event.getState() == KeyState.Released) return;
        var item = event.getPlayer().getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) return;
        if (new ItemFeaturesHelper().getItemType(event.getPlayer().getInventory().getItemInMainHand()) != ItemType.Gun) return;
        if (!buffer.containsKey(event.getPlayer())){
            buffer.put(event.getPlayer(), new ArrayList<>());
            buffer.get(event.getPlayer()).add(event.getKey());

            Bukkit.getServer().getScheduler().runTaskLater(FarmLandRpg.getInstance(), ()->{
               Bukkit.getServer().getPluginManager().
                       callEvent(new CombatCombinationHitEvent(event.getPlayer(), buffer.get(event.getPlayer())));
               buffer.remove(event.getPlayer());
            }, 2);
        } else {
            buffer.get(event.getPlayer()).add(event.getKey());
        }
    }
}
