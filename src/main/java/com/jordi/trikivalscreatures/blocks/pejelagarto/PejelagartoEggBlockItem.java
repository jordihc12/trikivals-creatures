package com.jordi.trikivalscreatures.blocks.pejelagarto;

import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PejelagartoEggBlockItem extends BlockItem {
    public PejelagartoEggBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    protected boolean postPlacement(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        boolean bl = super.postPlacement(pos, world, player, stack, state);
        if(getBlock() instanceof PejelagartoEggBlock pejelagartoEggBlock) {
            PejelagartoVariant variant = PejelagartoVariant.PLAINS;

            BlockStateComponent blockStateComponent = stack.get(DataComponentTypes.BLOCK_STATE);
            if(blockStateComponent != null && !blockStateComponent.isEmpty()) {
                variant = blockStateComponent.getValue(PejelagartoEggBlock.VARIANT);
            }

            pejelagartoEggBlock.setVariant(variant);
        }

        return bl;
    }
}
