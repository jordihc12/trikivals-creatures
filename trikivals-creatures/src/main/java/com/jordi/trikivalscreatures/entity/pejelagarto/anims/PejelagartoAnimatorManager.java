package com.jordi.trikivalscreatures.entity.pejelagarto.anims;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import net.minecraft.entity.AnimationState;
import java.util.EnumMap;

public class PejelagartoAnimatorManager {

    private static class AnimationData {
        int cooldown;
        AnimationState animationState;
        boolean animationFinished;

        AnimationData() {
            cooldown = 0;
            animationState = new AnimationState();
            animationFinished = false;
        }
    }

    private Animation previousActiveAnim;
    private Animation activeAnim;

    private final EnumMap<Animation, AnimationData> animations = new EnumMap<>(Animation.class);

    public PejelagartoAnimatorManager() {
        for (Animation anim : Animation.values()) {
            animations.put(anim, new AnimationData());
        }

        activeAnim = Animation.IDLE;
        previousActiveAnim = activeAnim;
    }

    public enum Animation {
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

        Animation(int tickLength, String name, boolean loop) {
            this.tickLength = tickLength;
            this.name = name;
            this.loop = loop;
        }

        Animation(int tickLength, String name) {
            this(tickLength, name, true);
        }

        public int getTickLength() { return this.tickLength; }

        public String getName() { return this.name; }

        public boolean getLoop() { return this.loop; }
    }

    public final AnimationState getAnimationStateFor(Animation anim) {
        return animations.get(anim).animationState;
    }

    public void stopAnimation(Animation anim) {
        animations.get(anim).animationState.stop();
    }

    public void resetAnimationCooldownFor(Animation anim) {
        animations.get(anim).cooldown = 0;
    }

    public final Animation getActiveAnim() {
        return activeAnim;
    }

    private void resetAnimFor(Animation anim, int tick) {
        AnimationData data = animations.get(activeAnim);
        data.animationFinished = false;
        data.animationState.stop();
        data.animationState.start(tick);
    }

    public void updateActiveAnimation(PejelagartoEntity pejelagarto) {
        if(pejelagarto.isSitting()) {
            activeAnim = Animation.SIT;
        }
        else if(pejelagarto.isAttacking()) {
            activeAnim = Animation.ATTACK;
        }
        else if(!pejelagarto.isOnGround() && pejelagarto.getMovement().getY() < -0.5F) {
            activeAnim = Animation.FALL;
        }
        else if(pejelagarto.getMovement().horizontalLength() > 0.01) {
            activeAnim = pejelagarto.isTouchingWater() ? Animation.SWIM : Animation.WALK;
        }
        else {
            activeAnim = pejelagarto.isTouchingWater() ? Animation.IDLE_WATER : Animation.IDLE;
        }

        if(previousActiveAnim != activeAnim) {
            resetAnimFor(previousActiveAnim, pejelagarto.age);

            TrikiValsCreatures.LogStringInfo(this,
                    String.format("Switched animation from %s to %s", previousActiveAnim.getName(), activeAnim.getName()));
            previousActiveAnim = activeAnim;
        }
    }

    public void updateAnimations(int tick) {
        AnimationData animationData = animations.get(activeAnim);

        if(animationData.cooldown <= 0) {
            if(activeAnim.getLoop() || !animationData.animationFinished) {
                animationData.animationFinished = true;
                animationData.cooldown = activeAnim.getTickLength();
                animationData.animationState.start(tick);
            }
        }
        else {
            --animationData.cooldown;
        }
    }
}
