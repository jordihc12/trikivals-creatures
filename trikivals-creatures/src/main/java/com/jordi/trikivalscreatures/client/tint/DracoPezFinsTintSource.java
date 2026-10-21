package com.jordi.trikivalscreatures.client.tint;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;

/**
 * Item tint that colors the fins drawn on the DracoPez bucket with the fin color stored in the bucket.
 * Used in assets/trikivals-creatures/items/dracopez_bucket.json as "trikivals-creatures:dracopez_fins".
 */
public record DracoPezFinsTintSource() implements TintSource {
    public static final Identifier ID = Identifier.of(TrikiValsCreatures.MOD_ID, "dracopez_fins");
    public static final DracoPezFinsTintSource INSTANCE = new DracoPezFinsTintSource();
    public static final MapCodec<DracoPezFinsTintSource> CODEC = MapCodec.unit(INSTANCE);

    private static boolean registered = false;

    /** Safe to call more than once (client init and datagen). */
    public static void register() {
        if (!registered) {
            registered = true;
            TintSourceTypes.ID_MAPPER.put(ID, CODEC);
        }
    }

    @Override
    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        NbtComponent data = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        int colorIndex = data.copyNbt().getInt("FinColor", DyeColor.WHITE.getIndex());
        return ColorHelper.fullAlpha(DyeColor.byIndex(colorIndex).getEntityColor());
    }

    @Override
    public MapCodec<DracoPezFinsTintSource> getCodec() {
        return CODEC;
    }
}
