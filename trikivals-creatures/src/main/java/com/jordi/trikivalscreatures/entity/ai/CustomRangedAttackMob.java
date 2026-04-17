package com.jordi.trikivalscreatures.entity.ai;

import net.minecraft.entity.ai.RangedAttackMob;

public interface CustomRangedAttackMob extends RangedAttackMob {
    int getRangeAggroMaxAttackRange();
    int getRangeAggroMinAttackRange();

    boolean canPerformRangedAttack();
}
