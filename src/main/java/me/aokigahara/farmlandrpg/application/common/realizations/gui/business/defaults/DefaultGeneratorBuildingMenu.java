package me.aokigahara.farmlandrpg.application.common.realizations.gui.business.defaults;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IBuildingWorkSystem;
import me.aokigahara.farmlandrpg.application.bussiness.events.PlaceForBuildingChangeEvent;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.IMoneySystem;
import me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui.DefaultButton;
import me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui.DefaultMenu;
import me.aokigahara.farmlandrpg.application.item.services.buidler.BaseItemBuilder;
import me.aokigahara.farmlandrpg.application.utils.actionresult.NotEnoughMoney;
import me.aokigahara.farmlandrpg.application.bussiness.models.Recipe;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.FarmingLand;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.GeneratorBuilding;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.PlaceForBuilding;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.GeneratorBuildingSaveModel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultGeneratorBuildingMenu extends DefaultMenu{
    private final GeneratorBuilding baseBuilding;
    private final GeneratorBuildingSaveModel saveModel;
    private final FarmingLand farmingLand;

    @Override
    protected String getTitle() {
        return baseBuilding.getName();
    }

    public DefaultGeneratorBuildingMenu(GeneratorBuilding baseBuilding, GeneratorBuildingSaveModel saveModel, FarmingLand farmingLand, PlaceForBuilding place) {
        super(DefaultMenuSize.THREE_ROWS);
        this.baseBuilding = baseBuilding;
        this.saveModel = saveModel;
        this.farmingLand = farmingLand;
        var buildingWorkSystem = FarmLandRpg.getInjector().getInstance(IBuildingWorkSystem.class);
        var moneySystem = FarmLandRpg.getInjector().getInstance(IMoneySystem.class);

        addButton(new DefaultButton(9) {
            @Override
            public ItemStack getItem() {
                double power = saveModel.getMaxPower();

                for (var x : saveModel.getRecipes()){
                    power -= (double)  x .getPower() / ((double) x .getTimeInMils()/1000);
                }

                return new BaseItemBuilder<>(Material.BARREL)
                        .setName(baseBuilding.getName())
                        .addDescriptionLine(ChatColor.YELLOW,"Текущий уровень: " + saveModel.getLevel())
                        .addDescriptionLine(ChatColor.YELLOW, "Опыт до след. уровня: " + saveModel.getExp())
                        .addDescriptionLine(ChatColor.YELLOW, "Доступно мощности: " + power
                                + " из " + saveModel.getMaxPower())

                        .build();
            }
            @Override
            public void onClick(Player player) {

            }
        });


        addButton(new DefaultButton(18) {
            @Override
            public ItemStack getItem() {
                var builder =  new BaseItemBuilder<>(Material.ANVIL)
                        .setName(ChatColor.GOLD + "Сделать вклад в здание");

                var requirement =  baseBuilding.getRequirements(saveModel.getLevel()+1);

                if (requirement.needMoney())
                    builder.addDescriptionLine(ChatColor.WHITE + ""+requirement.getMoney() + " серебра");
                if (requirement.needItems())
                    for (var x : requirement.getItems())
                        builder.addDescriptionLine(ChatColor.WHITE + ""+getName(x.getFirst())+" " + x.getSecond() + " шт.");


                return builder.build();
            }

            @Override
            public void onClick(Player player) {
                //TODO:
                var requirement =  baseBuilding.getRequirements(saveModel.getLevel()+1);
                if (requirement == null) return;

                if (requirement.needMoney()){
                    var result = moneySystem.writeOff(player, requirement.getMoney());

                    if (Objects.requireNonNull(result) instanceof NotEnoughMoney notEnoughMoney) {
                        player.sendMessage(ChatColor.RED + "Недостаточно денег! Нужно: " + notEnoughMoney.getRequired());
                        player.closeInventory();
                        return;
                    }
                }

                buildingWorkSystem.setBuildingLevel(player, buildingWorkSystem.getLendSaveModel(player, farmingLand),
                        saveModel, saveModel.getLevel()+1);

                Bukkit.getServer().getPluginManager().callEvent(new PlaceForBuildingChangeEvent(player, place, PlaceForBuildingChangeEvent.ChangeType.NewBuildingLevel));
                new DefaultGeneratorBuildingMenu(baseBuilding, saveModel, farmingLand, place).displayTo(player);
            }
        });


        addButton(new DefaultButton(10) {
            @Override
            public ItemStack getItem() {
                var builder = new BaseItemBuilder<>(Material.CHEST)
                        .setName(ChatColor.GREEN + "Рецепты: ");


                builder.addDescriptionLine(ChatColor.GREEN, "Текущие:");

                saveModel.getRecipes().forEach(x->{
                    builder.addDescriptionLine(ChatColor.DARK_GREEN,ChatColor.BOLD + "- "+ x.getName());
                    x.getResults().forEach(y->{
                        builder.addDescriptionLine(ChatColor.DARK_GREEN,ChatColor.BOLD + "  " + getName(y.getFirst()));
                        builder.addDescriptionLine(ChatColor.DARK_GREEN,ChatColor.BOLD + "    " + y.getSecond() + " за "
                                + String.format("%.2f", (double) x.getTimeInMils()/1000));
                    });
                    builder.addDescriptionLine("");
                });


                return builder.build();
            }

            @Override
            public void onClick(Player player) {
                new RecipesMenu(baseBuilding, saveModel, farmingLand).displayTo(player);
            }
        });


        addButton(new DefaultButton(11) {
            @Override
            public ItemStack getItem() {
                var builder = new BaseItemBuilder<>(Material.CHEST)
                        .setName(ChatColor.GREEN + "Хранилище: ");
                saveModel.getStorage().forEach((key, value) -> {
                        builder.addDescriptionLine(ChatColor.WHITE +
                                getName(key) + ": " + value);
                });

                return builder.build();
            }

            @Override
            public void onClick(Player player) {
                new StorageMenu(baseBuilding, saveModel, farmingLand).displayTo(player);
            }
        });
    }

    private String getName(ItemStack item){
        if (!item.getItemMeta().getDisplayName().isEmpty()) return item.getItemMeta().getDisplayName();
        return item.getType().name();
    }


    protected class StorageMenu extends DefaultMenu{

        private final IBuildingWorkSystem buildingWorkSystem;
        private final GeneratorBuilding baseBuilding;
        private final GeneratorBuildingSaveModel saveModel;
        private final FarmingLand farmingLand;

        @Override
        protected String getTitle() {
            return "Хранилище";
        }

        protected StorageMenu(GeneratorBuilding baseBuilding, GeneratorBuildingSaveModel saveModel, FarmingLand farmingLand) {
            super(DefaultMenuSize.SIX_ROWS);
            this.baseBuilding = baseBuilding;
            this.saveModel = saveModel;
            this.farmingLand = farmingLand;
            buildingWorkSystem = FarmLandRpg.getInjector().getInstance(IBuildingWorkSystem.class);
            var ref = new Object() {
                int position = 0;
            };

            saveModel.getStorage().forEach((key, value)->{
                if (value != 0)
                    addButton(new DefaultButton(ref.position++) {

                        private final ItemStack item = key.clone();

                        @Override
                        public ItemStack getItem() {
                            var item = key.clone();
                            var meta = item.getItemMeta();
                            List<String> lore = Objects.requireNonNullElse(meta.getLore(), new ArrayList<>());
                            lore.addAll(List.of( ChatColor.GRAY+"--------", ChatColor.WHITE+ ""+value + "шт."));
                            meta.setLore(lore);
                            item.setItemMeta(meta);

                            return item;
                        }

                        @Override
                        public void onClick(Player player) {
                            var getAmount = Integer.min(64, saveModel.getStorage().get(key));
                            var landSaveModel = buildingWorkSystem.getLendSaveModel(player, farmingLand);

                            if (getAmount < 1) return;

                            buildingWorkSystem.removeItemFromStorage(player, landSaveModel,saveModel, item, getAmount);


                            var item = key.clone();
                            item.setAmount(getAmount);
                            player.getInventory().addItem(item);

                            new StorageMenu(baseBuilding, saveModel, farmingLand).displayTo(player);
                        }
                    });
            });
        }
    }

    protected class RecipesMenu extends DefaultMenu{

        @Override
        protected String getTitle() {
            return "Текущие рецепты";
        }

        protected RecipesMenu(GeneratorBuilding baseBuilding, GeneratorBuildingSaveModel saveModel, FarmingLand farmingLand) {
            super(DefaultMenuSize.THREE_ROWS);
            var position = 0;
            var buildingWorkSystem = FarmLandRpg.getInjector().getInstance(IBuildingWorkSystem.class);

            for (var x : saveModel.getRecipes()){
                addButton(new DefaultButton(position++) {
                    @Override
                    public ItemStack getItem() {
                        return getItemStackForRecipe(x);
                    }

                    @Override
                    public void onClick(Player player) {
                        buildingWorkSystem.removeRecipe(player, buildingWorkSystem.getLendSaveModel(player, farmingLand),saveModel, x);

                        new RecipesMenu(baseBuilding, saveModel, farmingLand).displayTo(player);
                    }
                });
            }

            addButton(new DefaultButton(position) {
                @Override
                public ItemStack getItem() {
                    return new BaseItemBuilder<>(Material.PAPER)
                            .setName(ChatColor.GOLD + "Добавить новый рецепт")
                            .build();
                }

                @Override
                public void onClick(Player player) {
                    new RecipePickMenu(baseBuilding, saveModel, farmingLand).displayTo(player);
                }
            });
        }

    }


    protected class RecipePickMenu extends DefaultMenu{
        private final IBuildingWorkSystem buildingWorkSystem;
        private final GeneratorBuilding baseBuilding;
        private final GeneratorBuildingSaveModel saveModel;
        private final FarmingLand farmingLand;

        @Override
        protected String getTitle() {
            return "Выбор рецепта";
        }

        protected RecipePickMenu(GeneratorBuilding baseBuilding, GeneratorBuildingSaveModel saveModel, FarmingLand farmingLand) {
            super(DefaultMenuSize.SIX_ROWS);
            this.baseBuilding = baseBuilding;
            this.saveModel = saveModel;
            this.farmingLand = farmingLand;

            buildingWorkSystem = FarmLandRpg.getInjector().getInstance(IBuildingWorkSystem.class);

            var position = 0;
            for (var x : baseBuilding.getRecipes()){
                addButton(new DefaultButton(position++) {
                    @Override
                    public ItemStack getItem() {
                        return getItemStackForRecipe(x);
                    }

                    @Override
                    public void onClick(Player player) {
                        var landSaveModel = buildingWorkSystem.getLendSaveModel(player,farmingLand);

                        buildingWorkSystem.tryAddRecipe(player, landSaveModel,saveModel, x);

                        new RecipesMenu(baseBuilding, saveModel, farmingLand).displayTo(player);
                    }
                });
            }
        }

    }

    public ItemStack getItemStackForRecipe(Recipe x){
        var item = new BaseItemBuilder<>(x.getResults().get(0).getFirst().getType())
                .setName(ChatColor.GREEN + x.getName());

        for (var result : x.getResults()){
            item.addDescriptionLine(ChatColor.YELLOW, " +"+ getName(result.getFirst()));
            item.addDescriptionLine(ChatColor.YELLOW, "   " + result.getSecond() + " за " +
                    String.format("%.2f", (double) x.getTimeInMils()/1000));
        }
        return item.build();
    }

    public ItemStack getItemForRecipe(Recipe x, boolean addRemoveAnnotation) {
        var builder = new BaseItemBuilder<>(x.getResults().get(0).getFirst().getType())
                .setName(ChatColor.GREEN + x.getName());

        for (var y : x.getResults()){
            builder.addDescriptionLine(ChatColor.YELLOW, "  +" + getName(y.getFirst()));
            builder.addDescriptionLine(ChatColor.YELLOW, "    " + y.getSecond() + " за "
                    + String.format("%.2f", (double) x.getTimeInMils()/1000));
        }
        builder.addDescriptionLine("");
        if (addRemoveAnnotation)
            builder.addDescriptionLine(ChatColor.WHITE+ "" + ChatColor.ITALIC + "Нажмите чтобы удалить");
        return builder.build();
    }
}
