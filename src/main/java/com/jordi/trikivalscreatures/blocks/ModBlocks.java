package com.jordi.trikivalscreatures.blocks;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlock;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block PEJELAGARTO_EGG = registerBlockWithoutBlockItem("pejelagarto_egg",
            settings ->
                    new PejelagartoEggBlock(settings.mapColor(MapColor.PALE_YELLOW)
                            .solid()
                            .strength(0.5F)
                            .sounds(BlockSoundGroup.METAL)
                            .ticksRandomly()
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)));

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TrikiValsCreatures.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(TrikiValsCreatures.MOD_ID, name), toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(TrikiValsCreatures.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TrikiValsCreatures.MOD_ID, name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TrikiValsCreatures.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TrikiValsCreatures.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        TrikiValsCreatures.LOGGER.info("Registering Mod Blocks for " + TrikiValsCreatures.MOD_ID);

    }
}
