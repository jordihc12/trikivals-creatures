package com.jordi.trikivalscreatures.entity.dracopez.ai;

import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

/**
 * When a player holding glow berries is near, the DracoPez gets curious:
 * 1. APPROACH: swims towards the player.
 * 2. CIRCLE: swims around the player a couple of times.
 * 3. STAY: stays still in front of the player, looking at them.
 */
public class DracoPezCuriousGoal extends Goal {
    private enum Phase { APPROACH, CIRCLE, STAY }

    private static final double CIRCLE_RADIUS = 2.75;
    private static final double APPROACH_DISTANCE = 3.5;
    private static final double ANGLE_STEP = 0.11;          // radians per tick
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
    private int repathCooldown;

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
        this.repathCooldown = 0;
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

        this.dracoPez.getLookControl().lookAt(this.player, 10.0F, (float) this.dracoPez.getMaxLookPitchChange());

        double targetY = this.player.getEyeY() - 0.6;

        switch (this.phase) {
            case APPROACH -> {
                if (this.dracoPez.squaredDistanceTo(this.player) <= APPROACH_DISTANCE * APPROACH_DISTANCE) {
                    this.dracoPez.getNavigation().stop();
                    this.angle = Math.atan2(this.dracoPez.getZ() - this.player.getZ(), this.dracoPez.getX() - this.player.getX());
                    this.phase = Phase.CIRCLE;
                } else if (--this.repathCooldown <= 0) {
                    this.repathCooldown = 10;
                    this.dracoPez.getNavigation().startMovingTo(this.player.getX(), targetY, this.player.getZ(), DracoPezEntity.SPEED_CURIOUS);
                }
            }
            case CIRCLE -> {
                this.angle += ANGLE_STEP * this.direction;
                this.travelledAngle += ANGLE_STEP;

                double x = this.player.getX() + Math.cos(this.angle) * CIRCLE_RADIUS;
                double z = this.player.getZ() + Math.sin(this.angle) * CIRCLE_RADIUS;
                // Little up and down wave while circling
                double y = targetY + Math.sin(this.travelledAngle * 2.0) * 0.35;
                this.dracoPez.getMoveControl().moveTo(x, y, z, DracoPezEntity.SPEED_CURIOUS);

                if (this.travelledAngle >= TOTAL_CIRCLE_ANGLE) {
                    this.phase = Phase.STAY;
                }
            }
            case STAY -> {
                this.dracoPez.getNavigation().stop();
                // Gently stay at the same distance in front of the player
                double dist = Math.sqrt(this.dracoPez.squaredDistanceTo(this.player));
                if (dist > APPROACH_DISTANCE + 1.5) {
                    this.dracoPez.getMoveControl().moveTo(this.player.getX(), targetY, this.player.getZ(), DracoPezEntity.SPEED_CALM);
                } else {
                    this.dracoPez.setVelocity(this.dracoPez.getVelocity().multiply(0.8));
                    this.dracoPez.setMovementSpeed(MathHelper.lerp(0.2F, this.dracoPez.getMovementSpeed(), 0.0F));
                }
            }
        }
    }
}
