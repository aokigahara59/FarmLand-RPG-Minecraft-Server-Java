package me.aokigahara.farmlandrpg.savedata.player.business.buildings;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class BaseBuildingSaveModel {
    private int level;
    private int exp;
    private HashMap<ItemStack, Integer> storage;
    private int storageCap;

    private String type;


    public BaseBuildingSaveModel() {
        storage = new HashMap<>();
        level = 1;
        exp = 0;
        storageCap = 10;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setStorageCap(int storageCap) {
        this.storageCap = storageCap;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public HashMap<ItemStack, Integer> getStorage() {
        return storage;
    }

    public int getStorageCap() {
        return storageCap;
    }
}
