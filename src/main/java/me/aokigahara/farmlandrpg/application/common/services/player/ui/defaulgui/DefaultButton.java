package me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class DefaultButton {


    private final int slot;

    protected DefaultButton(int slot) {
        this.slot = slot;
    }

    public abstract ItemStack getItem();

    public abstract void onClick(Player player);

    public int getSlot() {
        return slot;
    }
}
