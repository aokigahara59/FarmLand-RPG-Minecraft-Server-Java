package me.aokigahara.farmlandrpg.application.utils;

import java.util.concurrent.TimeUnit;

public class AsyncUtils {

    public static void sleep(long milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
