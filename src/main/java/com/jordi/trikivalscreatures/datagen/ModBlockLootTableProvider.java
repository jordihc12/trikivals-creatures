package com.jordi.trikivalscreatures.datagen;

import com.jordi.trikivalscreatures.blocks.ModBlocks;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyStateFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private LootTable.Builder pejelagartoEgg(Block drop) {
        return LootTable.builder().pool(LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1.0F))
            .with(ItemEntry.builder(drop)
                .apply(CopyStateFunction.builder(drop).addProperty(PejelagartoEggBlock.VARIANT))
            )
        );
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PEJELAGARTO_EGG, this::pejelagartoEgg);
    }
}
