package com.jordi.trikivalscreatures.entity.dracopez.ai;

import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.math.MathHelper;

/**
 * Fish-like movement for the DracoPez.
 * Unlike the vanilla aquatic/fish controls it moves to any target set with moveTo(),
 * even without an active navigation path (used by the curious behaviour),
 * turns quickly towards the target (no dolphin-like orbits) and stops when it arrives.
 * The actual push is applied in DracoPezEntity.travel(): speed in blocks/s ~= 2 x speed multiplier.
 */
public class DracoPezMoveControl extends MoveControl {
    private static final double ARRIVE_DISTANCE = 0.6;
    private static final float MAX_TURN_PER_TICK = 20.0F;

    private final DracoPezEntity dracoPez;

    public DracoPezMoveControl(DracoPezEntity dracoPez) {
        super(dracoPez);
        this.dracoPez = dracoPez;
    }

    @Override
    public void tick() {
        if (this.state != State.MOVE_TO || !this.dracoPez.isTouchingWater()) {
            this.dracoPez.setMovementSpeed(MathHelper.lerp(0.25F, this.dracoPez.getMovementSpeed(), 0.0F));
            return;
        }

        double dx = this.targetX - this.dracoPez.getX();
        double dy = this.targetY - this.dracoPez.getY();
        double dz = this.targetZ - this.dracoPez.getZ();
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

        if (dist < ARRIVE_DISTANCE) {
            // Arrived: brake smoothly and wait for a new target
            this.state = State.WAIT;
            this.dracoPez.setMovementSpeed(MathHelper.lerp(0.25F, this.dracoPez.getMovementSpeed(), 0.0F));
            return;
        }

        // Turn towards the target
        if (dx * dx + dz * dz > 1.0E-4) {
            float targetYaw = (float) (MathHelper.atan2(dz, dx) * 180.0F / (float) Math.PI) - 90.0F;
            this.dracoPez.setYaw(this.wrapDegrees(this.dracoPez.getYaw(), targetYaw, MAX_TURN_PER_TICK));
            this.dracoPez.bodyYaw = this.dracoPez.getYaw();
            this.dracoPez.headYaw = this.dracoPez.getYaw();
        }

        // Pitch follows the vertical direction (used by the model)
        float targetPitch = (float) (-MathHelper.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * 180.0F / (float) Math.PI);
        this.dracoPez.setPitch(this.wrapDegrees(this.dracoPez.getPitch(), MathHelper.clamp(targetPitch, -60.0F, 60.0F), 5.0F));

        // Accelerate / brake smoothly towards the requested speed; slow down when getting close
        float wanted = (float) (this.speed * this.dracoPez.getAttributeValue(EntityAttributes.MOVEMENT_SPEED));
        if (dist < 2.0) {
            wanted *= (float) Math.max(0.35, dist / 2.0);
        }
        float speed = MathHelper.lerp(0.1F, this.dracoPez.getMovementSpeed(), wanted);
        this.dracoPez.setMovementSpeed(speed);

        // Vertical push with the same strength as the horizontal one (see DracoPezEntity.travel)
        this.dracoPez.setVelocity(this.dracoPez.getVelocity().add(0.0, speed * 0.01 * (dy / dist), 0.0));
    }
}
