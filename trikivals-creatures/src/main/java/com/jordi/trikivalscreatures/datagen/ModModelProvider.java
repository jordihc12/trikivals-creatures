package com.jordi.trikivalscreatures.datagen;

import com.jordi.trikivalscreatures.blocks.ModBlocks;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlock;
import com.jordi.trikivalscreatures.client.model.ModModels;
import com.jordi.trikivalscreatures.datagen.properties.PejelagartoVariantProperty;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import com.jordi.trikivalscreatures.items.ModItems;
import com.jordi.trikivalscreatures.utils.ModModelUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.item.ItemAsset;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.BiConsumer;

//Helper class that generates custom mod data
public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void registerPejelagartoEggItem(Item item) {

    }

    private Identifier getPejelagartoEggModel(Integer eggs, PejelagartoVariant variant, Integer hatch,
                                                BiConsumer<Identifier, ModelSupplier> modelCollector) {
        return ModModelUtils.getEggHatchModelTemplateWithVariant(hatch, eggs, ModBlocks.PEJELAGARTO_EGG,
                variant.asString(), modelCollector,
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
            VariantsBlockModelDefinitionCreator.of(ModBlocks.PEJELAGARTO_EGG)
                .with(
                    BlockStateVariantMap.models(PejelagartoEggBlock.EGGS, PejelagartoEggBlock.VARIANT, PejelagartoEggBlock.HATCH)
                        .generate(((eggInt, variant, hatchInt) ->
                            BlockStateModelGenerator.modelWithYRotation(
                                BlockStateModelGenerator.createModelVariant(
                                        this.getPejelagartoEggModel(eggInt, variant, hatchInt, blockStateModelGenerator.modelCollector)))))
                )
        );
    }

    private void generatePejelagartoEggItemModel(ItemModelGenerator itemModelGenerator) {
        ItemModel.Unbaked unbakedAlbino = ItemModels.basic(
                itemModelGenerator.registerSubModel(ModItems.PEJELAGARTO_EGG_ITEM, "_albino", Models.GENERATED));
        ItemModel.Unbaked unbakedJungle = ItemModels.basic(
                itemModelGenerator.registerSubModel(ModItems.PEJELAGARTO_EGG_ITEM, "_jungle", Models.GENERATED));
        ItemModel.Unbaked unbakedPink = ItemModels.basic(
                itemModelGenerator.registerSubModel(ModItems.PEJELAGARTO_EGG_ITEM, "_pink", Models.GENERATED));
        ItemModel.Unbaked unbakedPlains = ItemModels.basic(
                itemModelGenerator.registerSubModel(ModItems.PEJELAGARTO_EGG_ITEM, "_plains", Models.GENERATED));
        ItemModel.Unbaked unbakedSand = ItemModels.basic(
                itemModelGenerator.registerSubModel(ModItems.PEJELAGARTO_EGG_ITEM, "_sand", Models.GENERATED));

        itemModelGenerator.output.accept(
                ModItems.PEJELAGARTO_EGG_ITEM,
                ItemModels.select(new PejelagartoVariantProperty(),
                        ItemModels.switchCase(PejelagartoVariant.ALBINO, unbakedAlbino),
                        ItemModels.switchCase(PejelagartoVariant.JUNGLE, unbakedJungle),
                        ItemModels.switchCase(PejelagartoVariant.PINK, unbakedPink),
                        ItemModels.switchCase(PejelagartoVariant.PLAINS, unbakedPlains),
                        ItemModels.switchCase(PejelagartoVariant.SAND, unbakedSand)
                )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PEJELAGARTO_SPAWN_EGG, Models.GENERATED);
        generatePejelagartoEggItemModel(itemModelGenerator);
    }
}
