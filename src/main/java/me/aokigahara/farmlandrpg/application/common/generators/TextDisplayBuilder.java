package me.aokigahara.farmlandrpg.application.common.generators;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;

import java.util.Objects;

public class TextDisplayBuilder {
    private String text;
    private Location location;
    private TextDisplay.TextAlignment alignment = TextDisplay.TextAlignment.CENTER;
    private Display.Billboard rotationMode = Display.Billboard.CENTER;
    private int width = -1;
    private int height = -1;
    private int lineWidth = -1;
    private boolean isShadowed = false;
    private Transformation transformation;
    private TextDisplay entity;

    private Color backgroundColor = null;


    private TextDisplayBuilder(){
    }

    public static TextDisplayBuilder create(){
        return new TextDisplayBuilder();
    }
    public TextDisplay build(){
        entity = location.getWorld().spawn(location, TextDisplay.class, text ->{

            text.setText(this.text);
            text.setAlignment(alignment);
            text.setBillboard(rotationMode);

            if (width != -1) text.setDisplayWidth(width);
            if (height != -1) text.setDisplayHeight(height);
            if (lineWidth != -1) text.setLineWidth(lineWidth);

            text.setShadowed(isShadowed);
            if (!Objects.equals(backgroundColor, null)) text.setBackgroundColor(backgroundColor);

            transformation = text.getTransformation();
            //if (transformation != null) text.setTransformation(transformation);


            text.setSeeThrough(false);
            text.setGravity(false);
            text.setRotation(location.getYaw(), 0);
        });


        return entity;
    }

    public TextDisplay getEntity() {
        return entity;
    }

    public TextDisplayBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public TextDisplayBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }

    public TextDisplayBuilder setAlignment(TextDisplay.TextAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public TextDisplayBuilder setRotationMode(Display.Billboard rotationMode) {
        this.rotationMode = rotationMode;
        return this;
    }

    public TextDisplayBuilder setBackgroundColor(Color backgroundColor){
        this.backgroundColor = backgroundColor;
        return this;
    }

    public TextDisplayBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public TextDisplayBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public TextDisplayBuilder setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public TextDisplayBuilder setShadowed(boolean shadowed) {
        isShadowed = shadowed;
        return this;
    }

    public void setTransformation(Transformation transformation) {
        entity.setTransformation(transformation);
    }
}
