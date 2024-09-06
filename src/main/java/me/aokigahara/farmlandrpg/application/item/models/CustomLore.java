package me.aokigahara.farmlandrpg.application.item.models;

import java.io.Serializable;
import java.util.regex.Pattern;

public class CustomLore implements Serializable {

    private final StringBuilder rawLore;
    private final String separator = "\u0825";

    public CustomLore() {
        rawLore = new StringBuilder();
    }

    public String getString(){
        return rawLore.toString();
    }

    public void addString(String string){
        rawLore.append(string).append(separator);
    }

    public String[] getStrings(){
        var pattern = Pattern.compile(separator);
        return pattern.split(rawLore);
    }

    public boolean isEmpty() {
        return rawLore.isEmpty();
    }
}
