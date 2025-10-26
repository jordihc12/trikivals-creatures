package com.jordi.trikivalscreatures.entity.pejelagarto.custom;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.util.ModTags;
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
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PejelagartoEntity extends AnimalEntity implements Saddleable {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationCooldown = 0;

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> BOOST_TIME = DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> SADDLED = DataTracker.registerData(PejelagartoEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final SaddledComponent saddledComponent = new SaddledComponent(this.dataTracker, BOOST_TIME, SADDLED);

    public PejelagartoEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = (this.getPose() == EntityPose.STANDING) ? Math.min(posDelta * 6.0F, 1.0F) : 0;
        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        if (this.getFirstPassenger() instanceof MobEntity mobEntity) {
            return mobEntity;
        } else {
            return this.isSaddled() && this.getFirstPassenger() instanceof PlayerEntity playerEntity ? playerEntity : null;
        }
    }

    public static boolean canSpawn(EntityType<PejelagartoEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(ModTags.Blocks.PEJELAGARTO_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }

    @Override
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput) {
        super.tickControlled(controllingPlayer, movementInput);
        Vec2f vec2f = this.getControlledRotation(controllingPlayer);
        this.setRotation(vec2f.y, vec2f.x);
        this.prevYaw = this.bodyYaw = this.headYaw = this.getYaw();
    }

    protected Vec2f getControlledRotation(LivingEntity controllingPassenger) {
        return new Vec2f(controllingPassenger.getPitch() * 0.5F, controllingPassenger.getYaw());
    }

    @Override
    protected Vec3d getControlledMovementInput(PlayerEntity controllingPlayer, Vec3d movementInput) {
        float f = controllingPlayer.sidewaysSpeed * 0.5F;
        float g = controllingPlayer.forwardSpeed;
        if (g <= 0.0F) {
            g *= 0.25F;
        }

        return new Vec3d(f, 0.0, g);
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        if (this.hasPassenger(passenger)) {
            double d = this.getY() + passenger.getHeightOffset();
            positionUpdater.accept(passenger, this.getX(), d, this.getZ());
        }
    }

    @Override
    protected float getSaddledSpeed(PlayerEntity controllingPlayer) {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        boolean bl = this.isBreedingItem(player.getStackInHand(hand));
        if (!bl && this.isSaddled() && !this.hasPassengers() && !player.shouldCancelInteraction()) {
            if (!this.getWorld().isClient) {
                player.startRiding(this);
            }

            return ActionResult.success(this.getWorld().isClient);
        }
        else {
            return super.interactMob(player, hand);
        }
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

    public static DefaultAttributeContainer.Builder createPejelagartoAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2f);
    }

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

    //Variant
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DATA_ID_TYPE_VARIANT, 0);
        this.dataTracker.startTracking(BOOST_TIME, 0);
        this.dataTracker.startTracking(SADDLED, false);
    }

    public PejelagartoVariant getVariant() {
        return PejelagartoVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(PejelagartoVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
        this.saddledComponent.writeNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
        this.saddledComponent.readNbt(nbt);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        RegistryKey<Biome> biome = world.getBiome(this.getBlockPos()).getKey().orElseGet(null);
        if(biome != null) {
            TrikiValsCreatures.LOGGER.info("Spawning Pejelagarto at biome {}", biome);

            List<PejelagartoVariant> possibleVariants = new java.util.ArrayList<>(Collections.emptyList());
            List.of(PejelagartoVariant.values()).forEach(variant -> {
                RegistryKey<Biome>[] biomeKeys = variant.getSpawnBiomes();
                if(biomeKeys.length == 0) {
                    possibleVariants.add(variant);
                    return;
                }

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

    @Override
    public boolean canBeSaddled() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void saddle(@Nullable SoundCategory sound) {
        this.saddledComponent.setSaddled(true);
        if (sound != null) {
            this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_STRIDER_SADDLE, sound, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return this.saddledComponent.isSaddled();
    }
}
