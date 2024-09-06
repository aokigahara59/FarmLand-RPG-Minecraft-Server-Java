package me.aokigahara.farmlandrpg.application.common;

import me.aokigahara.farmlandrpg.application.bussiness.handlers.buildings.BuildingsBuildingHandler;
import me.aokigahara.farmlandrpg.application.common.commands.TestQuestCommand;
import me.aokigahara.farmlandrpg.application.common.commands.TestQuestCommandCompleter;
import me.aokigahara.farmlandrpg.application.common.commands.opbuilding.OpBuildingCommand;
import me.aokigahara.farmlandrpg.application.common.commands.opbuilding.OpBuildingCommandCompleter;
import me.aokigahara.farmlandrpg.application.common.handlers.HungerProtectHandler;
import me.aokigahara.farmlandrpg.application.common.handlers.WorldProtectHandler;
import me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui.DefaultMenuWorkHandler;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.MenuWorkHandler;
import me.aokigahara.farmlandrpg.infrastructure.eventhandlers.MoneyUpdateHandler;

public class CommonRegistry extends AbstractRegistry {
    public void register(){

        registerEvent(BuildingsBuildingHandler.class);
        registerEvent(DefaultMenuWorkHandler.class);
        registerEvent(MoneyUpdateHandler.class);
        registerEvent(HungerProtectHandler.class);
        registerEvent(WorldProtectHandler.class);

        registerOutgoingPacket(":curent.menu.open");
        registerIncomingPacket(":curent.menu.update", MenuWorkHandler.class);

        registerCommand("qtc", TestQuestCommand.class, TestQuestCommandCompleter.class);
        registerCommand("opbuilding", OpBuildingCommand.class, OpBuildingCommandCompleter.class);
    }
}
