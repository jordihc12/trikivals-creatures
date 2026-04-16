package com.jordi.trikivalscreatures.entity.ai;

import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.Vec3d;

import java.util.function.Function;

/**
 * Custom EscapeDangerGoal that expects horizontal and vertical range to find the position it needs to flee.
 * If no horizontal and vertical are passed it takes the default value of 5, making this class equal to EscapeDangerGoal.
 * **/
public class CustomEscapeDangerGoal extends EscapeDangerGoal {
    private final int horizontalRange;
    private final int verticalRange;

    public CustomEscapeDangerGoal(PathAwareEntity mob, double speed) {
        this(mob, speed, 5, 5, DamageTypeTags.PANIC_CAUSES);
    }

    public CustomEscapeDangerGoal(PathAwareEntity mob, double speed, int horizontalRange, int verticalRange) {
        this(mob, speed, horizontalRange, verticalRange, DamageTypeTags.PANIC_CAUSES);
    }

    public CustomEscapeDangerGoal(PathAwareEntity mob, double speed, int horizontalRange, int verticalRange, TagKey<DamageType> dangerousDamageTypes) {
        this(mob, speed, horizontalRange, verticalRange, entity -> dangerousDamageTypes);
    }

    public CustomEscapeDangerGoal(PathAwareEntity mob, double speed, int horizontalRange, int verticalRange, Function<PathAwareEntity, TagKey<DamageType>> entityToDangerousDamageTypes) {
        super(mob, speed, entityToDangerousDamageTypes);

        this.horizontalRange = horizontalRange;
        this.verticalRange = verticalRange;
    }

    @Override
    protected boolean findTarget() {
        Vec3d vec3d = NoPenaltyTargeting.find(this.mob, horizontalRange, verticalRange);
        if (vec3d == null) {
            return false;
        } else {
            this.targetX = vec3d.x;
            this.targetY = vec3d.y;
            this.targetZ = vec3d.z;
            return true;
        }
    }
}
