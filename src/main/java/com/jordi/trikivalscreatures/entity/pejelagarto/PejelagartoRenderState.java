package com.jordi.trikivalscreatures.entity.pejelagarto;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class PejelagartoRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public PejelagartoVariant variant;
}
