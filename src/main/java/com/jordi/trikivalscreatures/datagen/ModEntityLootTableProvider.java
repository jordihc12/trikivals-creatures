package com.jordi.trikivalscreatures.datagen;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.util.ModLootTableBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEntityLootTableProvider extends SimpleFabricLootTableProvider {

    private final ModLootTableBuilder tableBuilder;

    private final Map<Identifier, LootTable.Builder> lootTables;

    public ModEntityLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, LootContextTypes.ENTITY);

        this.tableBuilder = new ModLootTableBuilder();
        this.lootTables = new HashMap<>();
    }

    public void generate() {
        //Adding Pejelagarto loot table
        this.lootTables.put(Identifier.of(TrikiValsCreatures.MOD_ID, "entities/pejelagarto"),
            tableBuilder.createTable()
            .addPool(
                tableBuilder.createPool()
                    .addItem(
                        tableBuilder.createItem(Items.DIAMOND)
                            .setPoolCount(0, 1)
                            .setPoolLootingModifier(0, 2)
                            .getItem()
                    ).getPool()
            ).addPool(
                tableBuilder.createPool()
                    .addItem( tableBuilder.createItem(Items.DIAMOND_BLOCK).getItem() )
                    .addPoolCondition(KilledByPlayerLootCondition.builder())
                    .addPoolCondition(RandomChanceWithLootingLootCondition.builder(50.0F, 3.0F))
                    .getPool()
            ).getTable());
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        this.generate();

        this.lootTables.forEach(exporter);
    }
}
