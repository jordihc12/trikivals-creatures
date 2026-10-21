package com.jordi.trikivalscreatures.entity.dracopez.render;

import com.jordi.trikivalscreatures.entity.dracopez.anims.DracoPezAnimation;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class DracoPezRenderState extends LivingEntityRenderState {
    public AnimationState activeAnimationState = new AnimationState();
    public DracoPezAnimation activeAnimation;

    // ARGB color used to tint the fins layer
    public int finColor = 0xFFFFFFFF;
}
