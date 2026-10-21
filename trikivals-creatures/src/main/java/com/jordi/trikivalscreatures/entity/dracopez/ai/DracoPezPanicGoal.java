package com.jordi.trikivalscreatures.entity.dracopez.ai;

import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

/**
 * After being hit, the DracoPez darts away at full speed for a few seconds,
 * always trying to get as far as possible from whoever hit it.
 * Every half second it picks a new escape point straight away from the attacker
 * (or the closest free direction if there is a wall), checking the way is clear water.
 */
public class DracoPezPanicGoal extends Goal {
    private static final double ESCAPE_DISTANCE = 10.0;
    private static final int REPICK_TICKS = 10;
    // Directions tried when straight away is blocked (degrees)
    private static final double[] ANGLE_OFFSETS = {0, 25, -25, 50, -50, 80, -80, 115, -115};

    private final DracoPezEntity dracoPez;
    private int repickCooldown;

    public DracoPezPanicGoal(DracoPezEntity dracoPez) {
        this.dracoPez = dracoPez;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        return this.dracoPez.isInPanic() && this.dracoPez.isTouchingWater();
    }

    @Override
    public boolean shouldContinue() {
        return this.canStart();
    }

    @Override
    public void start() {
        this.repickCooldown = 0;
        this.dracoPez.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.dracoPez.getNavigation().stop();
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (--this.repickCooldown > 0) {
            return;
        }
        this.repickCooldown = REPICK_TICKS;

        Vec3d away = this.awayDirection(this.dracoPez.getPanicSource());
        Vec3d target = this.findEscapePoint(away);
        if (target != null) {
            this.dracoPez.getMoveControl().moveTo(target.x, target.y, target.z, DracoPezEntity.SPEED_PANIC);
        }
    }

    private Vec3d awayDirection(@Nullable LivingEntity attacker) {
        Vec3d dir;
        if (attacker != null) {
            dir = new Vec3d(this.dracoPez.getX() - attacker.getX(), 0.0, this.dracoPez.getZ() - attacker.getZ());
        } else {
            dir = Vec3d.fromPolar(0.0F, this.dracoPez.getYaw());
        }
        if (dir.lengthSquared() < 1.0E-4) {
            dir = new Vec3d(this.dracoPez.getRandom().nextDouble() - 0.5, 0.0, this.dracoPez.getRandom().nextDouble() - 0.5);
        }
        return dir.normalize();
    }

    @Nullable
    private Vec3d findEscapePoint(Vec3d away) {
        Vec3d from = new Vec3d(this.dracoPez.getX(), this.dracoPez.getY(), this.dracoPez.getZ());
        // Dive a bit deeper while escaping
        double dy = -1.0 + this.dracoPez.getRandom().nextDouble() * 0.5;

        for (double distance = ESCAPE_DISTANCE; distance >= 3.0; distance -= 3.5) {
            for (double offset : ANGLE_OFFSETS) {
                double rad = Math.toRadians(offset);
                double cos = Math.cos(rad);
                double sin = Math.sin(rad);
                Vec3d dir = new Vec3d(away.x * cos - away.z * sin, 0.0, away.x * sin + away.z * cos);

                for (double y : new double[]{dy, 0.0}) {
                    Vec3d target = from.add(dir.x * distance, y, dir.z * distance);
                    if (this.isClearWater(from, target)) {
                        return target;
                    }
                }
            }
        }
        return null;
    }

    /** True if every block on the straight line between both points is water. */
    private boolean isClearWater(Vec3d from, Vec3d to) {
        World world = this.dracoPez.getEntityWorld();
        int steps = (int) Math.ceil(from.distanceTo(to));
        for (int i = 1; i <= steps; i++) {
            Vec3d p = from.lerp(to, (double) i / steps);
            if (!world.getFluidState(BlockPos.ofFloored(p)).isIn(FluidTags.WATER)) {
                return false;
            }
        }
        return true;
    }
}
