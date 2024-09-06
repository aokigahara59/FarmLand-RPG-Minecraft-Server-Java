package me.aokigahara.farmlandrpg.application.combat.handlers;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.combat.events.PlayerHitEntityEvent;
import me.aokigahara.farmlandrpg.application.common.generators.TextDisplayBuilder;
import me.aokigahara.farmlandrpg.application.utils.TextSymbols;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.*;

public class DamageIndicatorHandler implements Listener {

    private final Random random = new Random();
    private final Map<LivingEntity, List<TextDisplay>> displays = new HashMap<>();

    @EventHandler
    public void onEntityHit(PlayerHitEntityEvent event){
        for (var entityWithDamage : event.getEntitiesWithDamage()){

            var holograms = displays.computeIfAbsent(entityWithDamage.getFirst(), key -> new LinkedList<>());

            if (holograms.size() == 2){
                holograms.get(0).remove();
                holograms.remove(0);
            }

            int damage = entityWithDamage.getSecond().intValue();

            Location locationForHpIndicator = entityWithDamage.getFirst().getLocation().clone()
                    .add(0, 1.5, 0)
                    .add(random.nextDouble(-1, 1),
                            random.nextDouble(0, 1),
                            random.nextDouble(-1, 1));

            var text = TextDisplayBuilder.create()
                    .setText(TextSymbols.WoodenSword + ChatColor.BOLD + "" + ChatColor.RED + "-" + damage)
                    .setLocation(locationForHpIndicator)
                    .setShadowed(false)
                    .setBackgroundColor(Color.fromARGB(0, 0, 0, 0))
                    .build();

            var transformation = text.getTransformation();
            transformation.getScale().set(random.nextDouble(1d, 2.1d));
            text.setTransformation(transformation);
            holograms.add(text);

            Bukkit.getScheduler().runTaskLater(FarmLandRpg.getInstance(), ()->{
                if (text.isValid() && !text.isDead()){
                    text.remove();
                    holograms.remove(text);
                }
            }, 40);
        }

    }
}
