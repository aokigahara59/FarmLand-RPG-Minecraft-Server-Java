package me.aokigahara.farmlandrpg.application.region.model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public class Bound {
    private Location minPoint;
    private Location maxPoint;

    public Bound(Location firstPoint, Location secondPoint) {
        this.minPoint = firstPoint.clone();
        this.maxPoint = secondPoint.clone();
        assignCorrectBounds();
    }

    public boolean isWithinBounds(Location location){
        var x = location.getX();
        var y = location.getY();
        var z = location.getZ();

        if (x < minPoint.getX()) return false;
        if (x > maxPoint.getX()) return false;

        if (y < minPoint.getY()) return false;
        if (y > maxPoint.getY()) return false;

        if (z < minPoint.getZ()) return false;
        if (z > maxPoint.getZ()) return false;

        return true;
    }

    private void assignCorrectBounds(){
        if (minPoint.getWorld() != maxPoint.getWorld()) throw new IllegalArgumentException("World doesn't matching");

        var firstX = minPoint.getX();
        var firstY = minPoint.getY();
        var firstZ = minPoint.getZ();

        var secondX = maxPoint.getX();
        var secondY = maxPoint.getY();
        var secondZ = maxPoint.getZ();


        maxPoint.setX(Math.max(firstX, secondX));
        maxPoint.setY(Math.max(firstY, secondY));
        maxPoint.setZ(Math.max(firstZ, secondZ));

        minPoint.setX(Math.min(firstX, secondX));
        minPoint.setY(Math.min(firstY, secondY));
        minPoint.setZ(Math.min(firstZ, secondZ));
    }
}
