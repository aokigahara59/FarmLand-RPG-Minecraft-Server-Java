package me.aokigahara.farmlandrpg.application.resource;

import me.aokigahara.farmlandrpg.application.resource.handlers.*;
import me.aokigahara.farmlandrpg.application.resource.handlers.holograms.*;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;

public class ResourceRegistry extends AbstractRegistry {

    public void register(){
        registerEvent(ResourceBreakCreator.class);
        registerEvent(ResourceToolCancelBreakEvent.class);
        registerEvent(ResourceGiveItemHandler.class);
        registerEvent(ResourceDamageHandler.class);
        registerEvent(ResourceParticlesOnBreakHandler.class);
        registerEvent(ResourceGiveSkillExpHandler.class);


        registerEvent(ResourceInfoHologramCreator.class);
        registerEvent(ResourceHologramFixLocationMiddleware.class);
        registerEvent(ResourceCanBreakHologramMiddleware.class);
        registerEvent(ResourceNameHologramMiddleware.class);
        registerEvent(ResourceBreakInfoHologramCreator.class);
        registerEvent(ResourceBreakCameraShakeHandler.class);
    }


}
