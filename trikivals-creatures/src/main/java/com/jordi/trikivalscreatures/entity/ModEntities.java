package com.jordi.trikivalscreatures.entity;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.spit.PejelagartoSpitEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

//Helper class that initialize custom Mobs
public class ModEntities {
    private static final RegistryKey<EntityType<?>> PEJELAGARTO_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto"));
    private static final RegistryKey<EntityType<?>> PEJELAGARTO_SPIT_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto_spit"));

    public static final EntityType<PejelagartoEntity> PEJELAGARTO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto"),
            EntityType.Builder.create(PejelagartoEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.45f, 0.2f).build(PEJELAGARTO_KEY));

    public static final EntityType<PejelagartoSpitEntity> PEJELAGARTO_SPIT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto_spit"),
            EntityType.Builder.create((EntityType.EntityFactory<PejelagartoSpitEntity>)PejelagartoSpitEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F).build(PEJELAGARTO_SPIT_KEY));
}
