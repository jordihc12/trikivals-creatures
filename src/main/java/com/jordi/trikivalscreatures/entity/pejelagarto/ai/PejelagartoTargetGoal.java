package com.jordi.trikivalscreatures.entity.pejelagarto.ai;

import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;

public class PejelagartoTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    public PejelagartoTargetGoal(MobEntity mob, Class<T> targetClass) {
        super(mob, targetClass, true);
    }

    @Override
    public boolean canStart() {
        if(this.mob instanceof PejelagartoEntity pejelagarto) {
            boolean canStart = pejelagarto.getVariant() == PejelagartoVariant.SAND;
            if(canStart) return super.canStart();
        }

        return false;
    }
}
