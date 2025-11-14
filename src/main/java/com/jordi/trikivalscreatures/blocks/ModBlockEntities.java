package com.jordi.trikivalscreatures.blocks;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<PejelagartoEggBlockEntity> PEJELAGARTO_EGG_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto_egg_be"),
                    BlockEntityType.Builder.create(PejelagartoEggBlockEntity::new, ModBlocks.PEJELAGARTO_EGG).build(null));

    public static void registerModBlockEntities() {
        TrikiValsCreatures.LOGGER.info("Registering Mod Block Entities for " + TrikiValsCreatures.MOD_ID);

    }
}
