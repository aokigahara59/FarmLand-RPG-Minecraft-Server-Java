package me.aokigahara.farmlandrpg.application.resource.realizations;

import me.aokigahara.farmlandrpg.application.item.models.ItemRareness;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import me.aokigahara.farmlandrpg.application.item.services.buidler.BaseItemBuilder;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import me.aokigahara.farmlandrpg.application.resource.models.ResourceInfo;
import me.aokigahara.farmlandrpg.savedata.player.leveling.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Objects;
import java.util.Random;

public class SimpleCoalResource extends Resource {

    private final ResourceInfo resourceInfo;
    
    public SimpleCoalResource() {
        defaultDurability = 3;
        durability = 3;

        repairTimeMs = 5000;
        
        resourceInfo = new ResourceInfo();
        resourceInfo.setName("Уголь");
        resourceInfo.addDrop(new BaseItemBuilder<>(Material.COAL).setName(ChatColor.GRAY + "Уголь")
                .setItemPrice(2).setItemRareness(ItemRareness.Common).build(), 1);
        resourceInfo.addStringInfo("Минимальный уровень 1");
        resourceInfo.setSkillExpDrop(SkillType.Mining, 10);

        resourceInfo.setCanBreakPredicate((player -> {
            var item = player.getInventory().getItemInMainHand();
            if (item.getType() == Material.AIR) return false;

            return Objects.equals(new ItemFeaturesHelper().getItemType(item), ItemType.Pickaxe);
        }));
    }

    @Override
    public void build() {
        allBlockList.clear();
        var random = new Random();
        for (int x = location.getBlockX()-1; x <= location.getBlockX()+1; x++){
            for (int z = location.getBlockZ()-1; z <= location.getBlockZ()+1; z++){
                for (int y = location.getBlockY(); y <= location.getBlockY()+1; y++){
                    var block = location.getWorld().getBlockAt(x, y, z);
                    if (y == location.getBlockY() || random.nextInt(1, 100) < 30){
                        block.setType(Material.COAL_ORE);
                        allBlockList.add(block);
                    }
                }
            }
        }
    }

    @Override
    public ResourceInfo getResourceInfo() {
        return resourceInfo;
    }
}
