package com.jordi.trikivalscreatures.entity.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.utils.ModTags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PejelagartoEntity extends AnimalEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationCooldown = 0;

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.INTEGER);

    //region INITIALIZATION
    public PejelagartoEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createPejelagartoAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15));
        this.goalSelector.add(2, new TemptGoal(this, 1.15,
                Ingredient.ofItems(Items.RABBIT), false));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.0f));
        this.goalSelector.add(4, new WanderAroundGoal(this, 1.0f));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 1.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        RegistryKey<Biome> biome = world.getBiome(this.getBlockPos()).getKey().orElseGet(null);
        if(biome != null) {
            List<PejelagartoVariant> possibleVariants = new java.util.ArrayList<>(Collections.emptyList());
            List.of(PejelagartoVariant.values()).forEach(variant -> {
                RegistryKey<Biome>[] biomeKeys = variant.getSpawnBiomes();

                //Variant cannot spawn in any biome. Returning!!
                if(biomeKeys == null) {
                    return;
                }

                //Variant can spawn in any biome. Adding as possible variant and returning
                if(biomeKeys.length == 0) {
                    possibleVariants.add(variant);
                    return;
                }

                // Variant spawn in specific biomes. Check if current biome is one of those specific biomes.
                if(Arrays.stream(biomeKeys).anyMatch(biomeRegistryKey -> biomeRegistryKey == biome)) {
                    possibleVariants.add(variant);
                }
            });

            PejelagartoVariant variant = Util.getRandom(possibleVariants, this.random);
            setVariant(variant);
        }
        else {
            TrikiValsCreatures.LOGGER.info("No biome detected. Spawning random variant");

            PejelagartoVariant variant = Util.getRandom(PejelagartoVariant.values(), this.random);
            setVariant(variant);
        }

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public static boolean canSpawn(EntityType<PejelagartoEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(ModTags.Blocks.PEJELAGARTO_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }
    //endregion

    //region DataTracker
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DATA_ID_TYPE_VARIANT, 0);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }
    //endregion

    //region Animations
    @Override
    protected void updateLimbs(float posDelta) {
        float f = (this.getPose() == EntityPose.STANDING) ? Math.min(posDelta * 6.0F, 1.0F) : 0;
        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationCooldown <= 0) {
            this.idleAnimationCooldown = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationCooldown--;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient) {
            setupAnimationStates();
        }
    }
    //endregion

    //region Breeding
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.RABBIT);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        //Create baby entity
        PejelagartoEntity baby = ModEntities.PEJELAGARTO.create(world);
        baby.setVariant(Util.getRandom(PejelagartoVariant.values(), this.random));
        return baby;
    }
    //endregion

    //region Sounds
    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_GHAST_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_DOLPHIN_DEATH;
    }
    //endregion

    //region Variant
    public PejelagartoVariant getVariant() {
        return PejelagartoVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(PejelagartoVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }
    //endregion
}
