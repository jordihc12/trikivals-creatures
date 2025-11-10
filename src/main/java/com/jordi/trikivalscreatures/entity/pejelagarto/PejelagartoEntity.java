package com.jordi.trikivalscreatures.entity.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.pejelagarto.ai.PejelagartoAttackGoal;
import com.jordi.trikivalscreatures.entity.pejelagarto.ai.PejelagartoTargetGoal;
import com.jordi.trikivalscreatures.utils.ModTags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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

//region ANIMATION VARIABLES
    //Idle Animation
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationCooldown = 0;

    //Attack Animation
    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationCooldown = 0;
//endregion

//region DATA TRACKER VARIABLES
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
//endregion

    private final int POISON_ATTACK_DURATION = 100; // 100 / 2 = 5 sec

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

    private void setCustomEntityAttributes() {
        //Modifying base attribute values of specific variants
        switch (getVariant()) {
            case JUNGLE -> {
                double speedBaseValue = this.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(speedBaseValue * 1.2F);
            }
            case SAND -> {
                double attackBaseValue = this.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(attackBaseValue * 1.2F);

                double healthBaseValue = this.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH);
                this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(healthBaseValue * 2.0F);
            }
        }
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        RegistryKey<Biome> biome = world.getBiome(this.getBlockPos()).getKey().orElseGet(null);
        PejelagartoVariant thisVariant;
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

            thisVariant = Util.getRandom(possibleVariants, this.random);
        }
        else {
            TrikiValsCreatures.LOGGER.info("No biome detected. Spawning random variant");

            thisVariant = Util.getRandom(PejelagartoVariant.values(), this.random);
        }
        this.setVariant(thisVariant);
        this.setCustomEntityAttributes();

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public static boolean canSpawn(EntityType<PejelagartoEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(ModTags.Blocks.PEJELAGARTO_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }
//endregion

//region DATA TRACKER
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DATA_ID_TYPE_VARIANT, 0);
        this.dataTracker.startTracking(ATTACKING, false);
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

//region ANIMATIONS
    @Override
    protected void updateLimbs(float posDelta) {
        float f = (this.getPose() == EntityPose.STANDING) ? Math.min(posDelta * 6.0F, 1.0F) : 0;
        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    public void resetAttackAnimationCooldown() {
        this.attackAnimationCooldown = 0;
    }

    private void setupAnimationStates() {
        //Idle Animation
        if (this.idleAnimationCooldown <= 0) {
            this.idleAnimationCooldown = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationCooldown--;
        }

        //Attack Animation
        if(this.isAttacking() && this.attackAnimationCooldown <= 0) {
            this.attackAnimationCooldown = this.random.nextInt(40) + 80;
            this.attackAnimationState.start(this.age);
        }
        else {
            this.attackAnimationCooldown--;
        }

        if(!this.isAttacking()) this.attackAnimationState.stop();
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient) {
            setupAnimationStates();
        }
    }
//endregion

//region BREEDING
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.RABBIT);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        //Create baby entity
        PejelagartoEntity baby = ModEntities.PEJELAGARTO.create(world);
        baby.setVariant(Util.getRandom(PejelagartoVariant.values(), this.random));
        baby.setCustomEntityAttributes();
        return baby;
    }
//endregion

//region SOUNDS
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

//region VARIANT
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

//region INTELLIGENCE
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new PejelagartoAttackGoal(this,1, true ));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.15));
        this.goalSelector.add(3, new TemptGoal(this, 1.15,
                Ingredient.ofItems(Items.RABBIT), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0f));
        this.goalSelector.add(5, new WanderAroundGoal(this, 1.0f));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 1.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new PejelagartoTargetGoal<>(this,  PlayerEntity.class));
    }

    //Attack Logic
    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if(!super.tryAttack(target)) {
            return false;
        }
        else {
            if(target instanceof LivingEntity) {
                StatusEffectInstance customEffect = new StatusEffectInstance(
                        StatusEffects.POISON,
                        POISON_ATTACK_DURATION,
                        (getVariant() == PejelagartoVariant.PINK) ? 1 : 0
                        //If variant is pink: poison 2. If other variants poison 1
                );
                ((LivingEntity)target).addStatusEffect(customEffect, this);
            }
            return true;
        }
    }
//endregion
}
