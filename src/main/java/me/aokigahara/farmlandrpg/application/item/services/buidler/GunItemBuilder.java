package me.aokigahara.farmlandrpg.application.item.services.buidler;

import de.tr7zw.nbtapi.NBT;
import me.aokigahara.farmlandrpg.application.combat.models.GunSettings;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.utils.JsonProxySerializationUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GunItemBuilder extends BaseItemBuilder<GunItemBuilder>{
    public GunItemBuilder(Material material) {
        super(material);
    }

    public GunItemBuilder setGunSettings(GunSettings gunSettings){
        NBT.modify(itemStack, nbt ->{
            nbt.setByteArray("gun_settings", JsonProxySerializationUtils.serialize(gunSettings));
        });
        return this;
    }

    @Override
    public ItemStack build(){
        NBT.modify(itemStack, nbt ->{
            nbt.setString(ItemType.identifier, ItemType.Gun.name());
            if (!customLore.isEmpty()){
                nbt.setString("custom_lore", customLore.getString());
            }
        });



        return itemStack;
    }
}

