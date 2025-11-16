package com.jordi.trikivalscreatures.entity.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.pejelagarto.ai.PejelagartoAttackGoal;
import com.jordi.trikivalscreatures.entity.pejelagarto.ai.PejelagartoFollowOwnerGoal;
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
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PejelagartoEntity extends TameableEntity {

// ANIMATION VARIABLES
    //Idle Animation
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationCooldown = 0;

    //Attack Animation
    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationCooldown = 0;

// DATA TRACKER VARIABLES
    private static final TrackedData<Integer> VARIANT =
        DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> ATTACKING =
        DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private final int POISON_ATTACK_DURATION = 100; // 100 / 20 = 5 sec

//region INITIALIZATION
    public PejelagartoEntity(EntityType<? extends TameableEntity> entityType, World world) {
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
        this.dataTracker.startTracking(VARIANT, 0);
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
        this.dataTracker.set(VARIANT, nbt.getInt("Variant"));
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
//endregion

//region UPDATE
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if(this.isTamed()) {
            if(itemStack.isOf(Items.GLASS_BOTTLE)) {
                player.playSound(SoundEvents.ITEM_BOTTLE_FILL, 1.0F, 1.0F);

                ItemStack poisonPotion = PotionUtil.setCustomPotionEffects(Items.SPLASH_POTION.getDefaultStack(), List.of(getStatusEffectInstance()));

                ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, poisonPotion);
                player.setStackInHand(hand, itemStack2);

                return ActionResult.SUCCESS;
            }
            else if(itemStack.isEmpty()) {
                this.setSitting(!this.isSitting());
                return ActionResult.SUCCESS;
            }
        }
        else {
            if(itemStack.isOf(Items.RABBIT)) {
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                if (this.random.nextInt(3) == 0) {
                    this.setOwner(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
                } else {
                    this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
                }

                return ActionResult.SUCCESS;
            }
        }

        return super.interactMob(player, hand);
    }

    private @NotNull StatusEffectInstance getStatusEffectInstance() {
        StatusEffectInstance customEffect;
        if(isBaby()) {
            customEffect = new StatusEffectInstance(
                    StatusEffects.POISON,
                    100,
                    (getVariant() == PejelagartoVariant.PINK) ? 1 : 0
            );
        }
        else {
            customEffect = new StatusEffectInstance(
                    StatusEffects.POISON,
                    300,
                    (getVariant() == PejelagartoVariant.PINK) ? 2 : 1
            );
        }
        return customEffect;
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
        return this.dataTracker.get(VARIANT);
    }

    private void setVariant(PejelagartoVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId() & 255);
    }
//endregion

//region INTELLIGENCE
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new PejelagartoAttackGoal(this,1, true ));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.15));
        this.goalSelector.add(4, new PejelagartoFollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.add(5, new TemptGoal(this, 1.15,
                Ingredient.ofItems(Items.RABBIT), false));
        this.goalSelector.add(6, new FollowParentGoal(this, 1.0f));
        this.goalSelector.add(7, new WanderAroundGoal(this, 1.0f));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 1.0f));
        this.goalSelector.add(9, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new PejelagartoTargetGoal<>(this,  LivingEntity.class));
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

//region TAMING
    @Override
    public EntityView method_48926() {
        return this.getWorld();
    }
//endregion

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return effect.getEffectType() != StatusEffects.POISON && super.canHaveStatusEffect(effect);
    }
}
