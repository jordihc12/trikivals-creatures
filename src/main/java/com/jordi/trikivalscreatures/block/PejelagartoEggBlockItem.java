package com.jordi.trikivalscreatures.block;

import com.jordi.trikivalscreatures.entity.pejelagarto.custom.PejelagartoVariant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

public class PejelagartoEggBlockItem extends BlockItem {
    public PejelagartoEggBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    protected @Nullable BlockState getPlacementState(ItemPlacementContext context) {
        NbtCompound nbt = context.getStack().getNbt();
        if(nbt == null) return super.getPlacementState(context);
        NbtCompound blockStateTag = nbt.getCompound(BlockItem.BLOCK_STATE_TAG_KEY);
        if(blockStateTag.isEmpty()) return super.getPlacementState(context);

        NbtCompound propertiesTag = blockStateTag.getCompound("Properties");
        if(propertiesTag.isEmpty()) return super.getPlacementState(context);

        if(getBlock() instanceof PejelagartoEggBlock pejelagartoEggBlock) {
            pejelagartoEggBlock.setVariant(PejelagartoVariant.byName(propertiesTag.getString("variant")));
        }

        return super.getPlacementState(context);
    }
}
