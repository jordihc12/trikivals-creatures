package com.jordi.trikivalscreatures.entity.creatures.pejelagarto.ai;

import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.PejelagartoEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.math.MathHelper;

public class PejelagartoMoveControl extends MoveControl {
    private final PejelagartoEntity pejelagarto;

    public PejelagartoMoveControl(PejelagartoEntity pejelagarto) {
        super(pejelagarto);
        this.pejelagarto = pejelagarto;
    }

    private void updateVelocity() {
        if (this.pejelagarto.isTouchingWater()) {
            this.pejelagarto.setVelocity(this.pejelagarto.getVelocity().add(0.0, 0.005, 0.0));
            this.pejelagarto.setMovementSpeed(Math.max(this.pejelagarto.getMovementSpeed() * 1.05f, 0.08F));

            if (this.pejelagarto.isBaby()) {
                this.pejelagarto.setMovementSpeed(Math.max(this.pejelagarto.getMovementSpeed(), 0.06F));
            }
        } else if (this.pejelagarto.isOnGround()) {
            this.pejelagarto.setMovementSpeed(Math.max(this.pejelagarto.getMovementSpeed(), 0.06F));
        }
    }

    @Override
    public void tick() {
        this.updateVelocity();
        if (this.state == State.MOVE_TO && !this.pejelagarto.getNavigation().isIdle()) {
            double d = this.targetX - this.pejelagarto.getX();
            double e = this.targetY - this.pejelagarto.getY();
            double f = this.targetZ - this.pejelagarto.getZ();
            double g = Math.sqrt(d * d + e * e + f * f);
            if (g < 1.0E-5F) {
                this.entity.setMovementSpeed(0.0F);
            } else {
                e /= g;
                float h = (float)(MathHelper.atan2(f, d) * 180.0F / (float)Math.PI) - 90.0F;
                this.pejelagarto.setYaw(this.wrapDegrees(this.pejelagarto.getYaw(), h, 90.0F));
                this.pejelagarto.bodyYaw = this.pejelagarto.getYaw();
                float i = (float)(this.speed * this.pejelagarto.getAttributeValue(EntityAttributes.MOVEMENT_SPEED));
                this.pejelagarto.setMovementSpeed(MathHelper.lerp(0.125F, this.pejelagarto.getMovementSpeed(), i));
                this.pejelagarto.setVelocity(this.pejelagarto.getVelocity().add(0.0, this.pejelagarto.getMovementSpeed() * e * 0.1, 0.0));
            }
        } else {
            super.tick();
        }
    }
}
