package com.jordi.trikivalscreatures.entity.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.ai.*;
import com.jordi.trikivalscreatures.entity.ai.pejelagarto.PejelagartoAttackGoal;
import com.jordi.trikivalscreatures.entity.ai.pejelagarto.PejelagartoMateGoal;
import com.jordi.trikivalscreatures.entity.ai.TerritorialTargetGoal;
import com.jordi.trikivalscreatures.entity.pejelagarto.spit.PejelagartoSpitEntity;
import com.jordi.trikivalscreatures.utils.ModTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
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
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PejelagartoEntity extends TameableEntity implements CustomRangedAttackMob, CustomMeleeAttackMob {

// ANIMATION VARIABLES
    //Idle Animation
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationCooldown = 0;

    //Attack Animation
    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationCooldown = 0;

// DATA TRACKER VARIABLES
    public static final TrackedData<Integer> VARIANT =
        DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> ATTACKING =
        DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private final int POISON_ATTACK_DURATION = 100; // 100 / 20 = 5 sec

//region INITIALIZATION
    public PejelagartoEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createPejelagartoAttributes() {
        return AnimalEntity.createMobAttributes().add(EntityAttributes.TEMPT_RANGE, 2.5F)
                .add(EntityAttributes.MAX_HEALTH, 10.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.ATTACK_DAMAGE, 3f);
    }

    private void setCustomEntityAttributes() {
        //Modifying base attribute values of specific variants
        switch (getVariant()) {
            case JUNGLE -> {
                double speedBaseValue = this.getAttributeBaseValue(EntityAttributes.MOVEMENT_SPEED);
                EntityAttributeInstance speedAttr = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
                if(speedAttr != null) {
                    speedAttr.setBaseValue(speedBaseValue * 1.2F);
                }
            }
            case SAND -> {
                double attackBaseValue = this.getAttributeBaseValue(EntityAttributes.ATTACK_DAMAGE);
                EntityAttributeInstance attackAttr = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
                if(attackAttr != null) {
                    attackAttr.setBaseValue(attackBaseValue * 1.2F);
                }

                double healthBaseValue = this.getAttributeBaseValue(EntityAttributes.MAX_HEALTH);
                EntityAttributeInstance healthAttr = this.getAttributeInstance(EntityAttributes.MAX_HEALTH);
                if(healthAttr != null) {
                    healthAttr.setBaseValue(healthBaseValue * 2.0F);
                }
            }
        }
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
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
            TrikiValsCreatures.LogStringWarning(this, "No biome detected. Spawning random variant");

            thisVariant = Util.getRandom(PejelagartoVariant.values(), this.random);
        }
        this.setVariant(thisVariant);
        this.setCustomEntityAttributes();

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public static boolean canSpawn(EntityType<PejelagartoEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(ModTags.Blocks.PEJELAGARTO_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }
//endregion

//region DATA TRACKER
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
        builder.add(ATTACKING, false);
    }

    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        view.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);
        this.dataTracker.set(VARIANT, view.getInt("Variant", 0));
    }
//endregion

//region ANIMATIONS
    @Override
    protected void updateLimbs(float posDelta) {
        float f = (this.getPose() == EntityPose.STANDING) ? Math.min(posDelta * 6.0F, 1.0F) : 0;
        this.limbAnimator.updateLimbs(f, 0.2F, this.isBaby() ? 3.0F : 1.0F);
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
        TrikiValsCreatures.LogStringInfo(this, String.format("Pejelagarto Tamed: %b",this.isTamed()));

        ItemStack itemStack = player.getStackInHand(hand);
        if(this.isTamed()) {
            if (itemStack.isOf(Items.GLASS_BOTTLE)) {
                player.playSound(SoundEvents.ITEM_BOTTLE_FILL, 1.0F, 1.0F);

                ItemStack poisonPotion = Items.SPLASH_POTION.getDefaultStack();
                PotionContentsComponent potionEffectComponent = PotionContentsComponent.DEFAULT.with(getStatusEffectInstance());
                poisonPotion.set(DataComponentTypes.POTION_CONTENTS, potionEffectComponent);

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
                    this.getEntityWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);

                    TrikiValsCreatures.LogStringInfo(this, "Pejelagarto Tamed!");
                } else {
                    this.getEntityWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
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

        if(this.getEntityWorld().isClient()) {
            setupAnimationStates();
        }
    }
//endregion

//region BREEDING
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.RABBIT);
    }

    public static PejelagartoEntity createNewChild(ServerWorld world, Vec3d pos, PejelagartoVariant variant) {
        PejelagartoEntity baby = ModEntities.PEJELAGARTO.create(world, SpawnReason.BREEDING);
        if(baby != null) {
            baby.setBaby(true);
            baby.refreshPositionAndAngles(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    MathHelper.wrapDegrees(world.random.nextFloat() * 360.0F),
                    0.0F);

            // 1 / 200 possibility of baby being albino
            baby.setVariant((baby.random.nextInt(200) == 0) ? PejelagartoVariant.ALBINO : variant);
            baby.setCustomEntityAttributes();
        }
        return baby;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.PEJELAGARTO.create(world, SpawnReason.BREEDING);
    }
