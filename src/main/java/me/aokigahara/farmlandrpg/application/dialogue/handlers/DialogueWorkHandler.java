package me.aokigahara.farmlandrpg.application.dialogue.handlers;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPluginMessageByteBuffer;
import me.aokigahara.farmlandrpg.application.dialogue.models.Dialogue;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.Key;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.KeyPressedEvent;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.KeyState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.List;

public class DialogueWorkHandler implements Listener {

    private HashMap<Player, Key> buffer;
    private final List<Key> keyVariants;

    public DialogueWorkHandler() {
        buffer = new HashMap<>();
        keyVariants = List.of(Key.KEY_1, Key.KEY_2, Key.KEY_3, Key.KEY_4);
    }

    @EventHandler
    public void onClick(KeyPressedEvent event){
        if (event.getState() == KeyState.Released) return;
        var key = event.getKey();
        var player = event.getPlayer();
        if (!keyVariants.contains(key)) return;

        if (!player.hasMetadata("dialogue")) return;

        Dialogue dialogue = (Dialogue) player.getMetadata("dialogue").get(0).value();

        int number = switch (key){
            case KEY_1 -> 1;
            case KEY_2 -> 2;
            case KEY_3 -> 3;
            case KEY_4 -> 4;

            default -> 1;
        };

        if (!dialogue.hasAnswer(number)) return;


        if (!buffer.containsKey(player) ||key != buffer.get(player)) {
            buffer.put(player, key);

            var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);

            packet.writeInt(number);

            player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":dialogue.select", packet.asByteArray());

        } else if (buffer.containsKey(player) && buffer.get(player).equals(event.getKey())) {
            dialogue.next(number-1);
            buffer.remove(player);
        }
    }
}
