package me.aokigahara.farmlandrpg.application.bussiness.models;

import me.aokigahara.farmlandrpg.application.utils.Tuple;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Requirement {

    private int money = 0;
    private List<Tuple<ItemStack, Integer>> items = new ArrayList<>();
    private int level = 0;

    public Requirement() {
    }

    public Requirement(int money) {
        this.money = money;
    }

    public Requirement(int money, int level) {
        this.money = money;
        this.level = level;
    }

    public Requirement(int money, List<Tuple<ItemStack, Integer>> items, int level) {
        this.money = money;
        this.items = items;
        this.level = level;
    }


    public boolean needMoney(){
        return money != 0;
    }

    public boolean needLevel(){
        return level != 0;
    }


    public boolean needItems(){
        return !items.isEmpty();
    }

    public int getMoney() {
        return money;
    }

    public List<Tuple<ItemStack, Integer>> getItems() {
        return items;
    }

    public int getLevel() {
        return level;
    }
}
