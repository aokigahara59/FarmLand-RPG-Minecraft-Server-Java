package me.aokigahara.farmlandrpg.application.resource.realizations;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.builder.ISavePasteHook;
import me.aokigahara.farmlandrpg.application.item.models.ItemRareness;
import me.aokigahara.farmlandrpg.application.item.services.buidler.BaseItemBuilder;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;
import me.aokigahara.farmlandrpg.application.resource.models.ResourceInfo;
import me.aokigahara.farmlandrpg.savedata.player.leveling.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.File;

public class SimpleTestOakTree extends Resource {

    @Inject private ISavePasteHook builder;
    private final ResourceInfo resourceInfo;


    public SimpleTestOakTree() {
        defaultDurability = 10;
        durability = defaultDurability;
        repairTimeMs = 5000;

        resourceInfo = new ResourceInfo();
        resourceInfo.setName("Дуб Ругару");
        resourceInfo.addDrop(new BaseItemBuilder<>(Material.OAK_LOG).setName(ChatColor.GRAY + "Дерево дуба Ругару")
                .setItemPrice(10).setItemRareness(ItemRareness.Common).build(), 1);
        resourceInfo.setSkillExpDrop(SkillType.Chopping, 10);

        resourceInfo.addStringInfo("Минимальный уровень 2");
        resourceInfo.setCanBreakPredicate((player -> {

            var item = player.getInventory().getItemInMainHand();

            Material itemType = item.getType();
            return itemType == Material.WOODEN_AXE ||
                    itemType == Material.STONE_AXE ||
                    itemType == Material.IRON_AXE ||
                    itemType == Material.GOLDEN_AXE ||
                    itemType == Material.DIAMOND_AXE;
        }));
    }


    @Override
    public void build() {
        var file = new File(FarmLandRpg.getInstance().getDataFolder(), "schematics/test.schem");
        allBlockList.clear();

        var blocks = builder.paste(location, file);
        allBlockList.addAll(blocks);
    }

    @Override
    public void setLocation(Location location) {
        this.location = location.clone().subtract(5, 1, 7);
    }

    @Override
    public ResourceInfo getResourceInfo() {
        return resourceInfo;
    }
}
