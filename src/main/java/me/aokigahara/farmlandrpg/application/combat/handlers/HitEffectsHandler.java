package me.aokigahara.farmlandrpg.application.combat.handlers;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.combat.events.PlayerHitEntityEvent;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.utils.Sounds;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class HitEffectsHandler implements Listener {

    @Inject private IPlayerInterface playerInterface;

    @EventHandler
    public void onHit(PlayerHitEntityEvent event){
        playerInterface.sendEntityHit(event.getPlayer());
        event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sounds.HitSound, 0.5f, 1f);
    }
}
