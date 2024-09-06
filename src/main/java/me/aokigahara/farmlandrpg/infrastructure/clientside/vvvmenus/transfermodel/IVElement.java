package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel;


import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.Margin;
import me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings.WidthAnchor;

public interface IVElement {
    int getWidth();
    void setWidth(int width);
    int getHeight();
    void setHeight(int height);
    Margin getMargin();
    void setMargin(Margin margin);
    WidthAnchor getWidthAnchor();
    void setWidthAnchor(WidthAnchor widthAnchor);
}
