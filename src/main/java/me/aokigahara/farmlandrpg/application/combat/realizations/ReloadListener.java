package me.aokigahara.farmlandrpg.application.combat.realizations;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.combat.abstractions.ICombatSystem;
import me.aokigahara.farmlandrpg.application.combat.events.CombatCombinationHitEvent;
import me.aokigahara.farmlandrpg.application.combat.handlers.CombatListener;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.Key;

import java.util.List;

public class ReloadListener extends CombatListener {

    @Inject private ICombatSystem combatSystem;
    private final ItemFeaturesHelper featuresHelper;

    protected ReloadListener() {
        super(List.of(Key.KEY_R));
        featuresHelper = new ItemFeaturesHelper();
    }

    @Override
    protected void execute(CombatCombinationHitEvent event) {
        var item = event.getPlayer().getInventory().getItemInMainHand();
        var settings = featuresHelper.getGunSettings(item);
        if (settings.getClip() != settings.getClipSize())
            combatSystem.reloadGun(event.getPlayer(), item);
    }
}
