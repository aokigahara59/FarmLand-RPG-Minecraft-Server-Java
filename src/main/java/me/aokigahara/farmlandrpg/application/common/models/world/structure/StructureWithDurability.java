package me.aokigahara.farmlandrpg.application.common.models.world.structure;

public interface StructureWithDurability {
    void repair();
    int getDurability();
    void setDurability(int durability);
    void setDefaultDurability(int defaultDurability);
    int getDefaultDurability();
}
