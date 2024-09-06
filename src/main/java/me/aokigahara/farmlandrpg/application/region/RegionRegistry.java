package me.aokigahara.farmlandrpg.application.region;

import me.aokigahara.farmlandrpg.application.region.handlers.RegionSwapCreator;
import me.aokigahara.farmlandrpg.application.region.handlers.RegionSwapNotifyListener;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;

public class RegionRegistry extends AbstractRegistry {
    public void register(){
        registerEvent(RegionSwapCreator.class);
        registerEvent(RegionSwapNotifyListener.class);
    }
}
