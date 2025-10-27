package com.jordi.trikivalscreatures.entity.pejelagarto.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;

public class PejelagartoTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    public PejelagartoTargetGoal(MobEntity mob, Class<T> targetClass) {
        super(mob, targetClass, true);
    }

    @Override
    public boolean canStart() {
        long time = this.mob.getWorld().getTimeOfDay();
        if(time >= 13000L && time < 23000L) return super.canStart();
        return false;
    }
}
