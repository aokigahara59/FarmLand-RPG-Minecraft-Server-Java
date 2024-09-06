package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel;



import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.DisplayMode;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.Margin;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.WidthAnchor;

import java.util.ArrayList;
import java.util.List;

public class VDiv extends VElement {

    protected List<IVElement> elementList;

    protected DisplayMode displayMode;
    protected boolean hasBorder;
    protected int borderColor;
    protected int borderWidth;
    protected boolean hasColor;
    protected int color;


    public VDiv(int width, int height) {
        elementList = new ArrayList<>();
        hasColor = false;
        hasBorder = false;
        this.width = width;
        this.height = height;
        margin = new Margin(0);
        widthAnchor = WidthAnchor.Left;
    }

    public void setColor(int color){
        hasColor = true;
        this.color = color;
    }

    public void setBorderWidth(int borderWidth, int borderColor) {
        hasBorder = true;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
    }

    public void addElement(IVElement element){
        elementList.add(element);
    }

    public void removeElement(IVElement element){
        elementList.remove(element);
    }


    public List<IVElement> getElementList() {
        return elementList;
    }

    public void setElementList(List<IVElement> elementList) {
        this.elementList = elementList;
    }

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }

    public boolean isHasBorder() {
        return hasBorder;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public boolean isHasColor() {
        return hasColor;
    }

    public void setHasColor(boolean hasColor) {
        this.hasColor = hasColor;
    }

    public int getColor() {
        return color;
    }
}
