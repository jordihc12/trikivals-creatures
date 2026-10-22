package com.jordi.trikivalscreatures.entity.palgaster.render;

import com.jordi.trikivalscreatures.entity.palgaster.PalgasterCoat;
import net.minecraft.client.render.entity.state.EntityRenderState;

public class PalgasterRenderState extends EntityRenderState {
    public float bodyYaw;
    public PalgasterCoat coat = PalgasterCoat.BLANCO;
    public boolean saddled;
    public boolean hurt;
    public String animation = "idle";
    public float animationTime;
    public float deathTime;
}
