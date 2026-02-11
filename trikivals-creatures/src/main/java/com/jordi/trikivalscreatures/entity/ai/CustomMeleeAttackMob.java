package com.jordi.trikivalscreatures.entity.ai;

import net.minecraft.entity.LivingEntity;

public interface CustomMeleeAttackMob {
    int getMeleeAttackRange();

    boolean canPerformMeleeAttack(LivingEntity target);
}
