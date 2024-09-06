package me.aokigahara.farmlandrpg.application.skillleveling;

import me.aokigahara.farmlandrpg.application.skillleveling.handlers.PlayerGetSkillExpInterfaceHandler;
import me.aokigahara.farmlandrpg.application.skillleveling.handlers.PlayerSkillLevelUpVisualHandler;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;

public class SkillRegistry extends AbstractRegistry {
    @Override
    public void register() {
        registerEvent(PlayerGetSkillExpInterfaceHandler.class);
        registerEvent(PlayerSkillLevelUpVisualHandler.class);
    }
}
