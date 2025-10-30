package com.jordi.trikivalscreatures.entity.pejelagarto.custom;

import net.minecraft.registry.RegistryKey;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

@SuppressWarnings("unchecked")
public enum PejelagartoVariant implements StringIdentifiable {
    GREEN("green", 0),
    PINK("pink", 1, new RegistryKey[]{
            BiomeKeys.DESERT,
            BiomeKeys.BADLANDS});

    private static final PejelagartoVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(PejelagartoVariant::getId)).toArray(PejelagartoVariant[]::new);
    private final int id;
    private RegistryKey<Biome>[] biomes = new RegistryKey[]{};
    private final String name;

    PejelagartoVariant(String name, int id) {
        this.id = id;
        this.name = name;
    }

    PejelagartoVariant(String name, int id, RegistryKey<Biome>[] biomes) {
        this.name = name;
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

    public static @Nullable PejelagartoVariant byName(String name) {
        for (var variant : BY_ID) {
            if(Objects.equals(variant.name, name)) return variant;
        }
        return null;
    }

    @Override
    public String asString() {
        return name;
    }
}
