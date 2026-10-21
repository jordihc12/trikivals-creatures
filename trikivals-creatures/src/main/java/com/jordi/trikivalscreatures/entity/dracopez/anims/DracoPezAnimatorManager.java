package com.jordi.trikivalscreatures.entity.dracopez.anims;

import com.jordi.trikivalscreatures.entity.anims.AnimationManager;
import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;

public class DracoPezAnimatorManager extends AnimationManager<DracoPezEntity, DracoPezAnimation> {

    public DracoPezAnimatorManager() {
        super(DracoPezAnimation.class, DracoPezAnimation.IDLE_WATER);
    }

    @Override
    public DracoPezAnimation getActiveAnimationPriv(DracoPezEntity entity) {
        if (!entity.isTouchingWater()) {
            return DracoPezAnimation.FLOP;
        }
        // Position change since last tick (works on the client for every entity)
        double dx = entity.getX() - entity.lastX;
        double dy = entity.getY() - entity.lastY;
        double dz = entity.getZ() - entity.lastZ;
        double moved = dx * dx + dy * dy + dz * dz;
        if (moved > DracoPezEntity.FAST_SWIM_THRESHOLD * DracoPezEntity.FAST_SWIM_THRESHOLD) {
            return DracoPezAnimation.SWIM_FAST;
        }
        else if (moved > 0.0004) {
            return DracoPezAnimation.SWIM;
        }
        else {
            return DracoPezAnimation.IDLE_WATER;
        }
    }
}
