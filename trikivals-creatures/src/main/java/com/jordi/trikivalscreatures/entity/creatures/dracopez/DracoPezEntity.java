package com.jordi.trikivalscreatures.entity.creatures.dracopez;

import com.jordi.trikivalscreatures.items.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public class DracoPezEntity extends TropicalFishEntity {
    private DyeColor patternColor = DyeColor.WHITE;

    public DracoPezEntity(EntityType<? extends TropicalFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public DyeColor getPatternColor() {
        return this.patternColor;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.DRACOPEZ_BUCKET);
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if(player.getStackInHand(hand).getItem() instanceof DyeItem color && this.setPatternColor(color.getColor())) {
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    private boolean setPatternColor(DyeColor newColor) {
        if(this.patternColor == newColor) return false;
        this.patternColor = newColor;
        this.setApplicableComponent(DataComponentTypes.TROPICAL_FISH_PATTERN_COLOR, this.patternColor);
        return true;
    }
}
