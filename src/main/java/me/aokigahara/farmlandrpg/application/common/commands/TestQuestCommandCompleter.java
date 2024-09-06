package me.aokigahara.farmlandrpg.application.common.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class TestQuestCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        switch (strings.length){
            case 1: return List.of("print", "testItem","addBlaster", "testDialog", "setMoney", "remove", "addQuest", "completeQuest", "setCrossPlatformData", "getCrossPlatformData");
        }

        return null;
    }
}
