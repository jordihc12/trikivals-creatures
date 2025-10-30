package com.jordi.trikivalscreatures.block;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block PEJELAGARTO_EGG_BLOCK = registerBlock("pejelagarto_egg", new PejelagartoEggBlock(FabricBlockSettings.copyOf(Blocks.SNIFFER_EGG)));

    private static Block registerBlockWithItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TrikiValsCreatures.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(TrikiValsCreatures.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TrikiValsCreatures.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        TrikiValsCreatures.LOGGER.info("Registering Mod Blocks for " + TrikiValsCreatures.MOD_ID);

    }

}
