package me.aokigahara.farmlandrpg.application.bussiness.realizations.buildings;

import me.aokigahara.farmlandrpg.application.item.services.buidler.BaseItemBuilder;
import me.aokigahara.farmlandrpg.application.utils.Tuple;
import me.aokigahara.farmlandrpg.application.bussiness.models.Recipe;
import me.aokigahara.farmlandrpg.application.bussiness.models.Requirement;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.GeneratorBuilding;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleWoodGenerator extends GeneratorBuilding {

    @Override
    public String getName() {
        return "Генератор дерева";
    }

    @Override
    public int getPrice() {
        return 1000;
    }

    @Override
    public int getBaseStorageCap() {
        return 100;
    }

    @Override
    public BuildingType getType() {
        return BuildingType.Generator;
    }

    @Override
    public Requirement getRequirements(int level) {
        var map = new HashMap<Integer, Requirement>();

        map.put(2, new Requirement(200));
        map.put(3, new Requirement(300));
        map.put(4, new Requirement(400));
        map.put(6, new Requirement(500));
        map.put(7, new Requirement(500));
        map.put(8, new Requirement(500));
        map.put(9, new Requirement(500));

        return map.get(level);
    }

    @Override
    public int getPowerLevel(int level) {
        var map = new HashMap<Integer, Integer>();

        map.put(1, 30);
        map.put(2, 40);
        map.put(3, 60);
        map.put(4, 90);
        map.put(5, 150);
        map.put(6, 150);
        map.put(7, 150);
        map.put(8, 150);
        map.put(9, 150);
        return map.get(level);
    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(new Tuple<>(new BaseItemBuilder<>(Material.OAK_WOOD)
                .setName(ChatColor.GREEN + "Дубовое бревно").build(), 1), 10, 1000, "Простая разработка дуба"));

        recipes.add(new Recipe(List.of(new Tuple<>(new BaseItemBuilder<>(Material.OAK_PLANKS)
                .setName(ChatColor.GREEN + "Дубовые доски").addDescriptionLine("")
                .addDescriptionLine("[Обычный]").build(), 2),
                new Tuple<>(new BaseItemBuilder<>(Material.STICK).setName(ChatColor.GREEN + "Палка").build(), 1)), 20, 1000,
                "Полный распил :))"));
        

        return recipes;
    }
}
