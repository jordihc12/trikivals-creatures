package com.jordi.trikivalscreatures.entity.dracopez.ai;

import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import com.jordi.trikivalscreatures.entity.goals.IntelligenceManager;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;

public class DracoPezIntelligenceManager extends IntelligenceManager<DracoPezEntity> {
    public DracoPezIntelligenceManager(DracoPezEntity entity) {
        super(entity);
    }

    @Override
    public void setGoalsPriv() {
        // Runs away at full speed from any player that is NOT holding glow berries
        addGoal(new FleeEntityGoal<>(entity, PlayerEntity.class, 8.0F, DracoPezEntity.SPEED_FLEE_SLOW, DracoPezEntity.SPEED_FLEE_FAST,
                living -> !living.isSpectator() && !DracoPezEntity.isAttractedBy(living)));
        // Breeding with glow berries
        addGoal(new AnimalMateGoal(entity, DracoPezEntity.SPEED_CALM));
        // Holding glow berries: approaches, circles around the player twice and stays still looking at them
        addGoal(new DracoPezCuriousGoal(entity, DracoPezEntity.CURIOUS_RANGE));
        addGoal(new FollowParentGoal(entity, DracoPezEntity.SPEED_CALM * 1.1));
        addGoal(new SwimAroundGoal(entity, DracoPezEntity.SPEED_CALM, 40));
        addGoal(new LookAroundGoal(entity));
    }
}