//endregion

//region SOUNDS
//    @Override
//    protected @Nullable SoundEvent getAmbientSound() {
//        return SoundEvents.ENTITY_GHAST_AMBIENT;
//    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return PejelagartoSounds.PEJELAGARTO_HURT_SOUND;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return PejelagartoSounds.PEJELAGARTO_DEATH_SOUND;
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
    private int goalPriority = 0;

    private PejelagartoEntity startGoalsSetup() {
        goalPriority = 0;
        return this;
    }

    private PejelagartoEntity addGoal(Goal goal) {
        this.goalSelector.add(goalPriority, goal);
        goalPriority++;
        return this;
    }

    private PejelagartoEntity addGoal(Goal[] goals) {
        for (var goal: goals) {
            this.goalSelector.add(goalPriority, goal);
        }
        goalPriority++;
        return this;
    }

    @Override
    protected void initGoals() {
        startGoalsSetup()
                .addGoal(new SwimGoal(this))
                .addGoal(new NarrowDistanceDelegatingGoal(this))
                .addGoal(new Goal[]{
                        new CustomRangedAttackGoal(this,1, getRangeAggroMaxAttackRange(), getRangeAggroMinAttackRange(), 40, 20.0F),
                        new PejelagartoAttackGoal(this,1, true)
                })
                .addGoal(new PejelagartoMateGoal(this, 1.15))
                .addGoal(new SitGoal(this))
                .addGoal(new FollowOwnerNoTeleportGoal(this, 1.0, 10.0F, 2.0F))
                .addGoal(new TemptGoal(this, 1.15, Ingredient.ofItems(Items.RABBIT), false))
                .addGoal(new FollowParentGoal(this, 1.0f))
                .addGoal(new WanderAroundGoal(this, 1.0f))
                .addGoal(new LookAtEntityGoal(this, PlayerEntity.class, 1.0f))
                .addGoal(new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new TerritorialTargetGoal<>(this,  LivingEntity.class, 20, 60));
    }

    //Attack Logic
    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        if(!super.tryAttack(world, target)) {
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

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        this.spitAt(target);
    }

    private void spitAt(LivingEntity target) {
        PejelagartoSpitEntity pejelagartoSpitEntity = new PejelagartoSpitEntity(this.getEntityWorld(), this);
        double d = target.getX() - this.getX();
        double e = target.getBodyY(0.3333333333333333) - pejelagartoSpitEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f) * 0.2F;
        pejelagartoSpitEntity.setVelocity(d, e + g, f, 1.5F, 10.0F);
        if (!this.isSilent()) {
            this.getEntityWorld()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ENTITY_LLAMA_SPIT,
                            this.getSoundCategory(),
                            1.0F,
                            1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F
                    );
        }

        this.getEntityWorld().spawnEntity(pejelagartoSpitEntity);
    }

    @Override
    public int getMeleeAttackRange() {
        return 2;
    }

    @Override
    public boolean canPerformMeleeAttack(LivingEntity target) {
        return this.getVariant() != PejelagartoVariant.PLAINS && target != null && !(target instanceof PejelagartoEntity);
    }

    @Override
    public int getRangeAggroMaxAttackRange() {
        return 40;
    }

    @Override
    public int getRangeAggroMinAttackRange() {
        return 4;
    }

    @Override
    public boolean canPerformRangedAttack() {
        return this.getVariant() == PejelagartoVariant.ALBINO;
    }
//endregion

//region TAMING

//endregion

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return effect.getEffectType() != StatusEffects.POISON && super.canHaveStatusEffect(effect);
    }
}
