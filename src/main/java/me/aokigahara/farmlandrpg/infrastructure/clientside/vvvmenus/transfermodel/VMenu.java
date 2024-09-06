package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel;


public class VMenu {
    private String title;
    private VDiv mainDiv;

    public VMenu(String title, VDiv mainDiv) {
        this.title = title;
        this.mainDiv = mainDiv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VDiv getMainDiv() {
        return mainDiv;
    }

    public void setMainDiv(VDiv mainDiv) {
        this.mainDiv = mainDiv;
    }
}
