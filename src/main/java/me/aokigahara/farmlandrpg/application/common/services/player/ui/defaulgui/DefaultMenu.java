package me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultMenu {

    private final List<DefaultButton> buttons = new ArrayList<>();
    private final int size;
    private DefaultMenu parent;

    protected DefaultMenu(int size) {
        this.size = size;
    }

    protected DefaultMenu(int size, DefaultMenu parent) {
        this.size = size;
        this.parent = parent;
    }



    protected final void addButton(DefaultButton button){
        buttons.add(button);
    }

    protected abstract String getTitle();


    public final void displayTo(Player player){
        var inventory = Bukkit.createInventory(player, size, getTitle());

        for (var x : buttons){
            inventory.setItem(x.getSlot(), x.getItem());
        }

        player.openInventory(inventory);
        player.setMetadata("DefaultMenuInformation", new FixedMetadataValue(FarmLandRpg.getInstance(), this));
    }


    public List<DefaultButton> getButtons() {
        return buttons;
    }

    public DefaultMenu getParent() {
        return parent;
    }

    protected static class DefaultMenuSize{
        public static final int ONE_ROW = 9;
        public static final int TWO_ROWS = 18;
        public static final int THREE_ROWS = 27;
        public static final int FOUR_ROWS = 36;
        public static final int FIVE_ROWS = 45;
        public static final int SIX_ROWS = 54;

    }
}
