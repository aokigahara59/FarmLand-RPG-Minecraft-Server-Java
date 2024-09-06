package me.aokigahara.farmlandrpg.application.common.services.player;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.application.common.abstractions.storages.IFPlayerStorage;
import me.aokigahara.farmlandrpg.application.common.abstractions.player.IMoneySystem;
import me.aokigahara.farmlandrpg.application.common.events.PlayerUpdateMoneyEvent;
import me.aokigahara.farmlandrpg.application.utils.actionresult.IActionResult;
import me.aokigahara.farmlandrpg.application.utils.actionresult.NotEnoughMoney;
import me.aokigahara.farmlandrpg.application.utils.actionresult.Success;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MoneySystem implements IMoneySystem {

    @Inject private IFPlayerStorage playerStorage;

    @Override
    public void add(Player player, long money) {
        var fplayer = playerStorage.get(player);
        set(player, fplayer.getMoney()+money);
    }

    @Override
    public IActionResult writeOff(Player player, long money) {
        var fplayer = playerStorage.get(player);

        if (fplayer.getMoney() - money < 0) return new NotEnoughMoney(money);

        set(player, fplayer.getMoney()-money);


        return new Success("Успех");
    }

    @Override
    public void set(Player player, long money) {
        var fplayer = playerStorage.get(player);
        fplayer.setMoney(money);
        Bukkit.getServer().getPluginManager().callEvent(new PlayerUpdateMoneyEvent(player, fplayer.getMoney()));
    }
}
