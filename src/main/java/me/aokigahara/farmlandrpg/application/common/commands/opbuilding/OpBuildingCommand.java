package me.aokigahara.farmlandrpg.application.common.commands.opbuilding;

import com.google.inject.Inject;
import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.builder.ISavePasteHook;
import me.aokigahara.farmlandrpg.application.utils.Tuple;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class OpBuildingCommand implements CommandExecutor {

    private static final HashMap<UUID, Tuple<Location, Location>> locationsBuffer = new HashMap<>();


    @Inject private ISavePasteHook ISavePasteHook;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player player)) {
            return true;
        }

        var firstArg = strings[0].toLowerCase();

        switch (firstArg){
            case "schemaworks" -> {

                var secondArg = strings[1];

                switch (secondArg){
                    case "save" ->{

                        var thirdArg = strings[2];
                        var buffer = locationsBuffer.getOrDefault(player.getUniqueId(), new Tuple<>(null, null));
                        switch (thirdArg){
                            case "pos1" -> {
                                buffer.setFirst(player.getLocation());
                                locationsBuffer.put(player.getUniqueId(), buffer);

                            }
                            case "pos2" ->{
                                buffer.setSecond(player.getLocation());
                                locationsBuffer.put(player.getUniqueId(), buffer);
                            }

                            case "saveFile" ->{
                                var fileName = strings[3];

                                var file = new File(FarmLandRpg.getInstance().getDataFolder(), "schematics/"+fileName+".schem");
                                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();

                                ISavePasteHook.save(buffer.getFirst(), buffer.getSecond(), file);
                            }
                        }
                    }

                    case "paste" ->{
                        var fileName = strings[2];
                        var file = new File(FarmLandRpg.getInstance().getDataFolder(), "schematics/"+fileName+".schem");

                        ISavePasteHook.paste(player.getLocation(), file);
                    }
                }

            }
        }

        return false;
    }
}
