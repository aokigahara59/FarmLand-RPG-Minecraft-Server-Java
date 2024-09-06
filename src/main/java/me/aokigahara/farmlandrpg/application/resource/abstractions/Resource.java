package me.aokigahara.farmlandrpg.application.resource.abstractions;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.resource.models.ResourceInfo;
import me.aokigahara.farmlandrpg.application.common.models.world.structure.AbstractStructure;
import me.aokigahara.farmlandrpg.application.common.models.world.structure.AutoGeneratingStructure;
import me.aokigahara.farmlandrpg.application.common.models.world.structure.StructureWithDurability;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public abstract class Resource extends AbstractStructure implements AutoGeneratingStructure, StructureWithDurability {
    protected int defaultDurability;
    protected int durability;
    protected int repairTimeMs;

    public abstract ResourceInfo getResourceInfo();

    @Override
    public void remove() {
        for (var x : allBlockList) {
            x.setType(Material.AIR);
        }
    }

    @Override
    public void repair(){
        Bukkit.getScheduler().runTaskLaterAsynchronously(FarmLandRpg.getInstance(), ()->{
            Bukkit.getScheduler().runTask(FarmLandRpg.getInstance(), this::build);
            durability = defaultDurability;
        },  (int) (20* (double)(repairTimeMs/1000)));
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;

        if (durability <= 0) {
            remove();
            repair();
        }
    }

    public void subtractDurability(int amount){
        setDurability(durability - amount);
    }

    public int getDefaultDurability() {
        return defaultDurability;
    }

    public void setDefaultDurability(int defaultDurability) {
        this.defaultDurability = defaultDurability;
    }
}
