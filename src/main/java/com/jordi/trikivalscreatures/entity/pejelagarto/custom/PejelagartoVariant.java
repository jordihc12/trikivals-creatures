package com.jordi.trikivalscreatures.entity.pejelagarto.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum PejelagartoVariant {
    GREEN(0),
    PINK(1);

    private static final PejelagartoVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(PejelagartoVariant::getId)).toArray(PejelagartoVariant[]::new);
    private final int id;

    PejelagartoVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PejelagartoVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
