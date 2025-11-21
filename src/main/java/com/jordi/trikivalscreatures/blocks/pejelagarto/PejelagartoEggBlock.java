package com.jordi.trikivalscreatures.blocks.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class PejelagartoEggBlock extends Block {
    public static final IntProperty EGGS = Properties.EGGS;
    public static final IntProperty HATCH = Properties.HATCH;
    public static final EnumProperty<PejelagartoVariant> VARIANT = EnumProperty.of("variant", PejelagartoVariant.class);

    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 12.0, 7.0, 12.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 7.0, 15.0);

    private static final int HATCH_TIME_TICKS = 300;

    //region INITIALISATION
    public PejelagartoEggBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(VARIANT, PejelagartoVariant.PLAINS)
                .with(EGGS, 1)
                .with(HATCH, 0)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(EGGS, VARIANT, HATCH);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(EGGS) > 1 ? LARGE_SHAPE : SMALL_SHAPE;
    }
//endregion

//region VARIANT
    public void setVariant(PejelagartoVariant variant) {
        this.setDefaultState(this.stateManager.getDefaultState().with(VARIANT, variant));
    }

    public PejelagartoVariant getVariantState(BlockState state) {
        return state.get(VARIANT);
    }

    public PejelagartoVariant getVariantState() {
        return this.getDefaultState().get(VARIANT);
    }
//endregion

//region EGG LOGIC
    private void breakEgg(World world, BlockPos pos, BlockState state) {
        world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_BREAK,
                SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
        int i = state.get(EGGS);
        if (i <= 1) {
            world.breakBlock(pos, false);
        } else {
            world.setBlockState(pos, state.with(EGGS, i - 1), Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(state));
            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
        }
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
                           @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        this.breakEgg(world, pos, state);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        boolean bl = isIncubationBlockBelow(world, pos.down());
        TrikiValsCreatures.LOGGER.info("[PejelagartoEggBlock] Block below allows egg incubation: {}", bl);
        if(bl) {
            world.syncWorldEvent(WorldEvents.PLANT_FERTILIZED, pos, 0);
            world.scheduleBlockTick(pos, this, HATCH_TIME_TICKS / 3);   //Hatch time divided by egg stages
        }
    }

    //endregion

//region HATCH AND INCUBATION

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(!readyToHatch(state)) {
            world.playSound(null, pos, SoundEvents.BLOCK_SNIFFER_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            world.setBlockState(pos, state.with(HATCH, state.get(HATCH) + 1), Block.NOTIFY_LISTENERS);
        }
        else {
            world.playSound(null, pos, SoundEvents.BLOCK_SNIFFER_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            world.breakBlock(pos, false);
            for (int i = 0; i < state.get(EGGS); i++) {
                Vec3d vec3d = pos.toCenterPos().add(0.3 + i * 0.2, 0.0 , 0.3);
                PejelagartoEntity baby = PejelagartoEntity.createNewChild(world, vec3d, getVariantState(state));
                if(baby != null) {
                    world.spawnEntity(baby);
                }
            }


        }
    }

    private boolean readyToHatch(BlockState state) {
        return state.get(HATCH) == 2;
    }

    private boolean isIncubationBlockBelow(BlockView world, BlockPos pos) {
        return world.getBlockState(pos).isIn(BlockTags.SAND);   //Override BlockTags.SAND with incubation blocks tag
    }


//endregion

//region MULTI-BLOCK

    private boolean isStackValid(ItemStack stack) {
        if(stack.getItem() instanceof PejelagartoEggBlockItem) {
            NbtCompound nbt = stack.getNbt();
            if(nbt == null) return false;

            NbtCompound blockStateTag = nbt.getCompound(BlockItem.BLOCK_STATE_TAG_KEY);
            if(blockStateTag.isEmpty()) return false;
            if(blockStateTag.contains("variant"))
                return this.getVariantState() == PejelagartoVariant.byName(blockStateTag.getString("variant"));

            NbtCompound propertiesTag = blockStateTag.getCompound("Properties");
            if(propertiesTag.isEmpty()) return false;

            return this.getVariantState() == PejelagartoVariant.byName(propertiesTag.getString("variant"));
        }

        return false;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int eggs = state.get(EGGS);
        if(eggs >= 4) return ActionResult.PASS;
        if(!world.isClient && isStackValid(player.getStackInHand(hand))) {
            world.setBlockState(pos, state.with(EGGS, eggs + 1), NOTIFY_LISTENERS);
            if(!player.isCreative()) {
                player.getStackInHand(hand).decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() &&
                context.getStack().isOf(this.asItem()) && state.get(VARIANT) == this.getVariantState(state) &&
                state.get(EGGS) < 4 || super.canReplace(state, context);
    }
//endregion

    public static NbtCompound getItemBlockStateNbt(PejelagartoVariant variant) {
        NbtCompound nbt = new NbtCompound();
        nbt.putString(PejelagartoEggBlock.VARIANT.getName(), variant.asString());
        return nbt;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack stack = super.getPickStack(world, pos, state);
        stack.getOrCreateNbt().put(BlockItem.BLOCK_STATE_TAG_KEY, PejelagartoEggBlock.getItemBlockStateNbt(state.get(VARIANT)));

        return stack;
    }


}
