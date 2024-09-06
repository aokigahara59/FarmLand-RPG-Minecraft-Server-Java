package me.aokigahara.farmlandrpg.application.bussiness.models.buildings;

import me.aokigahara.farmlandrpg.application.bussiness.models.Recipe;

import java.util.List;

public abstract class GeneratorBuilding extends BaseBuilding{

    public abstract List<Recipe> getRecipes();
}
