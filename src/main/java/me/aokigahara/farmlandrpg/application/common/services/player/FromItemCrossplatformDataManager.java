package me.aokigahara.farmlandrpg.application.common.services.player;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadableItemNBT;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.ICrossplatformDataManager;
import me.aokigahara.farmlandrpg.application.item.services.buidler.BaseItemBuilder;
import me.aokigahara.farmlandrpg.application.utils.JsonProxySerializationUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class FromItemCrossplatformDataManager implements ICrossplatformDataManager {

    private final int slot = 17;

    @Override
    public void setData(Player player, String name, Object value) {
        var item = getItem(player);

        NBT.modify(item, nbt ->{
            nbt.setByteArray(name, JsonProxySerializationUtils.serialize(value));
        });
    }

    @Override
    public <T> T geData(Player player, String value, Class<T> clazz) {
        var item = getItem(player);

        if (!hasData(player, value)) return null;

        return JsonProxySerializationUtils.deserialize( NBT.get(item, (Function<ReadableItemNBT, byte[]>) x -> x.getByteArray(value)), clazz);
    }

    @Override
    public boolean hasData(Player player, String value) {
        return NBT.get(getItem(player), x-> x.hasNBTData() && x.hasTag(value));
    }

    @Override
    public void removeData(Player player, String value) {
        NBT.modify(getItem(player), x->{
            x.removeKey(value);
        });
    }

    private ItemStack getItem(Player player) {
        if (player.getInventory().getItem(slot) == null) {
            var expectedItem = player.getInventory().getItem(slot);
            var context = new Object() {
                boolean isDataItem = false;
            };

            if (expectedItem != null) {
                NBT.get(expectedItem, x -> {
                    if (x.hasNBTData()) {
                        if (x.getBoolean("nbt_data_storage")) {
                            context.isDataItem = true;
                        }
                    }
                });
            }

            if (!context.isDataItem) {
                var dataItem = new BaseItemBuilder<>(Material.BARREL)
                        .setName(ChatColor.GREEN + "Рюкзак")
                        .build();

                NBT.modify(dataItem, nbt -> {
                    nbt.setBoolean("nbt_data_storage", true);
                });

                player.getInventory().setItem(slot, dataItem);
            }
        }

        return player.getInventory().getItem(slot);
    }


    public static class FromItemCrossplatformInventoryManager implements Listener{

        @EventHandler
        public void onClick(InventoryClickEvent event){
            var item = event.getCurrentItem();
            if (item != null) {
                var player = (Player) event.getWhoClicked();
                if (event.getClickedInventory().getHolder() == player)

                    if (NBT.get(item, x-> x.hasNBTData() && x.hasTag("nbt_data_storage"))){
                        event.setCancelled(true);
                    }
            }
        }
    }
}
