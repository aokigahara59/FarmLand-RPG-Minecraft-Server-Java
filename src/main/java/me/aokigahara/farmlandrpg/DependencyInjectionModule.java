package me.aokigahara.farmlandrpg;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IBuildingWorkSystem;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IFarmingLandService;
import me.aokigahara.farmlandrpg.application.bussiness.services.BuildingWorkSystem;
import me.aokigahara.farmlandrpg.application.bussiness.services.FarmingLandService;
import me.aokigahara.farmlandrpg.application.combat.abstractions.ICombatSystem;
import me.aokigahara.farmlandrpg.application.combat.services.CombatSystem;
import me.aokigahara.farmlandrpg.application.common.abstractions.IHologramService;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPluginMessageByteBuffer;
import me.aokigahara.farmlandrpg.application.common.abstractions.builder.ISavePasteHook;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.ICrossplatformDataManager;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.IMoneySystem;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.IPlayerRegionStorage;
import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IFPlayerStorage;
import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IHologramStorage;
import me.aokigahara.farmlandrpg.application.common.services.HologramService;
import me.aokigahara.farmlandrpg.application.common.services.ResourceHoloService;
import me.aokigahara.farmlandrpg.application.common.services.player.FromItemCrossplatformDataManager;
import me.aokigahara.farmlandrpg.application.common.services.player.MoneySystem;
import me.aokigahara.farmlandrpg.application.leveling.abstractions.IPlayerLevelingService;
import me.aokigahara.farmlandrpg.application.leveling.services.PlayerLevelingService;
import me.aokigahara.farmlandrpg.application.quest.abstractions.IQuestService;
import me.aokigahara.farmlandrpg.application.quest.services.QuestService;
import me.aokigahara.farmlandrpg.application.region.abstractions.IRegionService;
import me.aokigahara.farmlandrpg.application.region.abstractions.IRegionStorage;
import me.aokigahara.farmlandrpg.application.region.services.RegionService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceHologramService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceHologramsPointersStorage;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceService;
import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceStorage;
import me.aokigahara.farmlandrpg.application.resource.services.ResourceService;
import me.aokigahara.farmlandrpg.application.skillleveling.abstractions.IPlayerSkillService;
import me.aokigahara.farmlandrpg.application.skillleveling.services.PlayerSkillService;
import me.aokigahara.farmlandrpg.infrastructure.clientside.PluginMessageByteBuffer;
import me.aokigahara.farmlandrpg.infrastructure.clientside.interfaces.CooldownUI;
import me.aokigahara.farmlandrpg.infrastructure.storages.*;
import me.aokigahara.farmlandrpg.infrastructure.worldedit.WorldEditHook;

public class DependencyInjectionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IRegionStorage.class).to(InMemoryRegionStorage.class).in(Singleton.class);
        bind(IPlayerRegionStorage.class).to(InMemoryPlayerRegionStorage.class).in(Singleton.class);
        bind(ICombatSystem.class).to(CombatSystem.class);
        bind(IResourceStorage.class).to(InMemoryResourceStorage.class).in(Singleton.class);
        bind(IHologramStorage.class).to(InMemoryHologramStorage.class).in(Singleton.class);
        bind(IFPlayerStorage.class).to(InMemoryFPlayerStorage.class).in(Singleton.class);

        bind(IResourceHologramsPointersStorage.class)
                .to(InMemoryResourceHologramsPointersStorage.class).in(Singleton.class);

        bind(IRegionService.class).to(RegionService.class);
        bind(IResourceService.class).to(ResourceService.class);
        bind(IHologramService.class).to(HologramService.class);



        bind(IQuestService.class).to(QuestService.class);

        bind(IFarmingLandService.class).to(FarmingLandService.class);
        bind(IBuildingWorkSystem.class).to(BuildingWorkSystem.class);

        bind(IPluginMessageByteBuffer.class).to(PluginMessageByteBuffer.class);
        bind(ICrossplatformDataManager.class).to(FromItemCrossplatformDataManager.class);
        bind(IMoneySystem.class).to(MoneySystem.class);

        bind(IResourceHologramService.class).to(ResourceHoloService.class);

        bind(IPlayerLevelingService.class).to(PlayerLevelingService.class);
        bind(ISavePasteHook.class).to(WorldEditHook.class);
        bind(IPlayerInterface.class).to(CooldownUI.class).in(Singleton.class);

        bind(IPlayerSkillService.class).to(PlayerSkillService.class);
    }
}
