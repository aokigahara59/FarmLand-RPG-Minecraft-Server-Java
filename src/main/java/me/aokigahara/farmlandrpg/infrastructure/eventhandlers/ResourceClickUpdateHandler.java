package me.aokigahara.farmlandrpg.infrastructure.eventhandlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPluginMessageByteBuffer;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceService;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

import java.util.HashMap;
import java.util.UUID;

public class ResourceClickUpdateHandler implements Listener {


    @Inject private IResourceService resourceService;
    private final ItemFeaturesHelper featuresHelper;
    private final HashMap<UUID, Long> cooldowns ;

    public ResourceClickUpdateHandler() {
        featuresHelper = new ItemFeaturesHelper();
        cooldowns = new HashMap<>();
    }

    @EventHandler
    public void onLeftClick(BlockDamageEvent event){
        boolean isResource = false;

        if (event.getItemInHand() == null ||event.getItemInHand().getType() == Material.AIR){
            isResource = false;
        } else {
            var type = featuresHelper.getItemType(event.getItemInHand());
            if ((type == ItemType.Axe || type == ItemType.Pickaxe)) {
                isResource = resourceService.getResource(event.getBlock()) != null;
            }
        }

        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);
        packet.writeBoolean(isResource);
        event.getPlayer().sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID + ":resource.breaking", packet.asByteArray());
    }

    @EventHandler
    public void onBreak(ResourceBreakEvent event){
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);
        packet.writeBoolean(true);
        event.getPlayer().sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID + ":resource.broken", packet.asByteArray());
    }

    private boolean tryToExecute(Player player){
        if (!cooldowns.containsKey(player.getUniqueId())) {
            cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
            return true;
        }
        if (System.currentTimeMillis() - cooldowns.get(player.getUniqueId()) >= 300){
            cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
            return true;
        }
        return false;
    }
}
