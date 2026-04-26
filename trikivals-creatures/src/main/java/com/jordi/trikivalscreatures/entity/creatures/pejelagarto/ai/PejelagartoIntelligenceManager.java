package com.jordi.trikivalscreatures.entity.creatures.pejelagarto.ai;

import com.jordi.trikivalscreatures.entity.ai.CustomEscapeDangerGoal;
import com.jordi.trikivalscreatures.entity.ai.CustomRangedAttackGoal;
import com.jordi.trikivalscreatures.entity.ai.FollowOwnerNoTeleportGoal;
import com.jordi.trikivalscreatures.entity.ai.TerritorialTargetGoal;
import com.jordi.trikivalscreatures.entity.goals.IntelligenceManager;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.PejelagartoEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class PejelagartoIntelligenceManager extends IntelligenceManager<PejelagartoEntity> {
    public PejelagartoIntelligenceManager(PejelagartoEntity entity) {
        super(entity);
    }

    @Override
    public void setGoalsPriv() {
        addGoal(new CustomEscapeDangerGoal(entity, 2.0F, 10, 10));
        addGoal(new CustomRangedAttackGoal(entity,1, entity.getRangeAggroMaxAttackRange(), entity.getRangeAggroMinAttackRange(), 40, 20.0F));
        addGoal(new PejelagartoAttackGoal(entity,1, true));
        addGoal(new PejelagartoMateGoal(entity, 1.15));
        addGoal(new SitGoal(entity));
        addGoal(new FollowOwnerNoTeleportGoal(entity, 1.0, 10.0F, 2.0F));
        addGoal(new TemptGoal(entity, 1.15, stack -> stack.isOf(Items.RABBIT), false));
        addGoal(new FollowParentGoal(entity, 1.0f));
        addGoal(new Goal[]{
                new WanderAroundGoal(entity, 1.0f),
                new PejelagartoWanderInWater(entity, 1.0)
        });
        addGoal(new LookAtEntityGoal(entity, PlayerEntity.class, 1.0f));
        addGoal(new LookAroundGoal(entity));

        addTarget(new RevengeGoal(entity));
        addTarget(new TerritorialTargetGoal<>(entity,  LivingEntity.class, 20, 20));
    }
}
