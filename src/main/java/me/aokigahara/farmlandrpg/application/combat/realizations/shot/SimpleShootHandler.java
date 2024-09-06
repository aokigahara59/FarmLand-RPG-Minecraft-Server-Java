package me.aokigahara.farmlandrpg.application.combat.realizations.shot;

import me.aokigahara.farmlandrpg.application.combat.abstractions.IGunshotEffect;
import me.aokigahara.farmlandrpg.application.combat.abstractions.IShotHandler;
import me.aokigahara.farmlandrpg.application.combat.events.PlayerHitEntityEvent;
import me.aokigahara.farmlandrpg.application.combat.events.PlayerKillEntityEvent;
import me.aokigahara.farmlandrpg.application.combat.models.GunSettings;
import me.aokigahara.farmlandrpg.application.combat.realizations.effect.FireShot;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import me.aokigahara.farmlandrpg.application.utils.Tuple;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import java.util.ArrayList;
import java.util.List;

public class SimpleShootHandler implements IShotHandler {
    @Override
    public void shoot(Player player, ItemStack gun, GunSettings settings) {
        var range = settings.getRange();
        var damage = settings.getDamage();

        Tuple<LivingEntity, Location> trace = rayTrace(player, range);

        IGunshotEffect effect = new FireShot();
        effect.makeShootEffects(player, trace.getSecond());

        settings.reduceClip(1);
        settings.updateLastShootTime();
        new ItemFeaturesHelper().setGunSettings(gun, settings);

        if (trace.getFirst() != null){
            var entities = List.of(trace.getFirst());

            List<LivingEntity> dead = new ArrayList<>();

            List<Tuple<LivingEntity, Double>> hitList = new ArrayList<>();

            for (var x : entities) {
                if (!x.isDead() && x.isValid())
                    hitList.add(new Tuple<>(x, (double) damage));
            }

            var hitEvent = new PlayerHitEntityEvent(player, hitList);
            Bukkit.getServer().getPluginManager().callEvent(hitEvent);

            if (hitEvent.isCancelled()) return;

            for (var x : hitEvent.getEntitiesWithDamage()) {
                var newHealth = x.getFirst().getHealth() - damage;
                if (newHealth <= 0) newHealth = 0;

                x.getFirst().setHealth(newHealth);
                player.attack(x.getFirst());

                if (newHealth == 0) {
                    player.spawnParticle(Particle.SOUL, x.getFirst().getLocation(), 5, 0.2, 0.2, 0.2, 5);
                    player.playSound(player.getEyeLocation(),
                            Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                    dead.add(x.getFirst());
                }

            }

            if (!dead.isEmpty()) Bukkit.getServer().getPluginManager().callEvent(new PlayerKillEntityEvent(hitEvent.getPlayer(),dead));

        }
    }


    private Tuple<LivingEntity, Location> rayTrace(Player player, int range){
        var eyeLocation = player.getEyeLocation();
        RayTraceResult blockResult = player.getWorld().rayTraceBlocks(eyeLocation,
                eyeLocation.getDirection(), range, FluidCollisionMode.NEVER, false);


        RayTraceResult entityResult =  player.getWorld().rayTraceEntities(eyeLocation,
                eyeLocation.getDirection(), range, 0.1, entity -> entity instanceof LivingEntity && entity != player);


        if (entityResult != null && (blockResult == null
                || blockResult.getHitBlock().getType().equals(Material.GRASS)
                || blockResult.getHitBlock().getType().equals(Material.TALL_GRASS)
                || blockResult.getHitBlock().getType().equals(Material.WHEAT)

                || entityResult.getHitPosition().
                distance(eyeLocation.toVector()) < blockResult.getHitPosition()
                .distance(eyeLocation.toVector()))) {
            return new Tuple<>((LivingEntity) entityResult.getHitEntity(), entityResult.getHitPosition().toLocation(player.getWorld()));
        }

        if (blockResult != null) {
            return new Tuple<>(null, blockResult.getHitPosition().toLocation(player.getWorld()));
        }

        // Ничего не встретил
        return new Tuple<>(null, eyeLocation.clone().add(eyeLocation.getDirection().multiply(range)));
    }
}
