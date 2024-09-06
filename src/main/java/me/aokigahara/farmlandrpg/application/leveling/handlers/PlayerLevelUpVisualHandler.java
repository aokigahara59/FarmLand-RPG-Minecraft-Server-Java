package me.aokigahara.farmlandrpg.application.leveling.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.leveling.events.PlayerNewLevelEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerLevelUpVisualHandler implements Listener {

    @Inject private IPlayerInterface playerInterface;

    @EventHandler
    public void onLevelUp(PlayerNewLevelEvent event){
        playerInterface.sendTitle(event.getPlayer(),
                ChatColor.YELLOW +"Повышение уровня", 0xFFFFFF, true,
                ChatColor.WHITE + ""+event.getLevel() + " уровень!", 0xFFFFFF);
    }
}
