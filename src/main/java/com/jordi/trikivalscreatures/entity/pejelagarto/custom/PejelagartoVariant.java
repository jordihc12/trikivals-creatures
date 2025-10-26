package com.jordi.trikivalscreatures.entity.pejelagarto.custom;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public enum PejelagartoVariant {
    GREEN(0),
    PINK(1, new RegistryKey[]{
            BiomeKeys.DESERT,
            BiomeKeys.BADLANDS});

    private static final PejelagartoVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(PejelagartoVariant::getId)).toArray(PejelagartoVariant[]::new);
    private final int id;
    private RegistryKey<Biome>[] biomes = new RegistryKey[]{};

    PejelagartoVariant(int id) {
        this.id = id;
    }

    PejelagartoVariant(int id, RegistryKey<Biome>[] biomes) {
        this.id = id;
        this.biomes = biomes;
    }

    public RegistryKey<Biome>[] getSpawnBiomes() {
        return this.biomes;
    }

    public int getId() {
        return this.id;
    }

    public static PejelagartoVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
