package me.aokigahara.farmlandrpg.application.utils;

import com.google.inject.Injector;
import me.aokigahara.farmlandrpg.FarmLandRpg;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

public abstract class AbstractRegistry {

    protected FarmLandRpg plugin = FarmLandRpg.getInstance();
    protected Messenger messenger = plugin.getServer().getMessenger();
    protected PluginManager manager = plugin.getServer().getPluginManager();
    protected Injector injector = FarmLandRpg.getInjector();


    public abstract void register();


    protected void registerEvent(Class<? extends Listener> event){
        manager.registerEvents(injector.getInstance(event), plugin);
    }

    protected void registerOutgoingPacket(String channel){
        messenger.registerOutgoingPluginChannel(plugin, FarmLandRpg.ID + checkChanel(channel));
    }

    protected void registerIncomingPacket(String channel, Class<? extends PluginMessageListener> listener){
        messenger.registerIncomingPluginChannel(plugin, FarmLandRpg.ID + checkChanel(channel), injector.getInstance(listener));
    }

    protected void registerCommand(String name, Class<? extends CommandExecutor> executor){
        plugin.getCommand(name).setExecutor(injector.getInstance(executor));
    }

    protected void registerCommand(String name, Class<? extends CommandExecutor> executor, Class<? extends TabCompleter> tabCompleter){
        plugin.getCommand(name).setExecutor(injector.getInstance(executor));
        plugin.getCommand(name).setTabCompleter(injector.getInstance(tabCompleter));
    }

    private String checkChanel(String channel){
        if (channel.startsWith(":")) return channel;
        else return ":"+channel;
    }
}
