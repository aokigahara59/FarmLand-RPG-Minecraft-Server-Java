package me.aokigahara.farmlandrpg.application.common.models.world.structure;

import me.aokigahara.farmlandrpg.application.region.model.Bound;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStructure {
    protected List<Block> allBlockList = new ArrayList<>();
    protected Location location;
    protected Bound bound;




    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }



    public List<Block> getAllBlockList() {
        return allBlockList;
    }

    public void setAllBlockList(List<Block> allBlockList) {
        this.allBlockList = allBlockList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
