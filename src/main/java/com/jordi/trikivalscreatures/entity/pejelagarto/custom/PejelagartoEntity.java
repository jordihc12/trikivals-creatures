package com.jordi.trikivalscreatures.entity.pejelagarto.custom;

import com.jordi.trikivalscreatures.entity.ModEntities;
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
import net.minecraft.entity.passive.DonkeyEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15));
        this.goalSelector.add(2, new TemptGoal(this, 1.15,
                Ingredient.ofItems(Items.IRON_INGOT), false));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.0f));
        this.goalSelector.add(4, new WanderAroundGoal(this, 1.0f));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 1.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        PejelagartoVariant variant = Util.getRandom(PejelagartoVariant.values(), this.random);
        setVariant(variant);

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
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
        return stack.isOf(Items.IRON_INGOT);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        //Create baby entity
        PejelagartoEntity baby = ModEntities.PEJELAGARTO.create(world);

        //If other entity is a pejelagarto -- must always be but need to check
        if (entity instanceof PejelagartoEntity pejelagarto) {
            //50-50 variant of parents
            int rand = this.random.nextInt(1);
            if(rand == 0) {
                baby.setVariant(this.getVariant());
            }
            else {
                baby.setVariant(pejelagarto.getVariant());
            }
        }
        else {
            PejelagartoVariant variant = Util.getRandom(PejelagartoVariant.values(), this.random);
            baby.setVariant(variant);
        }
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
