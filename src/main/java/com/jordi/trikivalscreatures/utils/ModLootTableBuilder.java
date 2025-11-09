package com.jordi.trikivalscreatures.utils;

import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableBuilder {

    LootTable.Builder table = null;
    LootPool.Builder pool = null;

    LeafEntry.Builder<?> item = null;

    public ModLootTableBuilder createTable() {
        this.table = LootTable.builder();
        return this;
    }

    public ModLootTableBuilder createPool() {
        this.pool = LootPool.builder();
        return this;
    }

    public LootTable.Builder getTable() {
        return this.table;
    }

    public LootPool.Builder getPool() {
        return this.pool;
    }

    public LeafEntry.Builder<?> getItem() {return this.item; }

    public ModLootTableBuilder addPool(LootPool.Builder pool) {
        this.table.pool(pool);
        return this;
    }

    public ModLootTableBuilder addRolls(LootNumberProvider rolls) {
        this.pool.rolls(rolls);
        return this;
    }

    public ModLootTableBuilder addItem(LeafEntry.Builder<?> itemDrop) {
        this.pool.with(itemDrop);
        return this;
    }

    public ModLootTableBuilder createItem(ItemConvertible itemDrop) {
        this.item = ItemEntry.builder(itemDrop);
        return this;
    }

    public ModLootTableBuilder setPoolCount(int min, int max) {
        this.item.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)));
        return this;
    }

    public ModLootTableBuilder setPoolLootingModifier(int min, int max) {
        this.item.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(min, max)));
        return this;
    }

    public ModLootTableBuilder addPoolCondition(LootCondition.Builder condition) {
        this.pool.conditionally(condition);
        return this;
    }
}
