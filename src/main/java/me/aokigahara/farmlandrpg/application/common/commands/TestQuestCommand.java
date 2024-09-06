package me.aokigahara.farmlandrpg.application.common.commands;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.combat.models.GunSettings;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.ICrossplatformDataManager;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.IMoneySystem;
import me.aokigahara.farmlandrpg.application.dialogue.models.Dialogue;
import me.aokigahara.farmlandrpg.application.dialogue.models.Message;
import me.aokigahara.farmlandrpg.application.item.events.PlayerGetItemEvent;
import me.aokigahara.farmlandrpg.application.item.models.ItemRareness;
import me.aokigahara.farmlandrpg.application.item.models.ItemType;
import me.aokigahara.farmlandrpg.application.item.services.InventoryHelper;
import me.aokigahara.farmlandrpg.application.item.services.buidler.BaseItemBuilder;
import me.aokigahara.farmlandrpg.application.item.services.buidler.GunItemBuilder;
import me.aokigahara.farmlandrpg.application.item.services.buidler.ToolItemBuilder;
import me.aokigahara.farmlandrpg.application.quest.abstractions.IQuestService;
import me.aokigahara.farmlandrpg.application.quest.services.QuestBehaviours;
import me.aokigahara.farmlandrpg.application.utils.actions.PlayerAction;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TestQuestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player player)) return false;

        var questService = FarmLandRpg.getInjector().getInstance(IQuestService.class);
        var dataManager = FarmLandRpg.getInjector().getInstance(ICrossplatformDataManager.class);
        var moneySystem = FarmLandRpg.getInjector().getInstance(IMoneySystem.class);

        switch (strings[0]){
            case "setMoney" ->{
                var money = Long.parseLong(strings[1]);

                moneySystem.set(player, money);
            }

            case "testItem" -> {
                var item = new BaseItemBuilder<>(Material.OAK_PLANKS)
                        .setName(ChatColor.GREEN + "Доски Дуба Ругару")
                        .addDescriptionLine(ChatColor.WHITE, "Ну и ну")
                        .setItemRareness(ItemRareness.Common)
                        .setItemPrice(15)
                        .build();

                InventoryHelper.giveItemToPlayer(player, item, 1, PlayerGetItemEvent.GotItemType.PickedUp);


                var item2 = new ToolItemBuilder(Material.STONE_PICKAXE)
                        .setName(ChatColor.LIGHT_PURPLE + "Кирка Неверленда")
                        .addDescriptionLine(ChatColor.GREEN+ "Ну и ну")
                        .addDescriptionLine(ChatColor.GREEN+ "инструмент....")
                        .setToolType(ItemType.Pickaxe)
                        .setItemRareness(ItemRareness.Heroic)
                        .setItemPrice(3000)
                        .build();

                InventoryHelper.giveItemToPlayer(player, item2, 1, PlayerGetItemEvent.GotItemType.PickedUp);
            }

            case "addBlaster" ->{
                var pistol = new GunItemBuilder(Material.IRON_HORSE_ARMOR)
                        .setGunSettings(new GunSettings(3, 20, false, 10, 3000, 500))
                        .setName(ChatColor.GREEN + "[1] Бластер 22ln")
                        .addDescriptionLine(ChatColor.YELLOW,"<<M<")
                        .setItemRareness(ItemRareness.Uncommon)
                        .setModelData(6)
                        .build();


                var auto = new GunItemBuilder(Material.IRON_HORSE_ARMOR)
                        .setGunSettings(new GunSettings(2, 40, true, 20, 3000, 120))
                        .setName(ChatColor.GREEN + "[1] AR-15 22ln AUTO")
                        .addDescriptionLine(ChatColor.YELLOW,"<<M<")
                        .setItemRareness(ItemRareness.Uncommon)
                        .setModelData(1)
                        .build();


                player.getInventory().addItem(pistol, auto);
            }

            case "setCrossPlatformData" ->{
                var string = strings[1];

                dataManager.setData(player, "test_crossplatform_data", string);
            }
            case "getCrossPlatformData" ->{

                var result = dataManager.geData(player, "test_crossplatform_data", String.class);
                player.sendMessage(result);
            }

            case "print" ->{


                var quests = questService.getAll(player);
                for (var x : quests){

                    player.sendMessage("-------------");
                    player.sendMessage("");

                    player.sendMessage(ChatColor.GREEN + "Title: " + ChatColor.WHITE + x.getQuestBehaviour().getTitle());
                    player.sendMessage(ChatColor.GREEN + "Desc: " + ChatColor.WHITE + x.getQuestBehaviour().getDescription());
                    player.sendMessage(ChatColor.GREEN + "Phases: ");

                    for (var y : x.getCurrentPhases()){
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GREEN + "Title: " + ChatColor.WHITE + y.getPhaseBehaviour().getTitle());
                        player.sendMessage(ChatColor.GREEN + "Desc: " + ChatColor.WHITE + y.getPhaseBehaviour().getDescription());

                        player.sendMessage(ChatColor.GREEN + "Progress: " + ChatColor.WHITE + y.getPhaseProgress());
                        player.sendMessage(ChatColor.GREEN + "Done: " + ChatColor.WHITE + y.isDone());
                    }


                    player.sendMessage("");
                    player.sendMessage("-------------");
                    player.sendMessage("");

                }
            }

            case "addQuest" -> {
                questService.execute(player, QuestBehaviours.TestQuestBehaviour.getQuest());
            }


            case "testDialog" ->{
                var dialogue = new Dialogue(player) {
                    @Override
                    public void setDialogue() {
                        var first = new Message("YngXrist", "Есть работа?", List.of("Продолжить"));
                        var second = new Message("Дядя дядя", "Да, будешь копать?", List.of("Да", "Нет, не по мне"));
                        var yesChoose = new Message("Дядя дядя", "Ну тоогда приступай. Мне нужно 5 угля", List.of("Ok"));

                        PlayerAction nullAction = (player) ->{};

                        addMessage(first, nullAction);
                        addMessage(first, "Продолжить", second, nullAction);
                        addMessage(second, "Да", yesChoose, (player -> {
                            questService.execute(player, QuestBehaviours.TestQuestBehaviour.getQuest());
                        }));
                    }
                };

                dialogue.start();
            }

            case "completeQuest" -> {
                questService.completeQuest(player, QuestBehaviours.TestQuestBehaviour.getQuest());
            }

            case "remove" -> {

              questService.removeQuest(player, QuestBehaviours.TestQuestBehaviour.getQuest());

            }
        }

        return true;
    }
}
