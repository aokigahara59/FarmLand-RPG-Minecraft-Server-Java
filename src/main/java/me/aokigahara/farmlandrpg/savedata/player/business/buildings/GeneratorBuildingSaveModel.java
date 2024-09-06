package me.aokigahara.farmlandrpg.savedata.player.business.buildings;

import me.aokigahara.farmlandrpg.application.bussiness.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class GeneratorBuildingSaveModel extends BaseBuildingSaveModel {
    private int power;
    private int maxPower;
    private List<Recipe> recipes;
    private long lastTimeInteractedInSystemMills;


    public GeneratorBuildingSaveModel() {
        lastTimeInteractedInSystemMills = System.currentTimeMillis();
        recipes = new ArrayList<>();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public long getLastTimeInteractedInSystemMills() {
        return lastTimeInteractedInSystemMills;
    }

    public void setLastTimeInteractedInSystemMills(long lastTimeInteractedInSystemMills) {
        this.lastTimeInteractedInSystemMills = lastTimeInteractedInSystemMills;
    }
}
