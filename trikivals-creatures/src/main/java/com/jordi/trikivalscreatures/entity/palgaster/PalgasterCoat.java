package com.jordi.trikivalscreatures.entity.palgaster;

/** Coat colours of the Palgaster (texture: textures/entity/palgaster/palgaster_<id>.png). */
public enum PalgasterCoat {
    BLANCO("blanco"),
    TORDO("tordo"),
    ALAZAN("alazan"),
    CASTANO("castano"),
    NEGRO("negro"),
    PALOMINO("palomino");

    private final String id;

    PalgasterCoat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static PalgasterCoat byIndex(int index) {
        PalgasterCoat[] values = values();
        return values[Math.floorMod(index, values.length)];
    }
}
