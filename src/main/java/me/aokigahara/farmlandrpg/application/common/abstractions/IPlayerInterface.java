package me.aokigahara.farmlandrpg.application.common.abstractions;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IPlayerInterface {
    void sendPushNotification(Player player, String text, int color);

    void sendPushNotification(Player player, String text, int color, ItemStack itemStack);

    void sendTitle(Player player, String title, int color, boolean glow);

    void sendTitle(Player player, String title, int color, boolean glow, String subTitle, int subTitleColor);
    void sendShake(Player player, double strength);
    void sendEntityHit(Player player);

    void sendLeftSideBar(Player player, String title, int color, int percentage);
}
