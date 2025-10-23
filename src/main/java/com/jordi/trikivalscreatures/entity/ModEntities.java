package com.jordi.trikivalscreatures.entity;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.custom.PejelagartoEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<PejelagartoEntity> PEJELAGARTO = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TrikiValsCreatures.MOD_ID, "pejelagarto"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PejelagartoEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());
}
