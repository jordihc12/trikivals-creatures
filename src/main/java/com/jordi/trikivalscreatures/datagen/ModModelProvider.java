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
import net.minecraft.block.Blocks;
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

    private Identifier getPejelagartoEggModel(int eggs, String variant,
                                              TextureMap textures, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        switch (eggs) {
            case 1-> {
                return ModModels.TEMPLATE_PEJELAGARTO_EGG.upload(
                    ModModelIds.getNamespacedBlock(variant + "_pejelagarto_egg"),
                    textures,
                    modelCollector);
            }
            case 2-> {
                return ModModels.TEMPLATE_TWO_PEJELAGARTO_EGGS.upload(
                    ModModelIds.getNamespacedBlock("two_" + variant + "_pejelagarto_egg"),
                    textures,
                    modelCollector);
            }
            case 3-> {
                return ModModels.TEMPLATE_THREE_PEJELAGARTO_EGGS.upload(
                    ModModelIds.getNamespacedBlock("three_" + variant + "_pejelagarto_egg"),
                    textures,
                    modelCollector);
            }
            case 4 -> {
                return ModModels.TEMPLATE_FOUR_PEJELAGARTO_EGGS.upload(
                    ModModelIds.getNamespacedBlock("four_" + variant + "_pejelagarto_egg"),
                    textures,
                    modelCollector);
            }

            default -> throw new UnsupportedOperationException();
        }
    }

    private Identifier getPejelagartoEggModel(Integer eggs, PejelagartoVariant variant,
                                                    BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        return this.getPejelagartoEggModel(eggs, variant.asString(), TextureMap.all(TextureMap.getSubId(ModBlocks.PEJELAGARTO_EGG, "_" + variant.asString())), modelCollector);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //Register Pejelagarto Eggs
        blockStateModelGenerator.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(ModBlocks.PEJELAGARTO_EGG)
                .coordinate(
                    BlockStateVariantMap.create(PejelagartoEggBlock.EGGS, PejelagartoEggBlock.VARIANT)
                        .registerVariants(((integer, variant) ->
                            Arrays.asList(createModelVariantWithRandomHorizontalRotations(
                                this.getPejelagartoEggModel(integer, variant, blockStateModelGenerator.modelCollector)))))
                )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PEJELAGARTO_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }
}
