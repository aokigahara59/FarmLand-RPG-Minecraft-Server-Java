package me.aokigahara.farmlandrpg.application.resource.handlers.holograms;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceHologramService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceService;
import me.aokigahara.farmlandrpg.application.common.generators.TextDisplayBuilder;
import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourceBreakInfoHologramCreator implements Listener {

    @Inject private IResourceService resourceService;
    @Inject private IResourceHologramService hologramService;

    @EventHandler
    public void onBlockBreak(ResourceBreakEvent event){
        if (event.isCancelled()) return;

        hologramService.deleteHologram(event.getResource());


        if (event.getResource().getDurability() == 0){
            return;
        }

        var location = createLocation(event.getPlayer(), event.getBlock());


        var resource = event.getResource();
        var entity = TextDisplayBuilder.create()
                .setLocation(location)
                .setText(ChatColor.GREEN + String.valueOf(resource.getDurability())
                        + "/" + resource.getDefaultDurability())
                .setRotationMode(Display.Billboard.FIXED)
                .setAlignment(TextDisplay.TextAlignment.CENTER).build();
        entity.setRotation(location.getYaw(), 0);




        hologramService.addHologram(resource, entity);
    }





    private Location createLocation(Player player, Block block){
        var blocks = player.getLastTwoTargetBlocks(null, 5);
        var face = blocks.get(1).getFace(blocks.get(0));

        var result = block.getLocation().clone();

        double x = 0;
        double z = 0;

        switch (face){
            case NORTH -> {
                x = 0.5;
                z = -0.1;
                result.getDirection().rotateAroundY(180);
            }

            case SOUTH -> {
                z = 1.1;
                x = 0.5;
            }

            case WEST -> {
                z = 0.5;
                x = -0.1;
                result.getDirection().rotateAroundY(90);
            }

            case EAST -> {
                x = 1.1;
                z = 0.5;
                result.getDirection().rotateAroundY(-90);
            }
        }
        result.add(x, 0.4, z);
        result.setDirection(face.getDirection());

        return result;
    }
}
