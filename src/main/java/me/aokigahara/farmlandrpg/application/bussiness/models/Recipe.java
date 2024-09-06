package me.aokigahara.farmlandrpg.application.bussiness.models;

import me.aokigahara.farmlandrpg.application.utils.Tuple;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<Requirement> feedstock = new ArrayList<>();
    private List<Tuple<ItemStack, Integer>> results = new ArrayList<>();
    private int power;
    private long timeInMils;
    private String name;

    public Recipe() {
    }

    public Recipe(Tuple<ItemStack, Integer> result, int power, long timeInMils, String name) {
        this.name = name;
        this.results = new ArrayList<>();
        this.results.add(result);
        this.power = power;
        this.timeInMils = timeInMils;
    }

    public Recipe(List<Tuple<ItemStack, Integer>> results, int power, long timeInMils, String name) {
        this.results = results;
        this.power = power;
        this.timeInMils = timeInMils;
        this.name = name;
    }

    public Recipe(List<Requirement> feedstock, List<Tuple<ItemStack, Integer>> results, int power, long timeInMils, String name) {
        this.feedstock = feedstock;
        this.results = results;
        this.power = power;
        this.timeInMils = timeInMils;
        this.name = name;
    }

    public void setFeedstock(List<Requirement> feedstock) {
        this.feedstock = feedstock;
    }

    public void setResults(List<Tuple<ItemStack, Integer>> results) {
        this.results = results;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setTimeInMils(long timeInMils) {
        this.timeInMils = timeInMils;
    }

    public List<Requirement> getFeedstock() {
        return feedstock;
    }

    public List<Tuple<ItemStack, Integer>> getResults() {
        return results;
    }

    public int getPower() {
        return power;
    }

    public long getTimeInMils() {
        return timeInMils;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
