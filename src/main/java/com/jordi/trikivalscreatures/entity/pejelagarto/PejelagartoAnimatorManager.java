package com.jordi.trikivalscreatures.entity.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.minecraft.entity.AnimationState;
import java.util.EnumMap;

public class PejelagartoAnimatorManager {

    private static class AnimationData {
        int cooldown;
        AnimationState animationState;

        AnimationData() {
            cooldown = 0;
            animationState = new AnimationState();


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
        IDLE(30, "idle");

        private final int tickLength;
        private final String name;

        Animation(int tickLength, String name) {
            this.tickLength = tickLength;
            this.name = name;
        }

        public int getTickLength() {
            return this.tickLength;
        }

        public String getName() {return this.name; }
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

    public void updateActiveAnimation(PejelagartoEntity pejelagarto) {
        if(pejelagarto.isAttacking()) {
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
            TrikiValsCreatures.LogStringInfo(this,
                    String.format("Switched animation from %s to %s", previousActiveAnim.getName(), activeAnim.getName()));
            previousActiveAnim = activeAnim;
        }
    }

    public void updateAnimations(int tick) {
        animations.forEach(((animation, animationData) -> {
            if(animationData.cooldown <= 0) {
                animationData.cooldown = animation.getTickLength();
                animationData.animationState.start(tick);
            }
            else {
                --animationData.cooldown;
            }
        }));
    }
}
