package com.jordi.trikivalscreatures.entity.creatures.pejelagarto.render;

import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.anims.PejelagartoAnimation;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.PejelagartoVariant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

@Environment(EnvType.CLIENT)
public class PejelagartoRenderState extends LivingEntityRenderState {
    public AnimationState idleAnimationState = new AnimationState();
    public AnimationState activeAnimationState = new AnimationState();
    public PejelagartoVariant variant;

    public PejelagartoAnimation activeAnimation;

    public boolean inWater;
}
