package me.aokigahara.farmlandrpg.application.region.model;

import lombok.Data;

@Data
public class Region {
    private String name, description;
    private boolean isSafe;
    private Bound bound;
    private RegionPriority priority;
}
