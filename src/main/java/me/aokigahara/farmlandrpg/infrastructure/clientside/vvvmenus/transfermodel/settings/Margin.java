package me.aokigahara.farmlandrpg.infrastructure.clientside.vvvmenus.transfermodel.settings;

public class Margin {
    private int top;
    private int left;
    private int bottom;
    private int right;

    public Margin(int margin) {
        top = left = right = bottom = margin;
    }

    public Margin(int top, int side) {
        this.top = bottom = top;
        left = right = side;
    }

    public Margin(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }


    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
