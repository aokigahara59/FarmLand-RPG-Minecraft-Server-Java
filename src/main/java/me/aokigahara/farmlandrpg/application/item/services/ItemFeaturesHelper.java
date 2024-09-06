package me.aokigahara.farmlandrpg.application.item.services;

import de.tr7zw.nbtapi.NBT;
import me.aokigahara.farmlandrpg.application.combat.models.GunSettings;
import me.aokigahara.farmlandrpg.application.item.models.ItemRareness;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.utils.JsonProxySerializationUtils;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicReference;

public class ItemFeaturesHelper {

    @Nullable
    public Integer getPrice(ItemStack itemStack){
        AtomicReference<Integer> result = new AtomicReference<>(null);

        NBT.get(itemStack, readableItemNBT -> {
            if (readableItemNBT.hasTag("price")){
                result.set(readableItemNBT.getInteger("price"));
            }
        });
        return result.get();
    }

    @Nullable
    public GunSettings getGunSettings(ItemStack itemStack){
        AtomicReference<GunSettings> result = new AtomicReference<>(null);

        NBT.get(itemStack, readableItemNBT -> {
            if (readableItemNBT.hasTag("gun_settings")){
                result.set(JsonProxySerializationUtils.deserialize(readableItemNBT.getByteArray("gun_settings"), GunSettings.class));
            }
        });
        return result.get();
    }


    public void setGunSettings(ItemStack itemStack, GunSettings settings){
        NBT.modify(itemStack, nbt ->{
            nbt.setByteArray("gun_settings", JsonProxySerializationUtils.serialize(settings));
        });
    }

    @Nullable
    public ItemRareness getItemRareness(ItemStack itemStack){
        AtomicReference<ItemRareness> result = new AtomicReference<>(null);

        NBT.get(itemStack, readableItemNBT -> {
            if (readableItemNBT.hasTag(ItemRareness.identifier)){
                result.set(ItemRareness.valueOf(readableItemNBT.getString(ItemRareness.identifier)));
            }
        });
        return result.get();
    }

    @Nullable
    public ItemType getItemType(ItemStack itemStack){
        AtomicReference<ItemType> result = new AtomicReference<>(null);

        NBT.get(itemStack, readableItemNBT -> {
            if (readableItemNBT.hasTag(ItemType.identifier)){
                result.set(ItemType.valueOf(readableItemNBT.getString(ItemType.identifier)));
            }
        });
        return result.get();
    }
}
