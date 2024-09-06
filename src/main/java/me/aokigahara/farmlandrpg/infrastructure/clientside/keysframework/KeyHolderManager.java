package me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.HashSet;

public class KeyHolderManager implements Listener {

    private static HashMap<Player, HashSet<Key>> memory;

    public KeyHolderManager() {
        memory = new HashMap<>();
    }

    @EventHandler
    public void onClick(KeyPressedEvent event){
        var player = event.getPlayer();

        if (!memory.containsKey(player)) memory.put(player, new HashSet<>());

        if (event.getState() == KeyState.Pressed)
             memory.get(player).add(event.getKey());
        else memory.get(player).remove(event.getKey());
    }

    public static boolean isKeyPressed(Player player, Key key){
        return memory.containsKey(player) && memory.get(player).contains(key);
    }

}
