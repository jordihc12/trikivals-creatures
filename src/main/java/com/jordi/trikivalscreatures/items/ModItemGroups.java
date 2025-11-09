package com.jordi.trikivalscreatures.items;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup TRIKIVALS_CREATURES_GROUP = Registry.register(Registries.ITEM_GROUP,
        new Identifier(TrikiValsCreatures.MOD_ID, "trikivals_items_group"), FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.trikivals-creatures.creatures"))
            .icon(() -> new ItemStack(ModItems.PEJELAGARTO_SPAWN_EGG))
            .entries((displayContext, entries) -> {
                //Add Pejelagarto Eggs here
                entries.add(ModItems.PEJELAGARTO_SPAWN_EGG);
            }).build());

    public static void registerItemGroups() {
        TrikiValsCreatures.LOGGER.info("Registering Mod Item Groups for " + TrikiValsCreatures.MOD_ID);


    }
}
