package me.aokigahara.farmlandrpg.application.resource.handlers.holograms;

import me.aokigahara.farmlandrpg.application.common.events.HologramPreSpawnEvent;
import me.aokigahara.farmlandrpg.application.common.generators.TextDisplayBuilder;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourceCanBreakHologramMiddleware implements Listener {

    @EventHandler
    public void onEvent(HologramPreSpawnEvent event){
        var resource = event.getResource();

        for (var x : resource.getResourceInfo().getInformation()){
            String text = ChatColor.WHITE + "";

            text += x;

            var entity = TextDisplayBuilder.create()
                    .setText(text)
                    .setLocation(event.getNewLineLocation()).build();

            event.addHologram(entity);
        }


    }
}
