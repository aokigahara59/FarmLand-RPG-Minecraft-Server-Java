package me.aokigahara.farmlandrpg.application.common.realizations.gui.business.defaults;


import me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui.DefaultButton;
import me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui.DefaultMenu;
import me.aokigahara.farmlandrpg.application.item.services.buidler.BaseItemBuilder;
import me.aokigahara.farmlandrpg.application.bussiness.models.buildings.BaseBuilding;
import me.aokigahara.farmlandrpg.savedata.player.business.buildings.BaseBuildingSaveModel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DefaultBuildingMenu extends DefaultMenu {

    private final BaseBuilding baseBuilding;
    private final BaseBuildingSaveModel saveModel;

    @Override
    protected String getTitle() {
        return baseBuilding.getName();
    }

    public DefaultBuildingMenu(BaseBuilding baseBuilding, BaseBuildingSaveModel saveModel) {
        super(DefaultMenuSize.FIVE_ROWS);
        this.baseBuilding = baseBuilding;
        this.saveModel = saveModel;

        addButton(new DefaultButton(0) {
            @Override
            public ItemStack getItem() {
                return new BaseItemBuilder<>(Material.BARREL)
                        .setName(baseBuilding.getName())
                        .addDescriptionLine("lvl: " + saveModel.getLevel())
                        .addDescriptionLine("exp: " + saveModel.getExp())
                        .build();
            }

            @Override
            public void onClick(Player player) {

            }
        });

        addButton(new DefaultButton(2) {
            @Override
            public ItemStack getItem() {
                var builder = new BaseItemBuilder<>(Material.CHEST)
                        .setName(ChatColor.GREEN + "Хранилище: ");
                saveModel.getStorage().forEach((key, value) -> {
                    if (key.hasItemMeta())
                        builder.addDescriptionLine(ChatColor.WHITE +
                            key.getItemMeta().getDisplayName()
                            + ": " + value);
                });

                return builder.build();
            }

            @Override
            public void onClick(Player player) {

            }
        });
    }

}
