package me.aokigahara.farmlandrpg.application.common.realizations.gui.business.defaults;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.bussiness.abstractions.IBuildingWorkSystem;
import me.aokigahara.farmlandrpg.application.bussiness.events.FarmingLandPlaceClick;
import me.aokigahara.farmlandrpg.application.bussiness.realizations.buildings.SimpleWoodGenerator;
import me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui.DefaultButton;
import me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui.DefaultMenu;
import me.aokigahara.farmlandrpg.application.item.services.buidler.BaseItemBuilder;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.BaseBuilding;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DefaultNewBuildingPickMenu extends DefaultMenu {


    private final IBuildingWorkSystem buildingWorkSystem;
    private final FarmingLandPlaceClick placeClickEvent;

    @Override
    protected String getTitle() {
        return "ВЫбирите здание для постойки";
    }

    public DefaultNewBuildingPickMenu(FarmingLandPlaceClick placeClickEvent) {
        super(27);
        this.placeClickEvent = placeClickEvent;
        buildingWorkSystem = FarmLandRpg.getInjector().getInstance(IBuildingWorkSystem.class);


        addButton(new DefaultButton(9) {
            @Override
            public ItemStack getItem() {
                return new BaseItemBuilder<>(Material.OAK_WOOD)
                        .setName(ChatColor.GREEN + "Лесничество [1 lvl]")
                        .build();
            }

            @Override
            public void onClick(Player player) {
                setNewBuilding(new SimpleWoodGenerator());

            }
        });
    }

    private void setNewBuilding(BaseBuilding building){
        var place = placeClickEvent.getPlace();
        var land = buildingWorkSystem.getLendSaveModel(placeClickEvent.getPlayer(), place.getLand());
        buildingWorkSystem.setNewBuilding(placeClickEvent.getPlayer(), land, place, building);
        placeClickEvent.getPlayer().closeInventory();
    }

}
