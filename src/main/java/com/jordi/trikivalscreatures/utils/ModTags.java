package com.jordi.trikivalscreatures.utils;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> PEJELAGARTO_SPAWNABLE_ON = createTag("pejelagarto_spawnable_on");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(TrikiValsCreatures.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(TrikiValsCreatures.MOD_ID, name));
        }
    }
}