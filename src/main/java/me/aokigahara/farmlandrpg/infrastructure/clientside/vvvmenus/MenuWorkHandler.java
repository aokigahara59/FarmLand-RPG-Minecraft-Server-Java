package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.ICrossplatformDataManager;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.VMenu;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class MenuWorkHandler implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(@NotNull String s, @NotNull Player player, @NotNull byte[] bytes) {

        if (!s.equalsIgnoreCase("farmlandrpg:curent.menu.update")) return;

        var dataManager = FarmLandRpg.getInjector().getInstance(ICrossplatformDataManager.class);

        var menu = dataManager.geData(player, "current_menu_info", VMenu.class);

        var processor = FarmLandRpg.getInjector().getInstance(MenuProcessor.class);

        processor.processElement(menu.getMainDiv(), player);

        dataManager.setData(player, "current_menu_info", menu);
    }
}
