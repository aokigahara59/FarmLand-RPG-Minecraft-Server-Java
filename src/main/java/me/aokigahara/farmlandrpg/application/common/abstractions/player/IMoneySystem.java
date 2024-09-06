package me.aokigahara.farmlandrpg.application.common.abstractions.player;

import me.aokigahara.farmlandrpg.application.utils.actionresult.IActionResult;
import org.bukkit.entity.Player;

public interface IMoneySystem {
    void add(Player player, long money);
    IActionResult writeOff(Player player, long money);
    void set(Player player, long money);
}
