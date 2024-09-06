package me.aokigahara.farmlandrpg.infrastructure.clientside.interfaces;

import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.utils.DualKeyMap;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class CooldownUI implements IPlayerInterface {

    private IPlayerInterface playerInterface;
    private DualKeyMap<UUID, String, Long> cooldowns = new DualKeyMap<>();

    private HashMap<String, Long> periods = new HashMap<>();

    public CooldownUI() {
        playerInterface = new UI();

        periods.put("title", 5000l);
    }

    @Override
    public void sendPushNotification(Player player, String text, int color) {
        playerInterface.sendPushNotification(player, text, color);
    }

    @Override
    public void sendPushNotification(Player player, String text, int color, ItemStack itemStack) {
        playerInterface.sendPushNotification(player, text, color, itemStack);
    }

    @Override
    public void sendTitle(Player player, String title, int color, boolean glow) {
        if (tryToExecute(player, "title")){
            playerInterface.sendTitle(player, title, color, glow);
        }
    }

    @Override
    public void sendTitle(Player player, String title, int color, boolean glow, String subTitle, int subTitleColor) {
        if (tryToExecute(player, "title")){
            playerInterface.sendTitle(player, title, color, glow, subTitle, subTitleColor);
        }
    }

    @Override
    public void sendShake(Player player, double strength) {
        playerInterface.sendShake(player, strength);
    }

    @Override
    public void sendEntityHit(Player player) {
        playerInterface.sendEntityHit(player);
    }

    @Override
    public void sendLeftSideBar(Player player, String title, int color, int percentage) {
        playerInterface.sendLeftSideBar(player, title, color, percentage);
    }


    private boolean tryToExecute(Player player, String name){
        if (!cooldowns.containsKeys(player.getUniqueId(), name)) {
            cooldowns.put(player.getUniqueId(), name, System.currentTimeMillis());
            return true;
        }
        if (System.currentTimeMillis() - cooldowns.get(player.getUniqueId(), name) >= periods.get(name)){
            cooldowns.put(player.getUniqueId(), name, System.currentTimeMillis());
            return true;
        }
        return false;
    }
}
