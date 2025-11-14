package com.jordi.trikivalscreatures.blocks.pejelagarto;

import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class PejelagartoEggBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final IntProperty EGGS = Properties.EGGS;

    public PejelagartoEggBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PejelagartoEggBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(EGGS);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack stack = super.getPickStack(world, pos, state);
        NbtCompound nbt = NbtHelper.fromBlockState(state);
        stack.getOrCreateSubNbt(BlockItem.BLOCK_STATE_TAG_KEY).copyFrom(nbt);

        return stack;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        if (world.getBlockEntity(pos) instanceof PejelagartoEggBlockEntity pejelagartoEggBlockEntity) {
            ItemStack stack = player.getStackInHand(hand);
            if(pejelagartoEggBlockEntity.getEggs().size() < 4 && stack.getItem() instanceof PejelagartoEggBlockItem) {
                NbtCompound nbt = stack.getNbt();
                if(nbt == null) return super.onUse(state, world, pos, player, hand, hit);

                addEgg(pejelagartoEggBlockEntity,
                        PejelagartoVariant.byId(nbt.getInt("variant")));
                stack.decrement(1);

                return ActionResult.SUCCESS;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

//region Add/Remove Helpers
    public void addEgg(PejelagartoEggBlockEntity blockEntity, PejelagartoVariant variant) {
        blockEntity.addEgg(variant);
    }

    private PejelagartoVariant breakEgg(World world, BlockPos pos, BlockState state) {
        if (world.getBlockEntity(pos) instanceof PejelagartoEggBlockEntity pejelagartoEggBlockEntity) {
            world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F,
                    0.9F + world.random.nextFloat() * 0.2F);
            int i = pejelagartoEggBlockEntity.getEggs().size();
            if (i <= 1) {
                world.breakBlock(pos, false);
            } else {
                world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(state));
                world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
            }
            return pejelagartoEggBlockEntity.removeEgg();
        }
        return null;
    }
//endregion

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        this.breakEgg(world, pos, state);
        if(EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, tool) == 0) {
            return;
        }

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(EGGS)) {
            case 1:
                return Block.createCuboidShape(4.0, 0.0, 3.0, 10.0, 7.0, 9.0);
            case 2:
                return Block.createCuboidShape(4.0, 0.0, 3.0, 11.0, 8.0, 14.0);
            case 3:
                return Block.createCuboidShape(4.0, 0.0, 3.0, 15.0, 8.0, 14.0);
            case 4:
                return Block.createCuboidShape(0.0, 0.0, 3.0, 15.0, 8.0, 14.0);
        }
        return super.getOutlineShape(state, world, pos, context);
    }
}
