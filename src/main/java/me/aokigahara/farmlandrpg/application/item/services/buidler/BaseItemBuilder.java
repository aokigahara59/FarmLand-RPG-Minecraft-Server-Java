package me.aokigahara.farmlandrpg.application.item.services.buidler;

import de.tr7zw.nbtapi.NBT;
import me.aokigahara.farmlandrpg.application.item.models.CustomLore;
import me.aokigahara.farmlandrpg.application.item.models.ItemRareness;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BaseItemBuilder<SELF extends BaseItemBuilder<SELF>> {
    protected ItemStack itemStack;
    protected CustomLore customLore;


    public BaseItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        customLore = new CustomLore();
    }


    @SuppressWarnings("unchecked")
    public SELF setName(String name){
        NBT.modify(itemStack, nbt ->{
            nbt.modifyMeta((readableNBT, meta) -> meta.setDisplayName(name));
        });
        return (SELF) this;
    }

    @SuppressWarnings("unchecked")
    public SELF setModelData(int modelData){
        NBT.modify(itemStack, nbt ->{
            nbt.modifyMeta((readableNBT, meta) -> meta.setCustomModelData(modelData));
        });
        return (SELF) this;
    }

    @SuppressWarnings("unchecked")
    public SELF addDescriptionLine(String description){
        this.customLore.addString(description);
        return (SELF) this;
    }

    @SuppressWarnings("unchecked")
    public SELF addDescriptionLine(ChatColor color, String description){
        this.customLore.addString(color + description);
        return (SELF) this;
    }

    @SuppressWarnings("unchecked")
    public SELF setItemRareness(ItemRareness itemRareness) {
        NBT.modify(itemStack, nbt ->{
            nbt.setString(ItemRareness.identifier, itemRareness.name());
        });
        return (SELF) this;
    }

    @SuppressWarnings("unchecked")
    public SELF setItemPrice(int price) {
        NBT.modify(itemStack, nbt ->{
            nbt.setInteger("price", price);
        });
        return (SELF) this;
    }

    public ItemStack build(){
        NBT.modify(itemStack, nbt ->{
            nbt.setString(ItemType.identifier, ItemType.Common.name());
            if (!customLore.isEmpty()){
                nbt.setString("custom_lore", customLore.getString());
            }
        });

        return itemStack;
    }
}
