package com.jordi.trikivalscreatures.entity.pejelagarto.ai;

import com.jordi.trikivalscreatures.entity.pejelagarto.custom.PejelagartoEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Hand;

public class PejelagartoAttackGoal extends MeleeAttackGoal {
    private final PejelagartoEntity entity;
    private int attackDelay = 10;
    private int ticksUntilnextAttack = 10;
    private boolean shouldCountTillNextAttack = false;

    public PejelagartoAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = (PejelagartoEntity) mob;
    }

    @Override
    public void start() {
        super.start();

        attackDelay = 10;
        ticksUntilnextAttack = 10;
    }

    @Override
    protected void attack(LivingEntity target, double squaredDistance) {
        if(isEnemyWithinAttackDistance(target)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().lookAt(target.getX(), target.getEyeY(), target.getZ());
                performAttack(target);
            }
        }
        else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationCooldown = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
        return this.entity.distanceTo(pEnemy) <= 2.0F; //todo
    }

    protected void resetAttackCooldown() {
        this.ticksUntilnextAttack = this.getTickCount(attackDelay * 2);
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilnextAttack <= attackDelay;
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilnextAttack <= 0;
    }

    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swingHand(Hand.MAIN_HAND);
        this.mob.tryAttack(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack) this.ticksUntilnextAttack = Math.max(this.ticksUntilnextAttack - 1, 0);
    }

    @Override
    public void stop() {
        entity.setAttacking(false);

        super.stop();
    }
}
