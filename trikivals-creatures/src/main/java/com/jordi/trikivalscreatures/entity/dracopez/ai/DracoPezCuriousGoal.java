package com.jordi.trikivalscreatures.entity.dracopez.ai;

import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

/**
 * While a player holding glow berries is near, the DracoPez swims slowly towards them and then
 * keeps circling around them at CIRCLE_RADIUS blocks, for as long as they hold the berries.
 * When the player puts the berries away the goal stops and the flee goal (higher priority)
 * makes it dart away.
 * Movement goes straight through DracoPezMoveControl (no pathfinding), so it also works
 * when the player stands on the shore: it gets as close as the water allows.
 */
public class DracoPezCuriousGoal extends Goal {
    private enum Phase { APPROACH, CIRCLE }

    public static final double CIRCLE_RADIUS = 3.5;
    // How far ahead (radians) along the circle the swim target is placed: gives a smooth orbit
    private static final double LEAD_ANGLE = 0.6;

    private final DracoPezEntity dracoPez;
    private final double range;

    @Nullable
    private PlayerEntity player;
    private Phase phase;
    private double direction;
    private int ticks;

    public DracoPezCuriousGoal(DracoPezEntity dracoPez, double range) {
        this.dracoPez = dracoPez;
        this.range = range;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (!this.dracoPez.isTouchingWater()) {
            return false;
        }

        PlayerEntity closest = this.dracoPez.getEntityWorld().getClosestPlayer(this.dracoPez, this.range);
        if (closest == null || !DracoPezEntity.isAttractedBy(closest)) {
            return false;
        }

        this.player = closest;
        return true;
    }

    @Override
    public boolean shouldContinue() {
        return this.player != null
                && this.player.isAlive()
                && this.dracoPez.isTouchingWater()
                && DracoPezEntity.isAttractedBy(this.player)
                && this.dracoPez.squaredDistanceTo(this.player) < (this.range + 4.0) * (this.range + 4.0);
    }

    @Override
    public void start() {
        this.phase = Phase.APPROACH;
        this.direction = this.dracoPez.getRandom().nextBoolean() ? 1.0 : -1.0;
        this.ticks = 0;
        this.dracoPez.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.player = null;
        this.dracoPez.getNavigation().stop();
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.player == null) {
            return;
        }
        this.ticks++;

        double px = this.player.getX();
        double pz = this.player.getZ();
        double dx = this.dracoPez.getX() - px;
        double dz = this.dracoPez.getZ() - pz;
        double horizontalDist = Math.sqrt(dx * dx + dz * dz);

        if (this.phase == Phase.APPROACH) {
            // Look at the player while coming closer
            this.dracoPez.getLookControl().lookAt(this.player, 10.0F, (float) this.dracoPez.getMaxLookPitchChange());

            if (horizontalDist <= CIRCLE_RADIUS + 0.75) {
                this.phase = Phase.CIRCLE;
            } else {
                double nx = horizontalDist < 1.0E-4 ? 1.0 : dx / horizontalDist;
                double nz = horizontalDist < 1.0E-4 ? 0.0 : dz / horizontalDist;
                double x = px + nx * CIRCLE_RADIUS;
                double z = pz + nz * CIRCLE_RADIUS;
                this.dracoPez.getMoveControl().moveTo(x, this.waterY(x, this.preferredY(), z), z, DracoPezEntity.SPEED_CALM);
            }
            return;
        }

        // CIRCLE: aim at a point a bit ahead along the circle, from where the DracoPez actually is
        double currentAngle = Math.atan2(dz, dx);
        double targetAngle = currentAngle + LEAD_ANGLE * this.direction;
        double x = px + Math.cos(targetAngle) * CIRCLE_RADIUS;
        double z = pz + Math.sin(targetAngle) * CIRCLE_RADIUS;
        // Gentle up and down wave while circling
        double y = this.waterY(x, this.preferredY() + Math.sin(this.ticks * 0.04) * 0.4, z);
        this.dracoPez.getMoveControl().moveTo(x, y, z, DracoPezEntity.SPEED_CURIOUS);
    }

    private double preferredY() {
        return this.player == null ? this.dracoPez.getY() : this.player.getEyeY() - 0.8;
    }

    /** Keeps the target inside the water: if the desired height is not water, stays at the current height. */
    private double waterY(double x, double y, double z) {
        BlockPos pos = BlockPos.ofFloored(x, y, z);
        if (this.dracoPez.getEntityWorld().getFluidState(pos).isIn(FluidTags.WATER)) {
            return y;
        }
        return this.dracoPez.getY();
    }
}
