package me.aokigahara.farmlandrpg.application.combat.models;

import lombok.Data;

@Data
public class GunSettings {
    private int damage;
    private int range;
    private boolean multiShot;
    private int clipSize;
    private int clip;
    private long reloadTimeMs;
    private boolean isReloading = false;
    private long timeBetweenShotsMs;
    private long lastTimeShootMs = 0;

    public GunSettings(int damage, int range, boolean multiShot, int clipSize, long reloadTimeMs, long timeBetweenShotsMs) {
        this.damage = damage;
        this.range = range;
        this.multiShot = multiShot;
        this.clipSize = clipSize;
        clip = clipSize;
        this.reloadTimeMs = reloadTimeMs;
        this.timeBetweenShotsMs = timeBetweenShotsMs;
    }

    public boolean shootTimingPassed(){
        return System.currentTimeMillis() - lastTimeShootMs >= timeBetweenShotsMs;
    }
    public void updateLastShootTime(){
        lastTimeShootMs = System.currentTimeMillis();
    }


    public void reduceClip(int count){
        clip -= count;
    }

}
