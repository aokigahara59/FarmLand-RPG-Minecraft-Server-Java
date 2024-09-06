package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.elements;


import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.VElement;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.Margin;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.WidthAnchor;

public class VTextElement extends VElement {

    private String text;
    private int color;
    private boolean shadow;
    private float scale;

    public VTextElement(String text, Margin margin, WidthAnchor widthAnchor, int color, boolean shadow, float scale) {
        this.color = color;
        this.shadow = shadow;
        this.text = text;
        this.scale = scale;
        this.margin = margin;
        this.widthAnchor = widthAnchor;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
