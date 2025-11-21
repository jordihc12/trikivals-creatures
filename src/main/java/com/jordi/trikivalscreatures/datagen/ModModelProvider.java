package com.jordi.trikivalscreatures.datagen;

import com.google.gson.JsonElement;
import com.jordi.trikivalscreatures.blocks.ModBlocks;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlock;
import com.jordi.trikivalscreatures.client.model.ModModelIds;
import com.jordi.trikivalscreatures.client.model.ModModels;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import com.jordi.trikivalscreatures.items.ModItems;
import com.jordi.trikivalscreatures.utils.ModModelUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
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

    private Identifier getPejelagartoEggModel(Integer eggs, PejelagartoVariant variant, Integer hatch,
                                                    BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        return ModModelUtils.getEggHatchModelTemplate(hatch, eggs, ModBlocks.PEJELAGARTO_EGG, variant.asString(), modelCollector,
            new Model[] {
                ModModels.TEMPLATE_PEJELAGARTO_EGG,
                ModModels.TEMPLATE_TWO_PEJELAGARTO_EGGS,
                ModModels.TEMPLATE_THREE_PEJELAGARTO_EGGS,
                ModModels.TEMPLATE_FOUR_PEJELAGARTO_EGGS
        });
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
