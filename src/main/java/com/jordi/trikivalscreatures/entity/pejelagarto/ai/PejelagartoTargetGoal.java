package com.jordi.trikivalscreatures.entity.pejelagarto.ai;

import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;

public class PejelagartoTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    private final float INTERVAL_TO_AGGRESSIVE = 60; // 60 / 20 = 3 sec
    private float entityNearFor = 0;
    private LivingEntity lastTargetEntity = null;

    public PejelagartoTargetGoal(MobEntity mob, Class<T> targetClass) {
        super(mob, targetClass, true);
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public boolean canStart() {
        if(((PejelagartoEntity) this.mob).getVariant() == PejelagartoVariant.PLAINS) return false;

        this.findClosestTarget();
        if(this.targetEntity == null || this.targetEntity instanceof PejelagartoEntity) return false;


        this.entityNearFor++;
        if(this.lastTargetEntity != this.targetEntity) {
            this.entityNearFor = 0;
            this.lastTargetEntity = this.targetEntity;
        }

        if(this.entityNearFor >= INTERVAL_TO_AGGRESSIVE) return super.canStart();

        return false;
    }
}
