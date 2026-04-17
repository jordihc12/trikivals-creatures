package com.jordi.trikivalscreatures.entity.pejelagarto.ai;

import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class PejelagartoWanderInWater extends MoveToTargetPosGoal {
    private final PejelagartoEntity pejelagarto;

    public PejelagartoWanderInWater(PejelagartoEntity pejelagarto, double speed) {
        super(pejelagarto, pejelagarto.isBaby() ? 2.0 : speed, 24);
        this.pejelagarto = pejelagarto;
        this.lowestY = -1;
    }

    @Override
    public boolean shouldContinue() {
        return !this.pejelagarto.isTouchingWater() &&
                this.tryingTime <= 1200 &&
                this.pejelagarto.isFullyHydrated() &&
                this.isTargetPos(this.pejelagarto.getEntityWorld(), this.targetPos);
    }

    @Override
    public boolean canStart() {
        if(this.pejelagarto.isTouchingWater() && !this.pejelagarto.isDehydrated()) return false;
        return super.canStart();
    }

    @Override
    public boolean shouldResetPath() {
        return this.tryingTime % 160 == 0;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos).isOf(Blocks.WATER);
    }
}
