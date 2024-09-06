package me.aokigahara.farmlandrpg.application.skillleveling.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.skillleveling.events.PlayerNewSkillLevelEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerSkillLevelUpVisualHandler implements Listener {

    @Inject private IPlayerInterface playerInterface;

    @EventHandler
    public void onLevelUp(PlayerNewSkillLevelEvent event){
        StringBuilder title = new StringBuilder(ChatColor.GREEN +"Навык '");
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
        title.append("' улучшен");
        playerInterface.sendTitle(event.getPlayer(),
                title.toString(), 0xFFFFFF, true,
                ChatColor.WHITE + ""+event.getLevel() + " уровень!", 0xFFFFFF);
    }
}
