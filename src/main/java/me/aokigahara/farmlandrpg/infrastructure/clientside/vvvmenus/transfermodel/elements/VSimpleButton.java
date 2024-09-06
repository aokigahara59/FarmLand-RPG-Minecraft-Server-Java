package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.elements;


import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.utils.actions.PlayerAction;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.MenuProcessor;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.VElement;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.Margin;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.WidthAnchor;

public class VSimpleButton extends VElement {

    private Margin margin = new Margin(0);
    private WidthAnchor widthAnchor;

    private final int baseWidth;
    private final int baseHeight;
    protected boolean isClicked = false;
    protected String message;


    public VSimpleButton(int width, int height, Margin margin, WidthAnchor widthAnchor, String message, PlayerAction action) {
        this.margin = margin;
        this.widthAnchor = widthAnchor;
        baseWidth = width;
        baseHeight = height;
        this.message = message;

        FarmLandRpg.getInjector().getInstance(MenuProcessor.class).addAction(this,action);
    }


    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
