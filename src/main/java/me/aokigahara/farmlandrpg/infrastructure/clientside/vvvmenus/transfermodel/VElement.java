package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel;


import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.Margin;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.WidthAnchor;

public abstract class VElement implements IVElement {
    protected int width;
    protected int height;
    protected Margin margin;
    protected WidthAnchor widthAnchor;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Margin getMargin() {
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }

    public WidthAnchor getWidthAnchor() {
        return widthAnchor;
    }

    public void setWidthAnchor(WidthAnchor widthAnchor) {
        this.widthAnchor = widthAnchor;
    }
}
