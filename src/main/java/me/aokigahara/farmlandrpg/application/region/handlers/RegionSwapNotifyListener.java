package me.aokigahara.farmlandrpg.application.region.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.ICrossplatformDataManager;
import me.aokigahara.farmlandrpg.application.region.events.RegionSwapEvent;
import me.aokigahara.farmlandrpg.application.region.model.Region;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class RegionSwapNotifyListener implements Listener {

    @Inject private ICrossplatformDataManager dataManager;
    @Inject private IPlayerInterface playerInterface;

    @EventHandler
    public void onRegionSwap(RegionSwapEvent event){
        var region = event.getRegions().get(0);

        playerInterface.sendTitle(event.getPlayer(), region.getName(), 0x00FF00,false,
                        region.getDescription(), 0xFFFFFF);

        dataManager.setData(event.getPlayer(), "region_data", new RegionData(event.getRegions()));
    }

    private static class RegionData{
        public String title = "";
        public String description = "";
        public int color = 0xFF00FF00;

        public RegionData(Region region) {
            title = region.getName();
            description = region.getDescription();
            color = region.isSafe() ? 0xFF00FF00 : 0xFFFF0000;
        }

        public RegionData(List<Region> regions) {
            var builder = new StringBuilder();
            for (int i = regions.size()-1; i >= 0; i--){
                builder.append(regions.get(i).getName());
                if (i != 0) builder.append(", ");
            }
            title = builder.toString();


            description = regions.get(regions.size()-1).getDescription();
            color = regions.get(regions.size()-1).isSafe() ? 0xFF00FF00 : 0xFFFF0000;
        }
    }
}
