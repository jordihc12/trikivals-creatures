package com.jordi.trikivalscreatures.items;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.blocks.ModBlocks;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlockItem;
import com.jordi.trikivalscreatures.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

//Helper class that initializes custom Items
public class ModItems {

    public static final Item PEJELAGARTO_SPAWN_EGG = registerItem("pejelagarto_spawn_egg",
            settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.PEJELAGARTO)));

    public static final Item PEJELAGARTO_EGG_ITEM = registerItem("pejelagarto_egg",
            settings -> new PejelagartoEggBlockItem(ModBlocks.PEJELAGARTO_EGG, settings));

    private static void addItemsToSpawnEggsItemGroup(FabricItemGroupEntries entries) {
        entries.add(PEJELAGARTO_SPAWN_EGG);
    }

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(TrikiValsCreatures.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TrikiValsCreatures.MOD_ID, name)))));
    }

    public static void registerModItems() {
        TrikiValsCreatures.LOGGER.info("Registering Mod items for " + TrikiValsCreatures.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggsItemGroup);
    }
}
