package com.jordi.trikivalscreatures.items;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

//Helper class that initializes custom Items
public class ModItems {

    public static final Item PEJELAGARTO_SPAWN_EGG = registerItem("pejelagarto_spawn_egg",
            new SpawnEggItem(ModEntities.PEJELAGARTO, 0x42523e, 0xe4ed9d,new FabricItemSettings()));

    private static void addItemsToSpawnEggsItemGroup(FabricItemGroupEntries entries) {
        entries.add(PEJELAGARTO_SPAWN_EGG);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TrikiValsCreatures.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TrikiValsCreatures.LOGGER.info("Registering Mod items for " + TrikiValsCreatures.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggsItemGroup);
    }
}
