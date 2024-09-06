package me.aokigahara.farmlandrpg.application.common.generators.builder;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.region.model.Bound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TestBuilder {
    public void buildStructure(Bound bound, Player player) {
        var min = bound.getMinPoint();
        var max = bound.getMaxPoint();

        new BukkitRunnable() {
            private int x = min.getBlockX();
            private int y = min.getBlockY();
            private int z = min.getBlockZ();
            private int step = 0;

            @Override
            public void run() {
                if (step >= (max.getBlockX() - min.getBlockX() + 1) * (max.getBlockY() - min.getBlockY() + 1) * (max.getBlockZ() - min.getBlockZ() + 1)) {
                    this.cancel();
                    return;
                }

                Location loc = new Location(min.getWorld(), x, y, z);

                if (y == min.getBlockY() || y == max.getBlockY()) {
                    // Floor and ceiling
                    player.sendBlockChange(loc, Material.OAK_PLANKS.createBlockData());
                } else if (x == min.getBlockX() || x == max.getBlockX() || z == min.getBlockZ() || z == max.getBlockZ()) {
                    // Walls with spaces for doors and windows
                    if ((x == min.getBlockX() + 1 || x == max.getBlockX() - 1) && y == min.getBlockY() + 1 && z == min.getBlockZ()) {
                        player.sendBlockChange(loc, Material.GLASS.createBlockData()); // Windows
                    } else if (x == (min.getBlockX() + max.getBlockX()) / 2 && y == min.getBlockY() + 1 && z == min.getBlockZ()) {
                        player.sendBlockChange(loc, Material.AIR.createBlockData()); // Door
                    } else {
                        player.sendBlockChange(loc, Material.BRICKS.createBlockData());
                    }
                } else {
                    player.sendBlockChange(loc, Material.AIR.createBlockData());
                }

                loc.getWorld().spawnParticle(Particle.LAVA, loc, 10);
                loc.getWorld().playSound(loc, Sound.BLOCK_STONE_PLACE, 1, 1);

                z++;
                if (z > max.getBlockZ()) {
                    z = min.getBlockZ();
                    x++;
                    if (x > max.getBlockX()) {
                        x = min.getBlockX();
                        y++;
                    }
                }
                step++;
            }
        }.runTaskTimer(FarmLandRpg.getInstance(), 0L, 3L); // 10 ticks delay between each block placement
    }

    public void destroyStructure(Bound bound, Player player) {
        var min = bound.getMinPoint();
        var max = bound.getMaxPoint();
        for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
            for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
                for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
                    Location loc = new Location(min.getWorld(), x, y, z);
                    player.sendBlockChange(loc, Material.AIR.createBlockData());
                }
            }
        }
    }

    public void buildHollowSquare(Bound bound, Player player) {
        var min = bound.getMinPoint();
        var max = bound.getMaxPoint();

        int minX = min.getBlockX();
        int minZ = min.getBlockZ();
        int maxX = max.getBlockX();
        int maxZ = max.getBlockZ();
        int y = min.getBlockY(); // Assuming the flat square is on the Y level of the min location

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                if (x == minX || x == maxX || z == minZ || z == maxZ) {
                    Location loc = new Location(min.getWorld(), x, y, z);
                    player.sendBlockChange(loc, Material.STONE.createBlockData());
                }
            }
        }
    }
}
