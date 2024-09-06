package me.aokigahara.farmlandrpg.application.combat.handlers;

import me.aokigahara.farmlandrpg.application.combat.events.CombatCombinationHitEvent;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.Key;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.List;

public abstract class CombatListener implements Listener {

    private final List<Key> keys;

    protected CombatListener(List<Key> keys) {
        this.keys = keys;
    }


    @EventHandler
    public void onEvent(CombatCombinationHitEvent event){

        if (new HashSet<>(keys).containsAll(event.getKeys())){
            execute(event);
            event.setCancelled(true);
        }
    }

    protected abstract void execute(CombatCombinationHitEvent event);


    public List<Key> getKeys() {
        return keys;
    }
}
