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
        // When hit: darts away from the attacker at full speed for a few seconds
        addGoal(new DracoPezPanicGoal(entity));
        // Runs away at full speed from any player that is NOT holding glow berries
        addGoal(new FleeEntityGoal<>(entity, PlayerEntity.class, 8.0F, DracoPezEntity.SPEED_FLEE_SLOW, DracoPezEntity.SPEED_FLEE_FAST,
                living -> !living.isSpectator() && !DracoPezEntity.isAttractedBy(living)));
        // Breeding with glow berries
        addGoal(new AnimalMateGoal(entity, DracoPezEntity.SPEED_CALM));
        // Holding glow berries: approaches slowly and keeps circling around the player
        addGoal(new DracoPezCuriousGoal(entity, DracoPezEntity.CURIOUS_RANGE));
        addGoal(new FollowParentGoal(entity, DracoPezEntity.SPEED_CALM * 1.1));
        // Calm random swimming like fish: picks a random spot, swims there, stops a few seconds, repeats
        addGoal(new SwimAroundGoal(entity, DracoPezEntity.SPEED_CALM, 80));
        addGoal(new LookAroundGoal(entity));
    }
}
