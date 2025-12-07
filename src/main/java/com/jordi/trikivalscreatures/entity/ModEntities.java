package com.jordi.trikivalscreatures.entity;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.spit.PejelagartoSpitEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

//Helper class that initialize custom Mobs
public class ModEntities {
    public static final EntityType<PejelagartoEntity> PEJELAGARTO = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TrikiValsCreatures.MOD_ID, "pejelagarto"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PejelagartoEntity::new)
                    .dimensions(EntityDimensions.fixed(0.45f, 0.2f)).build());

    public static final EntityType<PejelagartoSpitEntity> PEJELAGARTO_SPIT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TrikiValsCreatures.MOD_ID, "pejelagarto_spit"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<PejelagartoSpitEntity>) PejelagartoSpitEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
}
