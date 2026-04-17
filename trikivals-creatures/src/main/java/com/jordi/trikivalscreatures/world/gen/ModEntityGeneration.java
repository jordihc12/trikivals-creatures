package com.jordi.trikivalscreatures.world.gen;

import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

//Helper class that adds custom mobs to world spawn generation
public class ModEntityGeneration {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.JUNGLE, BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.DESERT),
                SpawnGroup.CREATURE,
                ModEntities.PEJELAGARTO, 35, 3, 6);

        SpawnRestriction.register(ModEntities.PEJELAGARTO, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PejelagartoEntity::canSpawn);
    }
}
