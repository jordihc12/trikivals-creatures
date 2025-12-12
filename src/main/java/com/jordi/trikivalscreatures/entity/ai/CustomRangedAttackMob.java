package com.jordi.trikivalscreatures.entity.ai;

import net.minecraft.entity.ai.RangedAttackMob;

public interface CustomRangedAttackMob extends RangedAttackMob {
    int getAggroMaxAttackRange();
    int getAggroMinAttackRange();

    boolean canPerformRangedAttack();
}
