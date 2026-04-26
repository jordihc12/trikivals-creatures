package com.jordi.trikivalscreatures.entity.creatures.pejelagarto.ai;

import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.anims.PejelagartoAnimation;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.PejelagartoVariant;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

public class PejelagartoAttackGoal extends MeleeAttackGoal {
    private final PejelagartoEntity entity;
    private int attackDelay = 5;
    private int ticksUntilnextAttack = 5;
    private boolean shouldCountTillNextAttack = false;

    public PejelagartoAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = (PejelagartoEntity) mob;
    }

    @Override
    public void start() {
        super.start();

        attackDelay = 5;
        ticksUntilnextAttack = 5;
    }

    @Override
    protected void attack(LivingEntity target) {
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
            entity.getAnimationManager().resetAnimationCooldownFor(PejelagartoAnimation.ATTACK);
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
        float attackRange = this.entity.getMeleeAttackRange();
        //If entity is Albino variant the attack range is doubled
        if(entity.getVariant() == PejelagartoVariant.ALBINO) {
            attackRange *= 2.0F;
        }

        return this.entity.distanceTo(pEnemy) <= attackRange;
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
        this.mob.tryAttack((ServerWorld) (this.mob.getEntityWorld()), pEnemy);

        this.entity.playAttackSound();
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
