package me.aokigahara.farmlandrpg.application.item.services.buidler;

import de.tr7zw.nbtapi.NBT;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ToolItemBuilder extends BaseItemBuilder<ToolItemBuilder> {

    public ToolItemBuilder(Material material) {
        super(material);
    }

    public ToolItemBuilder setToolType(ItemType itemType){
        NBT.modify(itemStack, nbt ->{
            nbt.setString(ItemType.identifier, itemType.name());
        });
        return this;
    }

    @Override
    public ItemStack build(){

        NBT.modify(itemStack, nbt ->{
            if (!customLore.isEmpty()){
                nbt.setString("custom_lore", customLore.getString());
            }
        });



        return itemStack;
    }
}
