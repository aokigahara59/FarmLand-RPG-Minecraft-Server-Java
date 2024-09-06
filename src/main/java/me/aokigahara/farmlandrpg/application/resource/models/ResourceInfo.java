package me.aokigahara.farmlandrpg.application.resource.models;

import lombok.Getter;
import lombok.Setter;
import me.aokigahara.farmlandrpg.application.utils.Tuple;
import me.aokigahara.farmlandrpg.savedata.player.leveling.SkillType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ResourceInfo {

    @Getter
    @Setter
    private String name = "";

    @Getter
    private List<Tuple<ItemStack, Integer>> drops = new ArrayList<>();
    @Getter
    private Tuple<SkillType, Integer> skillExpDrop;

    @Getter
    private List<String> information = new ArrayList<>();

    @Setter
    private Predicate<Player> canBreakPredicate = (player -> true);


    public ResourceInfo() {
    }

    public ResourceInfo(String name, List<Tuple<ItemStack, Integer>> drops, List<String> information, Predicate<Player> canBreakPredicate) {
        this.name = name;
        this.drops = drops;
        this.information = information;
        this.canBreakPredicate = canBreakPredicate;
    }

    public ResourceInfo(String name, List<Tuple<ItemStack, Integer>> drops, Tuple<SkillType, Integer> skillExpDrop, List<String> information, Predicate<Player> canBreakPredicate) {
        this.name = name;
        this.drops = drops;
        this.skillExpDrop = skillExpDrop;
        this.information = information;
        this.canBreakPredicate = canBreakPredicate;
    }

    public void setSkillExpDrop(SkillType skillType, int exp){
        skillExpDrop = new Tuple<>(skillType, exp);
    }

    public void addStringInfo(String info){
        information.add(info);
    }

    public void addDrop(ItemStack item, int amount){
        drops.add(new Tuple<>(item, amount));
    }

    public boolean canBreak(Player player) {
        return canBreakPredicate.test(player);
    }
}
