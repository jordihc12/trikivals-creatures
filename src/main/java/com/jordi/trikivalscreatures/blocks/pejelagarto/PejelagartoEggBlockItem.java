package com.jordi.trikivalscreatures.blocks.pejelagarto;

import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

        NbtCompound nbt = stack.getNbt();
        if(nbt == null) return bl;

        if(!world.isClient
                && !bl
                && player != null
                && world.getBlockEntity(pos) instanceof PejelagartoEggBlockEntity pejelagartoEggBlockEntity
                && world.getBlockState(pos).getBlock() instanceof PejelagartoEggBlock pejelagartoEggBlock) {
            pejelagartoEggBlock.addEgg(
                pejelagartoEggBlockEntity,
                PejelagartoVariant.byId(nbt.getInt("variant")));
        }

        return bl;
    }
}
