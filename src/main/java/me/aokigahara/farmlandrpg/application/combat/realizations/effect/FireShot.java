package me.aokigahara.farmlandrpg.application.combat.realizations.effect;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.combat.abstractions.IGunshotEffect;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.utils.Sounds;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public class FireShot implements IGunshotEffect {
    @Override
    public void makeShootEffects(Player player, Location hitLocation) {
        var random = new Random();
        player.playSound(player.getLocation(), Sounds.SingleShot,
                random.nextFloat(0.18f, 0.25f), random.nextFloat(0.7f,0.8f));
        FarmLandRpg.getInjector().getInstance(IPlayerInterface.class).sendShake(player, 1);

        Location eyeLocation = player.getEyeLocation();

        Vector right = eyeLocation.getDirection().clone().crossProduct(new Vector(0, 1, 0)).normalize().multiply(0.2);
        Vector down = new Vector(0, -0.2, 0);
        Location startLocation = eyeLocation.clone().add(right).add(down);

        int particleCount = 2;
        Vector direction = hitLocation.toVector().subtract(startLocation.toVector()).normalize();
        double distance = 3;

        for (int i = 1; i < particleCount; i++) {
            double progress = (double) 1 / (2);
            Location particleLocation = startLocation.clone().add(direction.clone().multiply(progress * distance));
            player.spawnParticle(Particle.FLAME, particleLocation, 0, 0, 0, 0, 0);
        }
        player.spawnParticle(Particle.FLAME, hitLocation, 0, 0, 0, 0, 0);
    }
}
