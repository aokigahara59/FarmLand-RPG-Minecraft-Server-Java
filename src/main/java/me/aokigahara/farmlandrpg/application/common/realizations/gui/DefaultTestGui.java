package me.aokigahara.farmlandrpg.application.common.realizations.gui;

import me.aokigahara.farmlandrpg.application.common.services.player.ui.defaulgui.DefaultMenu;

public class DefaultTestGui extends DefaultMenu {
    public DefaultTestGui() {
        super(DefaultMenuSize.THREE_ROWS);
    }

    @Override
    protected String getTitle() {
        return "TestGui";
    }
}
