package com.jordi.trikivalscreatures.items;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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
                for (PejelagartoVariant variant : PejelagartoVariant.values()) {
                    entries.add(getPejelagartoEggVariantItemStack(variant));
                }

                entries.add(ModItems.PEJELAGARTO_SPAWN_EGG);
            }).build());

    private static ItemStack getPejelagartoEggVariantItemStack(PejelagartoVariant variant) {
        ItemStack stack = new ItemStack(ModItems.PEJELAGARTO_EGG_ITEM);
        NbtCompound nbt = stack.getOrCreateNbt();

        // Set the "Variant" NBT, which your item uses for placement
        nbt.putInt("variant", variant.getId());
        return stack;
    }

    public static void registerItemGroups() {
        TrikiValsCreatures.LOGGER.info("Registering Mod Item Groups for " + TrikiValsCreatures.MOD_ID);


    }
}
