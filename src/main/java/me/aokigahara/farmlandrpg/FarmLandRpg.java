package me.aokigahara.farmlandrpg;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.aokigahara.farmlandrpg.application.GameEngine;
import me.aokigahara.farmlandrpg.application.bussiness.BusinessRegistry;
import me.aokigahara.farmlandrpg.application.combat.CombatRegistry;
import me.aokigahara.farmlandrpg.application.common.CommonRegistry;
import me.aokigahara.farmlandrpg.application.dialogue.DialogueRegistry;
import me.aokigahara.farmlandrpg.application.item.ItemRegistry;
import me.aokigahara.farmlandrpg.application.leveling.LevelRegistry;
import me.aokigahara.farmlandrpg.application.quest.QuestRegistry;
import me.aokigahara.farmlandrpg.application.region.RegionRegistry;
import me.aokigahara.farmlandrpg.application.resource.ResourceRegistry;
import me.aokigahara.farmlandrpg.application.skillleveling.SkillRegistry;
import me.aokigahara.farmlandrpg.infrastructure.InfrastructureRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FarmLandRpg extends JavaPlugin {

    private static FarmLandRpg instance;
    private static Injector injector;
    private static ProtocolManager protocolManager;

    public static final String ID = "farmlandrpg";

    @Override
    public void onEnable() {
        instance = this;
        injector = Guice.createInjector(new DependencyInjectionModule());
        protocolManager = ProtocolLibrary.getProtocolManager();
        new SkillRegistry().register();
        new LevelRegistry().register();
        new RegionRegistry().register();
        new ResourceRegistry().register();
        new QuestRegistry().register();
        new BusinessRegistry().register();
        new ItemRegistry().register();
        new CombatRegistry().register();
        new DialogueRegistry().register();
        new InfrastructureRegistry().register();
        new CommonRegistry().register();


        TestData.enable();

        Bukkit.getLogger().fine("FarmingLand RPG started!");
    }

    @Override
    public void onDisable() {
        GameEngine.getInstance().stoutDown();
    }

    public static FarmLandRpg getInstance() {
        return instance;
    }

    public static Injector getInjector() {
        return injector;
    }

    public static ProtocolManager getProtocolManager(){
        return protocolManager;
    }
}
