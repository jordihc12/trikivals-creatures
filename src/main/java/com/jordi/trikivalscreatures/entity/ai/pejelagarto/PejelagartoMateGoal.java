package com.jordi.trikivalscreatures.entity.ai.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.blocks.ModBlocks;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlock;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.event.GameEvent;

public class PejelagartoMateGoal extends AnimalMateGoal {
    private final PejelagartoEntity pejelagarto;
    private final int NUMBER_OF_EGGS = 1;

    public PejelagartoMateGoal(PejelagartoEntity pejelagarto, double speed) {
        super(pejelagarto, speed);
        this.pejelagarto = pejelagarto;
    }

    @Override
    public boolean canStart() {
        return super.canStart();
    }

    @Override
    protected void breed() {
        ServerPlayerEntity serverPlayerEntity = this.animal.getLovingPlayer();
        if (serverPlayerEntity == null && this.mate.getLovingPlayer() != null) {
            serverPlayerEntity = this.mate.getLovingPlayer();
        }

        if (serverPlayerEntity != null) {
            serverPlayerEntity.incrementStat(Stats.ANIMALS_BRED);
            Criteria.BRED_ANIMALS.trigger(serverPlayerEntity, this.animal, this.mate, null);
        }

        //Place Egg
        PejelagartoVariant variant = this.pejelagarto.getVariant();
        if(this.mate instanceof PejelagartoEntity pejelagartoMate && this.pejelagarto.getRandom().nextBoolean()) {
            variant = pejelagartoMate.getVariant();
        }

        BlockState blockState = ModBlocks.PEJELAGARTO_EGG.getDefaultState()
                .with(PejelagartoEggBlock.EGGS, NUMBER_OF_EGGS)
                .with(PejelagartoEggBlock.VARIANT, variant);
        pejelagarto.getBlockPos();

        world.setBlockState(pejelagarto.getBlockPos().south(), blockState, Block.NOTIFY_ALL);
        world.emitGameEvent(GameEvent.BLOCK_PLACE, pejelagarto.getBlockPos().south(), GameEvent.Emitter.of(this.pejelagarto, blockState));

        this.animal.setBreedingAge(6000);
        this.mate.setBreedingAge(6000);
        this.animal.resetLoveTicks();
        this.mate.resetLoveTicks();
        Random random = this.animal.getRandom();
        if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.animal.getX(), this.animal.getY(), this.animal.getZ(), random.nextInt(7) + 1));
        }
    }
}
