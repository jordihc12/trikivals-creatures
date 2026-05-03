package com.jordi.trikivalscreatures.entity.creatures.dracopez;

import com.jordi.trikivalscreatures.items.ModItems;
import com.jordi.trikivalscreatures.utils.ModDataComponents;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Blocks;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class DracoPezEntity extends SchoolingFishEntity {
    public static final DracoPezEntity.Variant DEFAULT_VARIANT = new DracoPezEntity.Variant(DyeColor.WHITE);
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(DracoPezEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public DracoPezEntity(EntityType<? extends SchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

//region DATA
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, DEFAULT_VARIANT.getId());
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.put("Variant", DracoPezEntity.Variant.CODEC, new DracoPezEntity.Variant(this.getDracoPezVariant()));
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        DracoPezEntity.Variant variant = view.read("Variant", DracoPezEntity.Variant.CODEC).orElse(DEFAULT_VARIANT);
        this.setDracoPezVariant(variant.getId());
    }

    private void setDracoPezVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    private int getDracoPezVariant() {
        return this.dataTracker.get(VARIANT);
    }

    static int getVariantId(DyeColor patternColor) {
        return (patternColor.getIndex() & 0xFF) << 24;
    }

    @Nullable
    @Override
    public <T> T get(ComponentType<? extends T> type) {
        if (type == ModDataComponents.DRACOPEZ_COLOR) {
            return castComponentValue(type, new DracoPezEntity.Variant(this.getPatternColor()));
        } else {
            return super.get(type);
        }
    }

    @Override
    protected void copyComponentsFrom(ComponentsAccess from) {
        this.copyComponentFrom(from, ModDataComponents.DRACOPEZ_COLOR);
        super.copyComponentsFrom(from);
    }

    @Override
    protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
        if (type == ModDataComponents.DRACOPEZ_COLOR) {
            Variant variant = castComponentValue(ModDataComponents.DRACOPEZ_COLOR, value);
            this.setPatternColor(variant.patternColor());
            return true;
        } else {
            return super.setApplicableComponent(type, value);
        }
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        super.copyDataToStack(stack);
        stack.copy(ModDataComponents.DRACOPEZ_COLOR, this);
    }

    //endregion

//region COLORS
    public static DyeColor getPatternColor(int variant) {
        return DyeColor.byIndex(variant >> 24 & 0xFF);
    }

    public DyeColor getPatternColor() {
        return getPatternColor(this.getDracoPezVariant());
    }

    private void setPatternColor(DyeColor color) {
        this.setDracoPezVariant(getVariantId(color));
    }

    private boolean attemptToChangeColor(DyeColor newColor) {
        if(getPatternColor() == newColor) return false;
        this.setApplicableComponent(ModDataComponents.DRACOPEZ_COLOR, new DracoPezEntity.Variant(newColor));
        return true;
    }
//endregion

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.DRACOPEZ_BUCKET);
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        Random random = world.getRandom();
        DracoPezEntity.Variant variant;
        if (entityData instanceof DracoPezEntity.DracoPezData dracoPezData) {
            variant = dracoPezData.variant;
        } else {
            DyeColor[] dyeColors = DyeColor.values();
            DyeColor dyeColor = Util.getRandom(dyeColors, random);
            variant = new DracoPezEntity.Variant(dyeColor);
        }

        this.setDracoPezVariant(variant.getId());
        return entityData;
    }

    public static boolean canDracoPezSpawn(EntityType<DracoPezEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getFluidState(pos.down()).isIn(FluidTags.WATER)
                && world.getBlockState(pos.up()).isOf(Blocks.WATER)
                && (world.getBiome(pos).isIn(BiomeTags.ALLOWS_TROPICAL_FISH_SPAWNS_AT_ANY_HEIGHT) || WaterCreatureEntity.canSpawn(type, world, reason, pos, random));
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if(player.getStackInHand(hand).getItem() instanceof DyeItem color && this.attemptToChangeColor(color.getColor())) {
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

//region SOUNDS
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_TROPICAL_FISH_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_FLOP;
    }
//endregion

    static class DracoPezData extends SchoolingFishEntity.FishData {
        final DracoPezEntity.Variant variant;

        DracoPezData(DracoPezEntity leader, DracoPezEntity.Variant variant) {
            super(leader);
            this.variant = variant;
        }
    }

    public record Variant(DyeColor patternColor) implements TooltipAppender {
        public static final Codec<DracoPezEntity.Variant> CODEC = Codec.INT.xmap(DracoPezEntity.Variant::new, DracoPezEntity.Variant::getId);
        public static final PacketCodec<RegistryByteBuf, Variant> PACKET_CODEC = PacketCodec.tuple(
                DyeColor.PACKET_CODEC, Variant::patternColor, Variant::new
        );

        public Variant(int id) {
            this(DracoPezEntity.getPatternColor(id));
        }

        public int getId() {
            return DracoPezEntity.getVariantId(this.patternColor);
        }

        @Override
        public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
            Variant color = components.getOrDefault(ModDataComponents.DRACOPEZ_COLOR, DracoPezEntity.DEFAULT_VARIANT);
            MutableText mutableText = Text.translatable("color.minecraft." + color.patternColor().getId());
            mutableText.formatted(Formatting.ITALIC, Formatting.GRAY);
            textConsumer.accept(mutableText);
        }
    }
}
