package me.aokigahara.farmlandrpg.application.test;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.combat.events.PlayerKillEntityEvent;
import me.aokigahara.farmlandrpg.application.leveling.abstractions.IPlayerLevelingService;
import me.aokigahara.farmlandrpg.savedata.player.leveling.DefaultLevelTable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TestMobExpHandler implements Listener {


    @Inject private IPlayerLevelingService levelingService;

    @EventHandler
    public void onKill(PlayerKillEntityEvent event){
        levelingService.addExp(event.getPlayer(), event.getEntities().size()*10, new DefaultLevelTable());
    }
}
