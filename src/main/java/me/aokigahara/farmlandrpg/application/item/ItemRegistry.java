package me.aokigahara.farmlandrpg.application.item;

import me.aokigahara.farmlandrpg.application.item.handlers.GetItemNotifyHandler;
import me.aokigahara.farmlandrpg.application.item.handlers.PickupItemEventHandler;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;

public class ItemRegistry extends AbstractRegistry {

    public void register(){
        registerEvent(GetItemNotifyHandler.class);
        registerEvent(PickupItemEventHandler.class);
    }
}
