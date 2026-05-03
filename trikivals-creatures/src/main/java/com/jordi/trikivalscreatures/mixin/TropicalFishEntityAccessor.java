package com.jordi.trikivalscreatures.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.TropicalFishEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TropicalFishEntity.class)
public interface TropicalFishEntityAccessor {
    @Accessor("VARIANT")
    static TrackedData<Integer> getVariant() {
        throw new AssertionError();
    }
}
