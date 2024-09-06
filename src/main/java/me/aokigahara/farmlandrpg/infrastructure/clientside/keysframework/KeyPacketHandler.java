package me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework;

import me.aokigahara.farmlandrpg.infrastructure.clientside.PluginMessageByteBuffer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class KeyPacketHandler implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(@NotNull String s, @NotNull Player player, @NotNull byte[] bytes) {
        if (!s.equalsIgnoreCase("farmlandrpg:key.pressed")) return;

        var buffer = new PluginMessageByteBuffer(bytes);
        var keyCode = buffer.readInt();
        var state = buffer.readInt() == 0 ? KeyState.Released : KeyState.Pressed;


        Bukkit.getServer().getPluginManager().callEvent(new KeyPressedEvent(player, Key.fromKeyCode(keyCode), state));
    }
}
