package me.aokigahara.farmlandrpg.application.combat.realizations;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.combat.abstractions.ICombatSystem;
import me.aokigahara.farmlandrpg.application.combat.abstractions.IShotHandler;
import me.aokigahara.farmlandrpg.application.combat.events.CombatCombinationHitEvent;
import me.aokigahara.farmlandrpg.application.combat.handlers.CombatListener;
import me.aokigahara.farmlandrpg.application.combat.realizations.shot.SimpleShootHandler;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.Key;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.KeyHolderManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SimpleShotListener extends CombatListener {

    @Inject private ICombatSystem combatSystem;
    private final ItemFeaturesHelper itemFeaturesHelper = new ItemFeaturesHelper();
    private IShotHandler defaultShootHandler;

    public SimpleShotListener() {
        super(List.of(Key.KEY_RIGHT_MOUSE_BUTTON));
        defaultShootHandler = new SimpleShootHandler();
    }

    @Override
    protected void execute(CombatCombinationHitEvent event) {

        var item = event.getPlayer().getInventory().getItemInMainHand();

        var settings = itemFeaturesHelper.getGunSettings(item);

        if (!settings.isMultiShot()){
            manageShoot(event.getPlayer(), item);
        }
        if (settings.isMultiShot()){
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!KeyHolderManager.isKeyPressed(event.getPlayer(), Key.KEY_RIGHT_MOUSE_BUTTON)){
                        this.cancel();
                    }
                    else{
                        var item = event.getPlayer().getInventory().getItemInMainHand();
                        var shouldContinue = manageShoot(event.getPlayer(), item);
                        if (!shouldContinue) cancel();
                    }
                }
            }.runTaskTimer(FarmLandRpg.getInstance(), 0, (settings.getTimeBetweenShotsMs()-10) / 50);
        }
    }

    private boolean manageShoot(Player player, ItemStack item){
        var type = itemFeaturesHelper.getItemType(item);
        if (type == null || type != ItemType.Gun){
            return false;
        }

        var settings = itemFeaturesHelper.getGunSettings(item);

        if (settings.isReloading()) {
            return true;
        }

        if (settings.getClip() <= 0) {
            combatSystem.reloadGun(player, item);
        } else if (settings.shootTimingPassed()) {
            defaultShootHandler.shoot(player, item, settings);
        }
        return true;
    }
}
