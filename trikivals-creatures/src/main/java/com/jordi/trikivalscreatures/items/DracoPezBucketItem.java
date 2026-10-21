package com.jordi.trikivalscreatures.items;

import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

/**
 * Bucket with a DracoPez inside. Keeps its size and fin color, shows them in the tooltip
 * and the fins drawn on the item are tinted with the stored color.
 */
public class DracoPezBucketItem extends EntityBucketItem {
    public DracoPezBucketItem(EntityType<? extends MobEntity> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(type, fluid, emptyingSound, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent,
                              Consumer<Text> textConsumer, TooltipType type) {
        NbtComponent data = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        if (data.isEmpty()) {
            return;
        }

        NbtCompound nbt = data.copyNbt();
        DyeColor color = DyeColor.byIndex(nbt.getInt("FinColor", DyeColor.WHITE.getIndex()));
        float lengthBlocks = nbt.getFloat("AdultScale", DracoPezEntity.MIN_SCALE) * DracoPezEntity.MODEL_LENGTH_BLOCKS;
        boolean baby = nbt.getInt("Age", 0) < 0;

        textConsumer.accept(Text.translatable("tooltip.trikivals-creatures.dracopez.fins",
                Text.translatable("color.minecraft." + color.getId())).formatted(Formatting.GRAY));

        String sizeKey = lengthBlocks < 0.8F ? "small" : (lengthBlocks < 1.15F ? "medium" : "large");
        textConsumer.accept(Text.translatable("tooltip.trikivals-creatures.dracopez.size",
                Text.translatable("tooltip.trikivals-creatures.dracopez.size." + sizeKey),
                String.format("%.2f", lengthBlocks)).formatted(Formatting.GRAY));

        if (baby) {
            textConsumer.accept(Text.translatable("tooltip.trikivals-creatures.dracopez.baby").formatted(Formatting.GRAY));
        }
    }
}
