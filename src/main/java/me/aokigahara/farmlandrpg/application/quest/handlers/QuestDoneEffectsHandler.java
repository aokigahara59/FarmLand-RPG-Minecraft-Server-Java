package me.aokigahara.farmlandrpg.application.quest.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.quest.events.QuestDoneEvent;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class QuestDoneEffectsHandler implements Listener{

    @Inject private IPlayerInterface playerInterface;

    @EventHandler
    public void on(QuestDoneEvent event){
        var player = event.getPlayer();

        playerInterface.sendTitle(player, "Квест выполнен", 0xFF00FF00, true,
                                event.getQuest().getQuestBehaviour().getTitle(),
                                0xFFFFFFFF);
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 0.5f);
        player.spawnParticle(Particle.COMPOSTER, player.getLocation(), 20);
    }
}
