package com.jordi.trikivalscreatures.block;

import com.jordi.trikivalscreatures.entity.pejelagarto.custom.PejelagartoVariant;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class PejelagartoEggBlock extends Block {

    public static final IntProperty HATCH = Properties.HATCH;
    public static final EnumProperty<PejelagartoVariant> VARIANT = EnumProperty.of("variant", PejelagartoVariant.class);
    private static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 2.0, 15.0, 16.0, 14.0);

    public PejelagartoEggBlock(AbstractBlock.Settings settings, PejelagartoVariant variant) {
        super(settings);

        this.setDefaultState(this.stateManager.getDefaultState().with(VARIANT, variant));
    }

    public PejelagartoEggBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(VARIANT, PejelagartoVariant.GREEN));
    }

    public void setVariant(BlockState state, PejelagartoVariant variant) {
        this.setDefaultState(state.with(VARIANT, variant));
    }

    public void setVariant(PejelagartoVariant variant) {
        this.setDefaultState(this.stateManager.getDefaultState().with(VARIANT, variant));
    }

    public PejelagartoVariant getVariantState(BlockState state) {
        return state.get(VARIANT);
    }

    public PejelagartoVariant getVariantState() {
        return this.getDefaultState().get(VARIANT);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack stack = super.getPickStack(world, pos, state);
        NbtCompound nbt = NbtHelper.fromBlockState(state);
        stack.getOrCreateSubNbt(BlockItem.BLOCK_STATE_TAG_KEY).copyFrom(nbt);

        return stack;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VARIANT);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
