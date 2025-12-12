package com.jordi.trikivalscreatures.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;

public class TerritorialTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    private final float INTERVAL_TO_AGGRESSIVE; // 60 / 20 = 3 sec
    private float entityNearFor = 0;
    private LivingEntity lastTargetEntity = null;

    private final CustomMeleeAttackMob entity;

    private final float TARGET_RANGE;

    public TerritorialTargetGoal(MobEntity mob, Class<T> targetClass, float targetRange, float intervalToAggressive) {
        super(mob, targetClass, true);
        this.entity = (CustomMeleeAttackMob) mob;
        this.TARGET_RANGE = targetRange;
        this.INTERVAL_TO_AGGRESSIVE = intervalToAggressive;
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public boolean canStart() {
        this.findClosestTarget();
        if(this.entity.canPerformMeleeAttack(this.targetEntity)
                && this.mob.squaredDistanceTo(this.targetEntity) >= TARGET_RANGE * TARGET_RANGE) return false;

        this.entityNearFor++;
        if(this.lastTargetEntity != this.targetEntity) {
            this.entityNearFor = 0;
            this.lastTargetEntity = this.targetEntity;
        }

        if(this.entityNearFor >= INTERVAL_TO_AGGRESSIVE) return super.canStart();

        return false;
    }
}
