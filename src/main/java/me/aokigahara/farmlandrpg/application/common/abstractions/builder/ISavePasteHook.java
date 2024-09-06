package me.aokigahara.farmlandrpg.application.common.abstractions.builder;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.io.File;
import java.util.HashSet;

public interface ISavePasteHook {

    void save(Location first, Location second, File schematicsFile);
    HashSet<Block> paste(Location pasteLocation, File schematicsFile);
}
