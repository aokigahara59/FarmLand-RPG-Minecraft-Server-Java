package me.aokigahara.farmlandrpg.application.item.services;

import me.aokigahara.farmlandrpg.application.item.events.PlayerGetItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryHelper {

    public static void giveItemToPlayer(Player player, ItemStack item, int count, PlayerGetItemEvent.GotItemType type){
        if(count == 0) return;
        item = item.clone();
        item.setAmount(count);


        if (player.getInventory().firstEmpty() == -1){
            if (player.getInventory().containsAtLeast(item, 1)){
                if (!player.getInventory().containsAtLeast(item, (item.getMaxStackSize() - count + 1))){
                    player.getInventory().addItem(item);
                } else {
                    player.sendMessage(ChatColor.YELLOW + "Ваши карманы забиты, так что новые предметы выпали из них! " +
                            "Посмотрите под ноги!");

                    player.getWorld().dropItemNaturally(player.getLocation(), item);
                }
            } else {
                player.sendMessage(ChatColor.YELLOW + "Ваши карманы забиты, так что новые предметы выпали из них! " +
                        "Посмотрите под ноги!");

                player.getWorld().dropItemNaturally(player.getLocation(), item);
            }
        } else player.getInventory().addItem(item);

        Bukkit.getPluginManager().callEvent(new PlayerGetItemEvent(player, item, count, type));
    }

}
