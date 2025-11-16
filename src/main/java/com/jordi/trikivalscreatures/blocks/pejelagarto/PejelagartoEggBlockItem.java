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
        if(getBlock() instanceof PejelagartoEggBlock pejelagartoEggBlock) {
            PejelagartoVariant variant = PejelagartoVariant.PLAINS;

            NbtCompound nbt = stack.getNbt();
            if(nbt == null) return bl;
            NbtCompound blockStateTag = nbt.getCompound(BlockItem.BLOCK_STATE_TAG_KEY);
            if(blockStateTag.isEmpty()) return bl;
            if(blockStateTag.contains("variant"))
                variant =  PejelagartoVariant.byName(blockStateTag.getString("variant"));
            else {
                NbtCompound propertiesTag = blockStateTag.getCompound("Properties");
                if(propertiesTag.isEmpty()) return bl;
                if(propertiesTag.contains("variant"))
                    variant =  PejelagartoVariant.byName(propertiesTag.getString("variant"));
            }
            pejelagartoEggBlock.setVariant(variant);
        }

        return bl;
    }
}
