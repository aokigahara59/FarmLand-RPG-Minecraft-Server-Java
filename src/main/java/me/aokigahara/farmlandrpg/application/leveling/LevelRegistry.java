package me.aokigahara.farmlandrpg.application.leveling;

import me.aokigahara.farmlandrpg.application.leveling.handlers.PlayerGetExpInterfaceHandler;
import me.aokigahara.farmlandrpg.application.leveling.handlers.PlayerLevelUpVisualHandler;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;

public class LevelRegistry extends AbstractRegistry {
    @Override
    public void register() {
        registerEvent(PlayerLevelUpVisualHandler.class);
        registerEvent(PlayerGetExpInterfaceHandler.class);
    }
}
