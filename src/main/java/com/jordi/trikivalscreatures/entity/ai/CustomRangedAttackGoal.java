package com.jordi.trikivalscreatures.entity.ai;

import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.mob.MobEntity;

public class CustomRangedAttackGoal extends ProjectileAttackGoal {
    private final CustomRangedAttackMob entity;
    private final int AGGRO_RANGE_MAX;
    private final int AGGRO_RANGE_MIN;

    public CustomRangedAttackGoal(CustomRangedAttackMob mob, double mobSpeed, int maxRange, int minRange, int intervalTicks, float maxShootRange) {
        super(mob, mobSpeed, intervalTicks, maxShootRange);
        AGGRO_RANGE_MAX = maxRange;
        AGGRO_RANGE_MIN = minRange;
        entity = mob;
    }

    private boolean isWithinDistance() {
        if(entity instanceof MobEntity mobEntity && mobEntity.getTarget() != null) {
            double distance = mobEntity.distanceTo(mobEntity.getTarget());
            return distance >= AGGRO_RANGE_MIN && distance <= AGGRO_RANGE_MAX;
        }
        return false;
    }

    public int getAGGRO_RANGE_MAX() {
        return AGGRO_RANGE_MAX;
    }

    public int getAGGRO_RANGE_MIN() {
        return AGGRO_RANGE_MIN;
    }

    @Override
    public boolean canStart() {
        if(this.entity == null) return false;

        return this.entity.canPerformRangedAttack() && isWithinDistance() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return this.isWithinDistance();
    }
}
