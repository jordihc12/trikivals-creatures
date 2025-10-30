package com.jordi.trikivalscreatures.items;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.block.ModBlocks;
import com.jordi.trikivalscreatures.block.PejelagartoEggBlock;
import com.jordi.trikivalscreatures.entity.pejelagarto.custom.PejelagartoVariant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup TRIKIVALS_CREATURES_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TrikiValsCreatures.MOD_ID, "pejelagarto_egg_pink"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.trikivals-creatures.creatures"))
                    .icon(() -> new ItemStack(ModItems.PEJELAGARTO_SPAWN_EGG)).entries(((displayContext, entries) -> {

                        ItemStack greenStack = new ItemStack(ModItems.PEJELAGARTO_EGG_ITEM);

                        BlockState greenState = ModBlocks.PEJELAGARTO_EGG_BLOCK.getDefaultState();

                        NbtCompound greenNbt = NbtHelper.fromBlockState(greenState.with(PejelagartoEggBlock.VARIANT, PejelagartoVariant.GREEN));
                        greenStack.getOrCreateNbt().put(BlockItem.BLOCK_STATE_TAG_KEY, greenNbt);
                        entries.add(greenStack);

                        ItemStack pinkStack = new ItemStack(ModItems.PEJELAGARTO_EGG_ITEM);

                        BlockState pinkState = ModBlocks.PEJELAGARTO_EGG_BLOCK.getDefaultState();

                        NbtCompound pinkNbt = NbtHelper.fromBlockState(pinkState.with(PejelagartoEggBlock.VARIANT, PejelagartoVariant.PINK));

                        pinkStack.getOrCreateNbt().put(BlockItem.BLOCK_STATE_TAG_KEY, pinkNbt);
                        entries.add(pinkStack);

                        entries.add(ModItems.PEJELAGARTO_SPAWN_EGG);

                    })).build());

    public static void registerItemGroups() {
        TrikiValsCreatures.LOGGER.info("Registering Mod Item Groups for " + TrikiValsCreatures.MOD_ID);


    }
}
