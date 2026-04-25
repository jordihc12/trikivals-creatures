package com.jordi.trikivalscreatures.entity.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;

import java.util.function.Function;

public abstract class IntelligenceManager<T extends MobEntity> {
    private int goalPriority;
    private int targetPriority;
    protected final T entity;

    protected IntelligenceManager(T entity) {
        this.entity = entity;
        goalPriority = 0;
        targetPriority = 0;
    }

    protected void addGoal(Goal goal) {
        entity.goalSelector.add(goalPriority, goal);
        goalPriority++;
    }

    protected void addGoal(Goal[] goals) {
        for (var goal: goals) {
            entity.goalSelector.add(goalPriority, goal);
        }
        goalPriority++;
    }

    protected void addTarget(Goal goal) {
        entity.targetSelector.add(targetPriority, goal);
        targetPriority++;
    }

    protected void addTarget(Goal[] goals) {
        for (var goal: goals) {
            entity.targetSelector.add(targetPriority, goal);
        }
        targetPriority++;
    }

    public abstract void setGoalsPriv();

    /**
     * The "Universal" static initializer.
     * @param entity The entity instance
     * @param factory The IntelligenceManager subclass initializer
     */
    public static <T extends MobEntity> void setGoals(T entity, Function<T, IntelligenceManager<T>> factory) {
        factory.apply(entity).setGoalsPriv();
    }
}
