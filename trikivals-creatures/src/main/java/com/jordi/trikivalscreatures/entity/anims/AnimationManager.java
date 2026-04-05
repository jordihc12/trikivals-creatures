package com.jordi.trikivalscreatures.entity.anims;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;

import java.util.EnumMap;

public abstract class AnimationManager<U extends Entity, T extends Enum<T> & IAnimation> {

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

    private T activeAnim;

    private final EnumMap<T, AnimationManager.AnimationData> animations;

    public AnimationManager(Class<T> animClass, T defaultAnims) {
        this.animations = new EnumMap<>(animClass);
        for (T anim : animClass.getEnumConstants()) {
            animations.put(anim, new AnimationData());
        }

        activeAnim = defaultAnims;
    }

    public final AnimationState getAnimationStateFor(T anim) {
        return animations.get(anim).animationState;
    }

    public void stopAnimation(T anim) {
        animations.get(anim).animationState.stop();
    }

    public void resetAnimationCooldownFor(T anim) {
        animations.get(anim).cooldown = 0;
    }

    public final T getActiveAnim() {
        return activeAnim;
    }

    public abstract T getActiveAnimationPriv(U entity);

    public void updateActiveAnimation(U entity) {
        T nextAnim = getActiveAnimationPriv(entity);

        setActiveAnim(nextAnim, entity.age);
    }

    public void setActiveAnim(T nextAnim, int entityAge) {
        if(nextAnim != activeAnim) {
            resetAnim(activeAnim, entityAge);

            activeAnim = nextAnim;
        }
    }

    private void resetAnim(T anim, int age) {
        AnimationData data = animations.get(anim);
        data.animationFinished = false;
        data.animationState.stop();
        data.animationState.start(age);
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
