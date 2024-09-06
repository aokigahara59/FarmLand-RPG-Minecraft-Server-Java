package me.aokigahara.farmlandrpg.application.combat;

import me.aokigahara.farmlandrpg.application.combat.handlers.*;
import me.aokigahara.farmlandrpg.application.combat.realizations.ReloadListener;
import me.aokigahara.farmlandrpg.application.combat.realizations.SimpleShotListener;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CombatRegistry extends AbstractRegistry {

    public void register(){
        registerEvent(DamageIndicatorHandler.class);
        registerEvent(HitEffectsHandler.class);
        registerEvent(ReloadSwitchPreventHandler.class);
        registerEvent(AmmoInterfaceHandler.class);
        registerEvent(CombatClickCombinationHandler.class);



        List<CombatListener> listeners = new ArrayList<>();

        listeners.add(injector.getInstance(SimpleShotListener.class));
        listeners.add(injector.getInstance(ReloadListener.class));

        listeners.sort(Comparator.comparingInt(x -> x.getKeys().size()));

        for (var x : listeners){
            registerEvent(x.getClass());
        }
    }
}
