package com.jordi.trikivalscreatures.entity;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import com.jordi.trikivalscreatures.entity.palgaster.PalgasterEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.spit.PejelagartoSpitEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
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

    private static final RegistryKey<EntityType<?>> DRACOPEZ_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TrikiValsCreatures.MOD_ID, "dracopez"));

    private static final RegistryKey<EntityType<?>> PALGASTER_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TrikiValsCreatures.MOD_ID, "palgaster"));

    public static final EntityType<PejelagartoEntity> PEJELAGARTO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto"),
            EntityType.Builder.create(PejelagartoEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.45f, 0.2f).build(PEJELAGARTO_KEY));

    public static final EntityType<PejelagartoSpitEntity> PEJELAGARTO_SPIT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto_spit"),
            EntityType.Builder.create((EntityType.EntityFactory<PejelagartoSpitEntity>)PejelagartoSpitEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F).build(PEJELAGARTO_SPIT_KEY));

    // Hitbox at scale 1. The real size depends on each DracoPez (SCALE attribute): 1.5 to 2 blocks long (babies 0.75)
    public static final EntityType<DracoPezEntity> DRACOPEZ = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TrikiValsCreatures.MOD_ID, "dracopez"),
            EntityType.Builder.create(DracoPezEntity::new, SpawnGroup.WATER_AMBIENT)
                    .dimensions(1.0f, 0.5f).build(DRACOPEZ_KEY));

    // Palgaster: rideable mount, a bit taller than the player
    public static final EntityType<PalgasterEntity> PALGASTER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TrikiValsCreatures.MOD_ID, "palgaster"),
            EntityType.Builder.create(PalgasterEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.3f, 1.9f)
                    .passengerAttachments(1.62f)
                    .maxTrackingRange(10)
                    .build(PALGASTER_KEY));

    public static void registerEntities() {
        TrikiValsCreatures.LOGGER.info("Registering entities!");

        FabricDefaultAttributeRegistry.register(ModEntities.PEJELAGARTO, PejelagartoEntity.createPejelagartoAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.DRACOPEZ, DracoPezEntity.createDracoPezAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.PALGASTER, PalgasterEntity.createPalgasterAttributes());
    }
}
