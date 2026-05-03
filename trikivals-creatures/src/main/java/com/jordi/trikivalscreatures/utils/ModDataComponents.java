package com.jordi.trikivalscreatures.utils;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.creatures.dracopez.DracoPezEntity;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final ComponentType<DracoPezEntity.Variant> DRACOPEZ_COLOR = register(
            Identifier.of(TrikiValsCreatures.MOD_ID, "dracopez/color"),
           builder -> builder.codec(DracoPezEntity.Variant.CODEC).packetCodec(DracoPezEntity.Variant.PACKET_CODEC)
    );

    private static <E> ComponentType<E> register (Identifier id, UnaryOperator<ComponentType.Builder<E>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id,(builderOperator.apply(ComponentType.builder())).build());
    }

    public static void registerModDataComponents() {
        TrikiValsCreatures.LOGGER.info("Registering Mod Data Components");
    }
}
