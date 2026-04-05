package com.jordi.trikivalscreatures.entity.pejelagarto.render;

import com.jordi.trikivalscreatures.entity.pejelagarto.anims.PejelagartoAnimation;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class PejelagartoRenderState extends LivingEntityRenderState {
    public AnimationState idleAnimationState = new AnimationState();
    public AnimationState activeAnimationState = new AnimationState();
    public PejelagartoVariant variant;

    public PejelagartoAnimation activeAnimation;

    public boolean inWater;
}
