package me.aokigahara.farmlandrpg.application.common.commands.opbuilding;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OpBuildingCommandCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        switch (strings.length){
            case 1 -> {
                return List.of("schemaworks");
            }

            case 2 -> {
                var firstArg = strings[0].toLowerCase();

                if (firstArg.equals("schemaworks"))
                    return List.of("save", "paste");
            }

            case 3 -> {
                var firstArg = strings[0].toLowerCase();
                var secondArg = strings[1];

                if (firstArg.equals("schemaworks") && secondArg.equals("save"))
                    return List.of("pos1", "pos2");
            }
        }

        return List.of();
    }
}
