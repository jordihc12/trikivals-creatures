package com.jordi.trikivalscreatures.utils;

import com.google.gson.JsonElement;
import com.jordi.trikivalscreatures.client.model.ModModelIds;
import net.minecraft.block.Block;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureMap;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModModelUtils {

    private static Identifier getEggModelTemplate(int eggs, String entityEggStr, TextureMap textures,
                                           BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Model[] modelTemplates) {
        switch (eggs) {
            case 1 -> {
                return modelTemplates[0].upload(
                        ModModelIds.getNamespacedBlock(entityEggStr),
                        textures,
                        modelCollector);
            }
            case 2 -> {
                return modelTemplates[1].upload(
                        ModModelIds.getNamespacedBlock("two_" + entityEggStr),
                        textures,
                        modelCollector);
            }
            case 3 -> {
                return modelTemplates[2].upload(
                        ModModelIds.getNamespacedBlock("three_" + entityEggStr),
                        textures,
                        modelCollector);
            }
            case 4 -> {
                return modelTemplates[3].upload(
                        ModModelIds.getNamespacedBlock("four_" + entityEggStr),
                        textures,
                        modelCollector);
            }
            default -> throw new UnsupportedOperationException();
        }
    }

    public static Identifier getEggHatchModelTemplateWithVariant(int hatch, int eggs, Block block, String variant,
                                                BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Model[] modelTemplates) {
        String blockId = Registries.BLOCK.getId(block).getPath();
        switch (hatch) {
            case 0 -> {
                return getEggModelTemplate(eggs, variant + "_" + blockId,
                        TextureMap.all(TextureMap.getSubId(block, "_" + variant)), modelCollector, modelTemplates);
            }
            case 1 -> {
                return getEggModelTemplate(eggs, "slightly_cracked_" + variant + "_" + blockId,
                        TextureMap.all(TextureMap.getSubId(block, "_" + variant + "_slightly_cracked")), modelCollector, modelTemplates);
            }
            case 2 -> {
                return getEggModelTemplate(eggs, "very_cracked_" + variant + "_" + blockId,
                        TextureMap.all(TextureMap.getSubId(block, "_" + variant + "_very_cracked")), modelCollector, modelTemplates);
            }
            default -> throw new UnsupportedOperationException();
        }
    }

    public static Identifier getEggHatchModelTemplate(int hatch, int eggs, Block block,
                                                                 BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Model[] modelTemplates) {
        String blockId = Registries.BLOCK.getId(block).getPath();
        switch (hatch) {
            case 0 -> {
                return getEggModelTemplate(eggs, blockId,
                        TextureMap.all(TextureMap.getId(block)), modelCollector, modelTemplates);
            }
            case 1 -> {
                return getEggModelTemplate(eggs, "slightly_cracked_" + blockId,
                        TextureMap.all(TextureMap.getSubId(block, "_slightly_cracked")), modelCollector, modelTemplates);
            }
            case 2 -> {
                return getEggModelTemplate(eggs, "very_cracked_" + blockId,
                        TextureMap.all(TextureMap.getSubId(block, "_very_cracked")), modelCollector, modelTemplates);
            }
            default -> throw new UnsupportedOperationException();
        }
    }
}
