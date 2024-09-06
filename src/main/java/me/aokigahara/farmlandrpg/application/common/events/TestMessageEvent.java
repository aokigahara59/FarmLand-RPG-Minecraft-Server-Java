package me.aokigahara.farmlandrpg.application.common.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class TestMessageEvent implements Listener {

    private String word;

    @EventHandler
    public void event(PlayerDropItemEvent event){
        var player = event.getPlayer();

        CompletableFuture.runAsync(() -> {
            player.sendMessage("Привет! Давай начнем квест.");
            sleep(2);
            player.sendMessage("Привет! Давай начнем квест.");
            sleep(2);
            player.sendMessage("Привет! Давай начнем квест.");
        });

    }

    private void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public TestMessageEvent(String word) {
        this.word = word;
    }
}
