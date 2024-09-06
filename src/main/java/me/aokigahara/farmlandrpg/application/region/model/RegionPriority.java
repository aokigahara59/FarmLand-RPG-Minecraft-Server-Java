package me.aokigahara.farmlandrpg.application.region.model;

public enum RegionPriority {
    Common(40),
    City(55),
    FarmLand(66),
    Default(1);

    private final int priority;
    RegionPriority(int priority) {
        this.priority = priority;
    }

    public int getValue() {
        return priority;
    }
}
