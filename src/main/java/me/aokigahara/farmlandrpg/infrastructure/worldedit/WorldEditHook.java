package me.aokigahara.farmlandrpg.infrastructure.worldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.mask.Mask2D;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockTypes;
import me.aokigahara.farmlandrpg.application.common.abstractions.builder.ISavePasteHook;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.HashSet;

public class WorldEditHook implements ISavePasteHook {

    public void save(Location primary, Location secondary, File schemaFile) {
        BukkitWorld weWorld = new BukkitWorld(primary.getWorld());
        Region region = new CuboidRegion(BukkitAdapter.asBlockVector(primary), BukkitAdapter.asBlockVector(secondary));
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);

        ForwardExtentCopy copy = new ForwardExtentCopy(
                weWorld, region, clipboard, region.getMinimumPoint()
        );

        copy.setSourceMask(new Mask() {
            @Override
            public boolean test(BlockVector3 vector) {
                BlockState block = weWorld.getBlock(vector);
                return !block.getBlockType().equals(BlockTypes.AIR);
            }

            @Nullable
            @Override
            public Mask2D toMask2D() {
                return null;
            }
        });

        try {
            Operations.complete(copy);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }

        try (ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(schemaFile))) {
            try {
                writer.write(clipboard);
            } catch (IOException e) {
                e.printStackTrace();

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public HashSet<Block> paste(Location pasteLocation, File schemaFile) {
        ClipboardFormat format = ClipboardFormats.findByFile(schemaFile);
        if (format == null) {
            return null;
        }

        Clipboard clipboard;
        try (FileInputStream fis = new FileInputStream(schemaFile)) {
            clipboard = format.getReader(fis).read();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        HashSet<Block> placedBlocks = new HashSet<>();
        BukkitWorld weWorld = new BukkitWorld(pasteLocation.getWorld());
        BlockVector3 pastePosition = BlockVector3.at(pasteLocation.getX(), pasteLocation.getY(), pasteLocation.getZ());

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(weWorld)) {
            ClipboardHolder holder = new ClipboardHolder(clipboard);
            Operation operation = holder
                    .createPaste(editSession)
                    .to(pastePosition)
                    .ignoreAirBlocks(true)
                    .build();

            try {
                Operations.complete(operation);
            } catch (WorldEditException e) {
                e.printStackTrace();
                return null;
            }

            clipboard.getRegion().forEach(block -> {
                BlockState blockState = clipboard.getBlock(block);
                if (!blockState.getBlockType().equals(BlockTypes.AIR)) {
                    org.bukkit.block.Block bukkitBlock = weWorld.getWorld().getBlockAt(
                            block.getX() - clipboard.getRegion().getMinimumPoint().getX() + pasteLocation.getBlockX(),
                            block.getY() - clipboard.getRegion().getMinimumPoint().getY() + pasteLocation.getBlockY(),
                            block.getZ() - clipboard.getRegion().getMinimumPoint().getZ() + pasteLocation.getBlockZ()
                    );
                    placedBlocks.add(bukkitBlock);
                }
            });
        }

        return placedBlocks;
    }
}
