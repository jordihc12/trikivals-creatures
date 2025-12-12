package com.jordi.trikivalscreatures.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;

import java.util.EnumSet;

public class NarrowDistanceDelegatingGoal extends Goal {
    private final PathAwareEntity entity;
    private LivingEntity target;

    // Define your distance constants (using squared values)
    private static final double MELEE_RANGE = 2.0;

    public NarrowDistanceDelegatingGoal(PathAwareEntity mob) {
        this.entity = mob;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }
    @Override
    public boolean canStart() {
        this.target = entity.getTarget();
        return this.target != null && this.target.isAlive() && NotTargetWithinDistance();
    }

    @Override
    public boolean shouldContinue() {
        return NotTargetWithinDistance();
    }

    private boolean NotTargetWithinDistance() {
        double distanceSq = this.entity.squaredDistanceTo(this.target);
        if(distanceSq <= MELEE_RANGE * MELEE_RANGE) return false;

        if(this.entity instanceof CustomRangedAttackMob customRangedAttackMob) {
            int RANGE_ATTACK_RANGE_MIN = customRangedAttackMob.getAggroMinAttackRange();
            int RANGE_ATTACK_RANGE_MAX = customRangedAttackMob.getAggroMaxAttackRange();

            return (!(distanceSq >= RANGE_ATTACK_RANGE_MIN * RANGE_ATTACK_RANGE_MIN)
                    || !(distanceSq <= RANGE_ATTACK_RANGE_MAX * RANGE_ATTACK_RANGE_MAX));
        }

        return true;
    }

    @Override
    public void tick() {
        if(this.target == null) return;

        this.entity.getLookControl().lookAt(this.target, 30.0F, 30.0F);
        this.entity.getNavigation().startMovingTo(this.target, 1.2);
    }
}
