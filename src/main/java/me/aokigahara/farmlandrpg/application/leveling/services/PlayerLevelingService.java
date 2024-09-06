package me.aokigahara.farmlandrpg.application.leveling.services;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IFPlayerStorage;
import me.aokigahara.farmlandrpg.savedata.player.leveling.ILevelTable;
import me.aokigahara.farmlandrpg.application.leveling.abstractions.IPlayerLevelingService;
import me.aokigahara.farmlandrpg.application.leveling.events.PlayerGainExpEvent;
import me.aokigahara.farmlandrpg.application.leveling.events.PlayerNewLevelEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerLevelingService implements IPlayerLevelingService {

    @Inject private IFPlayerStorage playerStorage;

    public void addExp(Player player, int exp, ILevelTable levelTable){
        var levelModel = playerStorage.get(player).getPlayerLevel();

        var event = new PlayerGainExpEvent(player,exp);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (event.isCancelled()) return;

        var level = levelModel.getLevel();
        var newLevel = level;
        var currentExp = levelModel.getExp();

        currentExp += event.getFinalExp();

        while (levelTable.enoughFor(newLevel+1, currentExp)){
            newLevel++;
            currentExp -= levelTable.getRequiredExpForLevel(newLevel);
        }

        levelModel.setExp(currentExp);

        if (newLevel != level){
            var newLevelEvent = new PlayerNewLevelEvent(player, newLevel);
            Bukkit.getServer().getPluginManager().callEvent(newLevelEvent);
            if (!event.isCancelled()){
                levelModel.setLevel(newLevel);
            }
        }

        playerStorage.save();
    }

    public int getLevel(Player player){
        return playerStorage.get(player).getPlayerLevel().getLevel();
    }

    public int getExp(Player player){
        return playerStorage.get(player).getPlayerLevel().getExp();
    }

    @Override
    public ILevelTable getLevelTable(Player player) {
        return playerStorage.get(player).getLevelTable();
    }


}
