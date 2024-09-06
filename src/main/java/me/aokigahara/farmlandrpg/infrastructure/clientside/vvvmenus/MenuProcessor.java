package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPluginMessageByteBuffer;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.ICrossplatformDataManager;
import me.aokigahara.farmlandrpg.application.utils.actions.PlayerAction;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.IVElement;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.VDiv;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.VMenu;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.elements.VSimpleButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MenuProcessor {


    private Map<IVElement, PlayerAction> buttons;

    public MenuProcessor() {
        buttons = new HashMap<>();
    }

    public void addAction(IVElement element, PlayerAction action){
        buttons.put(element, action);
    }

    public void openMenu(Player player, VMenu vMenu){

        var dataManager = FarmLandRpg.getInjector().getInstance(ICrossplatformDataManager.class);

        dataManager.setData(player, "current_menu_info", vMenu);

        var buffer = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);

        buffer.writeBoolean(true);
        player.sendPluginMessage(FarmLandRpg.getInstance(), "farmlandrgp:curent.menu.open", buffer.asByteArray());
    }

    public void processElement(VDiv div, Player player){
        for (var x : div.getElementList()){
            if (x instanceof VDiv vdiv) processElement(vdiv, player);

            if (x instanceof VSimpleButton button){
                button.setClicked(false);
                if (buttons.containsKey(button)){
                    buttons.get(button).execute(player);
                }
            }
        }
    }
}
