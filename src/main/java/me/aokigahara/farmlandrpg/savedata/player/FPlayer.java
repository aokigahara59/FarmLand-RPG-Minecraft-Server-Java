package me.aokigahara.farmlandrpg.savedata.player;

import me.aokigahara.farmlandrpg.savedata.player.leveling.ILevelTable;
import me.aokigahara.farmlandrpg.savedata.player.leveling.DefaultLevelTable;
import me.aokigahara.farmlandrpg.savedata.player.business.PlayerBusinessData;
import me.aokigahara.farmlandrpg.savedata.player.leveling.PlayerLevel;
import me.aokigahara.farmlandrpg.savedata.player.leveling.SkillType;
import me.aokigahara.farmlandrpg.savedata.player.quest.PlayerQuestData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class FPlayer {
    private final PlayerQuestData questData;
    private final PlayerBusinessData businessData;
    private final UUID playerUUID;
    private long money;

    private final PlayerLevel playerLevel;
    private ILevelTable levelTable;

    private final HashMap<SkillType, PlayerLevel> skillsLevels;
    private final HashMap<SkillType, ILevelTable> skillsLevelsTables;



    private FPlayer(Player player) {
        playerUUID = player.getUniqueId();
        questData = new PlayerQuestData();
        businessData = new PlayerBusinessData();
        skillsLevels = new HashMap<>();
        skillsLevelsTables = new HashMap<>();
        playerLevel = new PlayerLevel();
        levelTable = new DefaultLevelTable();
    }

    public static FPlayer create(Player player){
        var fplayer = new FPlayer(player);
        fplayer.skillsLevels.put(SkillType.Chopping, new PlayerLevel());
        fplayer.skillsLevels.put(SkillType.Mining, new PlayerLevel());
        fplayer.skillsLevels.put(SkillType.Farming, new PlayerLevel());

        fplayer.skillsLevelsTables.put(SkillType.Chopping, new DefaultLevelTable());
        fplayer.skillsLevelsTables.put(SkillType.Mining, new DefaultLevelTable());
        fplayer.skillsLevelsTables.put(SkillType.Farming, new DefaultLevelTable());

        fplayer.money = 0;

        return fplayer;
    }


    public ILevelTable getLevelTable() {
        return levelTable;
    }

    public ILevelTable getSkillLevelTable(SkillType skillType){
        return skillsLevelsTables.get(skillType);
    }

    public PlayerLevel getSkillLevel(SkillType skillType){
        return skillsLevels.get(skillType);
    }

    public PlayerLevel getPlayerLevel() {
        return playerLevel;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public PlayerQuestData getQuestData() {
        return questData;
    }

    public PlayerBusinessData getBusinessData() {
        return businessData;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

}
