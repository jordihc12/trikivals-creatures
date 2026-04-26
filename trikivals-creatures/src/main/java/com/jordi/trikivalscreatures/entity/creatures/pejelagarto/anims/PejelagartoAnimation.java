package com.jordi.trikivalscreatures.entity.creatures.pejelagarto.anims;

import com.jordi.trikivalscreatures.entity.anims.IAnimation;

public enum PejelagartoAnimation implements IAnimation {
    ATTACK(15, "attack"),
    WALK(20, "walk"),
    SWIM(20, "swim"),
    FALL(10, "fall"),
    IDLE_WATER(40, "idle_water"),
    IDLE(30, "idle"),
    SIT(10, "sit", false);

    private final int tickLength;
    private final String name;
    private final boolean loop;

    PejelagartoAnimation(int tickLength, String name, boolean loop) {
        this.tickLength = tickLength;
        this.name = name;
        this.loop = loop;
    }

    PejelagartoAnimation(int tickLength, String name) {
        this(tickLength, name, true);
    }

    public int getTickLength() { return this.tickLength; }

    public String getName() { return this.name; }

    public boolean getLoop() { return this.loop; }
}
