package com.jordi.trikivalscreatures.items;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlock;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup TRIKIVALS_CREATURES_GROUP = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(TrikiValsCreatures.MOD_ID, "trikivals_items_group"), FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.trikivals-creatures.creatures"))
            .icon(() -> new ItemStack(ModItems.PEJELAGARTO_SPAWN_EGG))
            .entries((displayContext, entries) -> {
                //Adding Pejelagarto Eggs
                ModItemGroups.addPejelagartoEggEntries(entries);

                //Adding Pejelagarto entity Spawn egg
                entries.add(ModItems.PEJELAGARTO_SPAWN_EGG);
            }).build());

    //Add Pejelagarto Egg variants to TrikiVals creatures group
    private static void addPejelagartoEggEntries(ItemGroup.Entries entries) {
        for (PejelagartoVariant variant : PejelagartoVariant.values()) {
            //Create new Stack for the egg variant
            ItemStack pejelagartoEggItemStack = new ItemStack(ModItems.PEJELAGARTO_EGG_ITEM);

            //Request nbt with the variant from PejelagartoEggBlock
            BlockStateComponent blockStateComponent =
                    BlockStateComponent.DEFAULT.with(PejelagartoEggBlock.VARIANT, variant);

            pejelagartoEggItemStack.set(DataComponentTypes.BLOCK_STATE, blockStateComponent);

            //Add pejelagartoEggItemStack to item group
            entries.add(pejelagartoEggItemStack);
        }
    }

    public static void registerItemGroups() { TrikiValsCreatures.LOGGER.info("Registering Mod Item Groups for " + TrikiValsCreatures.MOD_ID); }
}
