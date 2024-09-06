package me.aokigahara.farmlandrpg.application.combat.handlers;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.item.services.ItemFeaturesHelper;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class AmmoInterfaceHandler implements Listener {

    private final ItemFeaturesHelper featuresHelper;
    private final Set<Player> gunHolders;

    public AmmoInterfaceHandler() {
        featuresHelper = new ItemFeaturesHelper();
        gunHolders = new HashSet<>();
    }

    @EventHandler
    public void onItemSwitch(PlayerItemHeldEvent event){

        var player = event.getPlayer();
        var item = player.getInventory().getItem(event.getNewSlot());
        if (item == null || item.getType() == Material.AIR) {
            gunHolders.remove(player);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
            return;
        }

        var type = featuresHelper.getItemType(item);
        if (type == null){
            gunHolders.remove(player);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));

            return;
        }
        if (!type.equals(ItemType.Gun)) {
            gunHolders.remove(player);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
            return;
        }


        if (!gunHolders.contains(player)){

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!gunHolders.contains(player)) cancel();
                    var item = player.getInventory().getItemInMainHand();
                    if (item == null || item.getType() == Material.AIR) {
                        cancel();
                        return;
                    }
                    var type = featuresHelper.getItemType(item);
                    if (type == null){
                        cancel();
                        return;
                    }
                    if (type != ItemType.Gun) {
                        cancel();
                        return;
                    }
                    var settings = featuresHelper.getGunSettings(item);
                    if (settings == null){
                        cancel();
                        return;
                    }
                    StringBuilder builder = new StringBuilder();
                    builder.append(ChatColor.YELLOW);
                    if (settings.isReloading()) {
                        builder.append("Перезарядка...");
                    }
                    else {
                        var progress = (double) settings.getClip() / settings.getClipSize();
                        builder.append(getAmmoDisplay(settings.getClip(), settings.getClipSize()))
                                .append(getProgressBar(progress));
                    }
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(builder.toString()));
                }
            }.runTaskTimer(FarmLandRpg.getInstance(), 0, 2);
            gunHolders.add(player);
        }


    }

    public String getProgressBar(double progress) {
        int TOTAL_BLOCKS = 10;
        int filledBlocks = (int) (progress * TOTAL_BLOCKS);
        double fractionalPart = (progress * TOTAL_BLOCKS) - filledBlocks;
        StringBuilder progressBar = new StringBuilder();

        // Add fully filled blocks
        for (int i = 0; i < filledBlocks; i++) {
            progressBar.append("█");
        }

        // Add partial block if necessary
        if (fractionalPart > 0) {
            if (fractionalPart <= 0.25) {
                progressBar.append("░");
            } else if (fractionalPart <= 0.50) {
                progressBar.append("▒");
            } else if (fractionalPart <= 0.75) {
                progressBar.append("▓");
            } else {
                progressBar.append("█");
            }
        }

        // Fill the remaining space with empty blocks
        while (progressBar.length() < TOTAL_BLOCKS) {
            progressBar.append("░");
        }

        return progressBar.toString();
    }

    public String getAmmoDisplay(int currentAmmo, int maxAmmo) {
        // Determine the max length of the numbers
        int maxLength = String.valueOf(maxAmmo).length();

        // Create formatted string with right-aligned numbers
        String currentAmmoStr = String.format("%" + maxLength + "d", currentAmmo);
        String maxAmmoStr = String.format("%" + maxLength + "d", maxAmmo);

        return currentAmmoStr + " / " + maxAmmoStr;
    }
}
