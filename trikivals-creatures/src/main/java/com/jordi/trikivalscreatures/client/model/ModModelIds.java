package com.jordi.trikivalscreatures.client.model;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModModelIds {
    public static Identifier getNamespacedBlock(String name) {
        return Identifier.of(TrikiValsCreatures.MOD_ID, "block/" + name);
    }

    public static Identifier getNamespacedItem(String name) {
        return Identifier.of(TrikiValsCreatures.MOD_ID, "item/" + name);
    }

    public static Identifier getBlockSubModelId(Block block, String suffix) {
        Identifier identifier = Registries.BLOCK.getId(block);
        return identifier.withPath((UnaryOperator<String>)(path -> "block/" + path + suffix));
    }

    public static Identifier getBlockModelId(Block block) {
        Identifier identifier = Registries.BLOCK.getId(block);
        return identifier.withPrefixedPath("block/");
    }

    public static Identifier getItemModelId(Item item) {
        Identifier identifier = Registries.ITEM.getId(item);
        return identifier.withPrefixedPath("item/");
    }

    public static Identifier getItemSubModelId(Item item, String suffix) {
        Identifier identifier = Registries.ITEM.getId(item);
        return identifier.withPath((UnaryOperator<String>)(path -> "item/" + path + suffix));
    }
}
