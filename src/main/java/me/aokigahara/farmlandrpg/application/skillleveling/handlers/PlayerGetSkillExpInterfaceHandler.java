package me.aokigahara.farmlandrpg.application.skillleveling.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.skillleveling.abstractions.IPlayerSkillService;
import me.aokigahara.farmlandrpg.application.skillleveling.events.PlayerGainSkillExpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerGetSkillExpInterfaceHandler implements Listener {

    @Inject private IPlayerInterface playerInterface;
    @Inject private IPlayerSkillService skillService;

    @EventHandler
    public void onExpGain(PlayerGainSkillExpEvent event){
        StringBuilder title = new StringBuilder();
        int color = 0xffffffff;

        switch (event.getSkillType()){

            case Chopping -> {
                title.append("Дровосек") ;
            }
            case Farming -> {
                title.append("Фермер") ;
            }
            case Mining -> {
                title.append("Шахтер") ;
            }
            default -> title.append(event.getSkillType().name());
        }

        var player =event.getPlayer();
        var skill = event.getSkillType();
        var exp = skillService.getExp(player, skill) + event.getFinalExp();
        var level = skillService.getLevel(player, skill);
        var nextLevelExp = skillService.getSkillLevelTable(player, skill).getRequiredExpForLevel(level+1);
        if (nextLevelExp == null) nextLevelExp = exp;

        title.append(' ').append(exp).append('/').append(nextLevelExp);

        playerInterface.sendLeftSideBar(event.getPlayer(),  title.toString(), 0xFF00Fb5d, (int) (((double) exp / nextLevelExp)*100));
    }

}
