package com.jordi.trikivalscreatures.entity.dracopez.ai;

import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

/**
 * When a player holding glow berries is near, the DracoPez gets curious:
 * 1. APPROACH: swims slowly towards the player.
 * 2. CIRCLE: swims slowly around the player a couple of times.
 * 3. FOLLOW: keeps close to the player, gently swimming around them (never fully still)
 *    and slowly following them if they move.
 * Movement goes straight through the move control (no pathfinding), so it also works
 * when the player stands on the shore: the DracoPez gets as close as the water allows.
 */
public class DracoPezCuriousGoal extends Goal {
    private enum Phase { APPROACH, CIRCLE, FOLLOW }

    private static final double CIRCLE_RADIUS = 2.75;
    private static final double APPROACH_DISTANCE = 3.2;
    private static final double FOLLOW_DISTANCE = 2.2;
    // ~1.6 blocks/s along the circle: one lap every ~10 seconds
    private static final double ANGLE_STEP = 0.03;          // radians per tick
    private static final double LAPS = 2.0;
    private static final double TOTAL_CIRCLE_ANGLE = LAPS * Math.PI * 2.0;

    private final DracoPezEntity dracoPez;
    private final double range;

    @Nullable
    private PlayerEntity player;
    private Phase phase;
    private double angle;
    private double travelledAngle;
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
        this.travelledAngle = 0.0;
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

        this.dracoPez.getLookControl().lookAt(this.player, 10.0F, (float) this.dracoPez.getMaxLookPitchChange());

        double px = this.player.getX();
        double pz = this.player.getZ();
        double distSq = this.dracoPez.squaredDistanceTo(this.player);

        switch (this.phase) {
            case APPROACH -> {
                if (distSq <= APPROACH_DISTANCE * APPROACH_DISTANCE) {
                    this.angle = Math.atan2(this.dracoPez.getZ() - pz, this.dracoPez.getX() - px);
                    this.phase = Phase.CIRCLE;
                } else {
                    this.swimNearPlayer(FOLLOW_DISTANCE, 0.0, DracoPezEntity.SPEED_CALM);
                }
            }
            case CIRCLE -> {
                this.angle += ANGLE_STEP * this.direction;
                this.travelledAngle += ANGLE_STEP;

                double x = px + Math.cos(this.angle) * CIRCLE_RADIUS;
                double z = pz + Math.sin(this.angle) * CIRCLE_RADIUS;
                // Little up and down wave while circling
                double y = this.waterY(x, this.preferredY() + Math.sin(this.travelledAngle * 2.0) * 0.35, z);
                this.dracoPez.getMoveControl().moveTo(x, y, z, DracoPezEntity.SPEED_CALM);

                if (this.travelledAngle >= TOTAL_CIRCLE_ANGLE) {
                    this.phase = Phase.FOLLOW;
                }
            }
            case FOLLOW -> {
                if (distSq > (FOLLOW_DISTANCE + 1.5) * (FOLLOW_DISTANCE + 1.5)) {
                    // The player moved away: slowly go back to them
                    this.swimNearPlayer(FOLLOW_DISTANCE, 0.0, DracoPezEntity.SPEED_CALM);
                } else {
                    // Close enough: drift gently from side to side in front of the player
                    double sway = Math.sin(this.ticks * 0.05) * 0.9;
                    this.swimNearPlayer(FOLLOW_DISTANCE, sway, DracoPezEntity.SPEED_CALM * 0.7);
                }
            }
        }
    }

    /**
     * Swims to a point at {@code distance} blocks from the player, on the side where the DracoPez is,
     * shifted {@code sideOffset} blocks sideways.
     */
    private void swimNearPlayer(double distance, double sideOffset, double speed) {
        if (this.player == null) {
            return;
        }
        double dx = this.dracoPez.getX() - this.player.getX();
        double dz = this.dracoPez.getZ() - this.player.getZ();
        double len = Math.sqrt(dx * dx + dz * dz);
        if (len < 1.0E-4) {
            dx = 1.0;
            dz = 0.0;
            len = 1.0;
        }
        dx /= len;
        dz /= len;

        double x = this.player.getX() + dx * distance - dz * sideOffset;
        double z = this.player.getZ() + dz * distance + dx * sideOffset;
        double y = this.waterY(x, this.preferredY(), z);
        this.dracoPez.getMoveControl().moveTo(x, y, z, speed);
    }

    private double preferredY() {
        return this.player == null ? this.dracoPez.getY() : this.player.getEyeY() - 0.6;
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
