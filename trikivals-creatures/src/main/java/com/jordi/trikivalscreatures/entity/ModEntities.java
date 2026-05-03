package com.jordi.trikivalscreatures.entity;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.creatures.dracopez.DracoPezEntity;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.spit.PejelagartoSpitEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

//Helper class that initialize custom Mobs
public class ModEntities {

//region PEJELAGARTO
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
//endregion

//region DRACOPEZ
    private static final RegistryKey<EntityType<?>> DRACOPEZ_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TrikiValsCreatures.MOD_ID, "dracopez"));

    public static final EntityType<DracoPezEntity> DRACOPEZ = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TrikiValsCreatures.MOD_ID, "dracopez"),
            EntityType.Builder.create(DracoPezEntity::new, SpawnGroup.WATER_AMBIENT)
                    .dimensions(0.5F, 0.4F).eyeHeight(0.26F).maxTrackingRange(4).build(DRACOPEZ_KEY));
//endregion
    public static void registerEntities() {
        TrikiValsCreatures.LOGGER.info("Registering entities!");

        FabricDefaultAttributeRegistry.register(ModEntities.PEJELAGARTO, PejelagartoEntity.createPejelagartoAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.DRACOPEZ, FishEntity.createFishAttributes());
    }
}
