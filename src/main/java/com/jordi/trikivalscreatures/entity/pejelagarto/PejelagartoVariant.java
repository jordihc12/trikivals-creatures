
package com.jordi.trikivalscreatures.entity.pejelagarto;

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
    PLAINS(
        "plains",
        0,
        1.0F
    ),
    JUNGLE(
        "jungle",
        1,
        1.0F,
        new RegistryKey[]{BiomeKeys.JUNGLE}
    ),
    PINK(
        "pink",
        2,
        0.8F,
        new RegistryKey[] {BiomeKeys.MUSHROOM_FIELDS}
    ),
    SAND(
        "sand",
        3,
        2.0F,
        new RegistryKey[] {BiomeKeys.DESERT}
    ),
    ALBINO(
        "albino",
        4,
        1.0F,
        null
    );

    private static final PejelagartoVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(PejelagartoVariant::getId)).toArray(PejelagartoVariant[]::new);
    private final int id;

    private final float size;

    // List of biomes where each variant spawn. Can have 3 values:
    // - biomes = new RegistryKey[]{ BiomeKeys.BIOME_1, BiomeKeys.BIOME_2, ... } -> variant can spawn in any biome present in the list
    // - biomes = new RegistryKey[]{} -> variant can spawn in any biome the entity spawns
    // - biomes = null -> variant cannot spawn in any biome
    private RegistryKey<Biome>[] biomes = new RegistryKey[]{};
    private final String name;

    PejelagartoVariant(String name, int id, float size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    PejelagartoVariant(String name, int id, float size, RegistryKey<Biome>[] biomes) {
        this.name = name;
        this.id = id;
        this.size = size;
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

    public float getVariantSize() {
        return size;
    }

    @Override
    public String asString() {
        return name;
    }
}
