package me.aokigahara.farmlandrpg.infrastructure.clientside.interfaces;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPlayerInterface;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPluginMessageByteBuffer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UI implements IPlayerInterface {

    @Override
    public void sendPushNotification(Player player, String text, int color){
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);

        packet.writeString(text);
        packet.writeInt(color);
        packet.writeBoolean(false);

        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":notify.push", packet.asByteArray());
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.4f, 0.15f);
    }

    @Override
    public void sendPushNotification(Player player, String text, int color, ItemStack itemStack){
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);

        packet.writeString(text);
        packet.writeInt(color);
        packet.writeBoolean(true);
        packet.writeString(itemStack.getType().getKey().getKey());
        packet.writeString(itemStack.getItemMeta().getDisplayName());
        packet.writeInt(itemStack.getAmount());


        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":notify.push", packet.asByteArray());
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.4f, 0.15f);
    }

    @Override
    public void sendTitle(Player player, String title, int color, boolean glow){
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);

        packet.writeString(title);
        packet.writeInt(color);
        packet.writeBoolean(glow);

        packet.writeBoolean(false);

        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":title.send", packet.asByteArray());
    }

    @Override
    public void sendTitle(Player player, String title, int color, boolean glow, String subTitle, int subTitleColor){
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);

        packet.writeString(title);
        packet.writeInt(color);
        packet.writeBoolean(glow);
        packet.writeBoolean(true);
        packet.writeString(subTitle);
        packet.writeInt(subTitleColor);

        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":title.send", packet.asByteArray());
    }

    @Override
    public void sendShake(Player player, double strength) {
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);
        packet.writeDouble(strength);

        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":camera.shake", packet.asByteArray());
    }

    @Override
    public void sendEntityHit(Player player) {
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);
        packet.writeBoolean(true);
        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":hit", packet.asByteArray());
    }

    @Override
    public void sendLeftSideBar(Player player, String title, int color, int percentage) {
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);
        packet.writeString(title);
        packet.writeInt(color);
        packet.writeInt(percentage);
        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":leftsidebar", packet.asByteArray());
    }
}
