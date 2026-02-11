package com.jordi.trikivalscreatures.datagen.properties;

import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlock;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.property.select.SelectProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record PejelagartoVariantProperty() implements SelectProperty<PejelagartoVariant> {
    public static final Codec<PejelagartoVariant> VALUE_CODEC = PejelagartoVariant.CODEC;
    public static final SelectProperty.Type<PejelagartoVariantProperty, PejelagartoVariant> TYPE = SelectProperty.Type.create(
            MapCodec.unit(new PejelagartoVariantProperty()), VALUE_CODEC
    );

    @Override
    public @Nullable PejelagartoVariant getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ItemDisplayContext displayContext) {
        BlockStateComponent blockStateComponent = stack.get(DataComponentTypes.BLOCK_STATE);
        if(blockStateComponent != null && !blockStateComponent.isEmpty()) {
            return blockStateComponent.getValue(PejelagartoEggBlock.VARIANT);
        }
        return PejelagartoVariant.PLAINS;
    }

    @Override
    public Codec<PejelagartoVariant> valueCodec() {
        return VALUE_CODEC;
    }

    @Override
    public Type<? extends SelectProperty<PejelagartoVariant>, PejelagartoVariant> getType() {
        return TYPE;
    }
}
