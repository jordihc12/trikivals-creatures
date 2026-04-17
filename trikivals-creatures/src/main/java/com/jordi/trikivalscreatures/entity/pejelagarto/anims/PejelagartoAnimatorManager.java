package com.jordi.trikivalscreatures.entity.pejelagarto.anims;

import com.jordi.trikivalscreatures.entity.anims.AnimationManager;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;

public class PejelagartoAnimatorManager extends AnimationManager<PejelagartoEntity, PejelagartoAnimation> {

    public PejelagartoAnimatorManager() {
        super(PejelagartoAnimation.class, PejelagartoAnimation.IDLE);
    }

    @Override
    public PejelagartoAnimation getActiveAnimationPriv(PejelagartoEntity entity) {
        if(entity.isSitting()) {
            return PejelagartoAnimation.SIT;
        }
        else if(entity.isAttacking()) {
            return PejelagartoAnimation.ATTACK;
        }
        else if(!entity.isOnGround() && entity.getMovement().getY() < -0.5F) {
            return PejelagartoAnimation.FALL;
        }
        else if(entity.getMovement().horizontalLength() > 0.01) {
            return entity.isTouchingWater() ? PejelagartoAnimation.SWIM : PejelagartoAnimation.WALK;
        }
        else {
            return entity.isTouchingWater() ? PejelagartoAnimation.IDLE_WATER : PejelagartoAnimation.IDLE;
        }
    }
}
