package com.jordi.trikivalscreatures.entity.dracopez.anims;

import com.jordi.trikivalscreatures.entity.anims.IAnimation;

public enum DracoPezAnimation implements IAnimation {
    SWIM(20, "swim"),
    IDLE_WATER(60, "idle_water"),
    FLOP(12, "flop");

    private final int tickLength;
    private final String name;
    private final boolean loop;

    DracoPezAnimation(int tickLength, String name, boolean loop) {
        this.tickLength = tickLength;
        this.name = name;
        this.loop = loop;
    }

    DracoPezAnimation(int tickLength, String name) {
        this(tickLength, name, true);
    }

    public int getTickLength() { return this.tickLength; }

    public String getName() { return this.name; }

    public boolean getLoop() { return this.loop; }
}
