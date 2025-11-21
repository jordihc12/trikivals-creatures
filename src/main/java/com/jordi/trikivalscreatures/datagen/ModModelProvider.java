package com.jordi.trikivalscreatures.datagen;

import com.google.gson.JsonElement;
import com.jordi.trikivalscreatures.blocks.ModBlocks;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlock;
import com.jordi.trikivalscreatures.client.model.ModModelIds;
import com.jordi.trikivalscreatures.client.model.ModModels;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import com.jordi.trikivalscreatures.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static net.minecraft.data.client.BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations;

//Helper class that generates custom mod data
public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private Identifier getEggModelTemplate(int eggs, String entityEggStr, TextureMap textures,
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

    private Identifier getPejelagartoEggHatchModel(int hatch, int eggs, String variant,
                                                   BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        switch (hatch) {
            case 0 -> {
                return getPejelagartoEggModel(eggs, variant + "_pejelagarto_egg",
                        TextureMap.all(TextureMap.getSubId(ModBlocks.PEJELAGARTO_EGG, "_" + variant)), modelCollector);
            }
            case 1 -> {
                return getPejelagartoEggModel(eggs, "slightly_cracked_" + variant + "_pejelagarto_egg",
                        TextureMap.all(TextureMap.getSubId(ModBlocks.PEJELAGARTO_EGG, "_" + variant + "_slightly_cracked")), modelCollector);
            }
            case 2 -> {
                return getPejelagartoEggModel(eggs, "very_cracked_" + variant+ "_pejelagarto_egg",
                        TextureMap.all(TextureMap.getSubId(ModBlocks.PEJELAGARTO_EGG, "_" + variant + "_very_cracked")), modelCollector);
            }
            default -> throw new UnsupportedOperationException();
        }
    }

    private Identifier getPejelagartoEggModel(int eggs, String entityStr,
                                              TextureMap textures, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        return getEggModelTemplate(eggs, entityStr, textures, modelCollector, new Model[] {
            ModModels.TEMPLATE_PEJELAGARTO_EGG,
            ModModels.TEMPLATE_TWO_PEJELAGARTO_EGGS,
            ModModels.TEMPLATE_THREE_PEJELAGARTO_EGGS,
            ModModels.TEMPLATE_FOUR_PEJELAGARTO_EGGS
        });
    }

    private Identifier getPejelagartoEggModel(Integer eggs, PejelagartoVariant variant, Integer hatch,
                                                    BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        return this.getPejelagartoEggHatchModel(hatch, eggs, variant.asString(), modelCollector);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //Register Pejelagarto Eggs
        blockStateModelGenerator.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(ModBlocks.PEJELAGARTO_EGG)
                .coordinate(
                    BlockStateVariantMap.create(PejelagartoEggBlock.EGGS, PejelagartoEggBlock.VARIANT, PejelagartoEggBlock.HATCH)
                        .registerVariants(((eggInt, variant, hatchInt) ->
                            Arrays.asList(createModelVariantWithRandomHorizontalRotations(
                                this.getPejelagartoEggModel(eggInt, variant, hatchInt, blockStateModelGenerator.modelCollector)))))
                )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PEJELAGARTO_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }
}
