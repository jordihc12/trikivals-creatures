package com.jordi.trikivalscreatures.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class NarrowDistanceDelegatingGoal extends Goal {
    private final PathAwareEntity entity;
    private LivingEntity target;

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
        if(this.entity instanceof CustomMeleeAttackMob customMeleeAttackMob) {
            int MELEE_RANGE = customMeleeAttackMob.getMeleeAttackRange();
            if(distanceSq <= MELEE_RANGE * MELEE_RANGE) return false;
        }

        if(this.entity instanceof CustomRangedAttackMob customRangedAttackMob) {
            int RANGE_ATTACK_RANGE_MIN = customRangedAttackMob.getRangeAggroMinAttackRange();
            int RANGE_ATTACK_RANGE_MAX = customRangedAttackMob.getRangeAggroMaxAttackRange();

            return (!(distanceSq >= RANGE_ATTACK_RANGE_MIN * RANGE_ATTACK_RANGE_MIN)
                    || !(distanceSq <= RANGE_ATTACK_RANGE_MAX * RANGE_ATTACK_RANGE_MAX));
        }

        return true;
    }

    private boolean isBetweenRanges() {
        double distanceSq = this.entity.squaredDistanceTo(this.target);
        if(this.entity instanceof CustomRangedAttackMob customRangedAttackMob) {
            int RANGE_ATTACK_RANGE_MIN = customRangedAttackMob.getRangeAggroMinAttackRange();
            int RANGE_ATTACK_RANGE_MAX = customRangedAttackMob.getRangeAggroMaxAttackRange();

            return (!(distanceSq >= RANGE_ATTACK_RANGE_MIN * RANGE_ATTACK_RANGE_MIN)
                    || !(distanceSq <= RANGE_ATTACK_RANGE_MAX * RANGE_ATTACK_RANGE_MAX));
        }
        return false;
    }

    @Override
    public void tick() {
        if(this.target == null) return;

        this.entity.getLookControl().lookAt(this.target, 30.0F, 30.0F);
        if(this.entity instanceof CustomMeleeAttackMob customMeleeAttackMob && this.entity instanceof CustomRangedAttackMob customRangedAttackMob) {
            double MELEE_RANGE = Math.pow(customMeleeAttackMob.getMeleeAttackRange(), 2);
            double RANGE_ATTACK_RANGE_MIN = Math.pow(customRangedAttackMob.getRangeAggroMinAttackRange(), 2);

            double distanceSq = this.entity.squaredDistanceTo(this.target);

            if(distanceSq > MELEE_RANGE && distanceSq < RANGE_ATTACK_RANGE_MIN) {
                double distanceToMelee = distanceSq - MELEE_RANGE;
                double distanceToRange = RANGE_ATTACK_RANGE_MIN - distanceSq;

                //Flee Target
                if(distanceToRange < distanceToMelee) {
                    Vec3d targetPos = new Vec3d(this.target.getX(), this.target.getY(), this.target.getZ());
                    Vec3d entityPos = new Vec3d(this.entity.getX(), this.entity.getY(), this.entity.getZ());

                    Vec3d dirNormalized = (targetPos.subtract(entityPos)).normalize();
                    Vec3d fleePos = entityPos.add(dirNormalized.multiply(-2));
                    this.entity.getNavigation().startMovingTo(fleePos.getX(), fleePos.getY(), fleePos.getZ(), 1.2);
                    return;
                }
            }
        }
        this.entity.getNavigation().startMovingTo(this.target, 1.2);
    }
}
