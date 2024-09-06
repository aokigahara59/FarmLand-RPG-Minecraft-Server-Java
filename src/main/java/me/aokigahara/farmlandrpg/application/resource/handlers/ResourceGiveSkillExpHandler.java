package me.aokigahara.farmlandrpg.application.resource.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.savedata.player.leveling.ILevelTable;
import me.aokigahara.farmlandrpg.savedata.player.leveling.DefaultLevelTable;
import me.aokigahara.farmlandrpg.application.resource.events.ResourceBreakEvent;
import me.aokigahara.farmlandrpg.application.skillleveling.abstractions.IPlayerSkillService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourceGiveSkillExpHandler implements Listener {


    @Inject private IPlayerSkillService skillService;
    private ILevelTable levelTable = new DefaultLevelTable();

    @EventHandler
    public void onBreak(ResourceBreakEvent event){
        var info = event.getResource().getResourceInfo();

        if (info.getSkillExpDrop() != null){
            skillService.addExp(event.getPlayer(), info.getSkillExpDrop().getFirst(), info.getSkillExpDrop().getSecond(),
                    levelTable);
        }
    }
}
