package com.jordi.trikivalscreatures.entity.dracopez;

import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.dracopez.ai.DracoPezIntelligenceManager;
import com.jordi.trikivalscreatures.entity.dracopez.anims.DracoPezAnimatorManager;
import com.jordi.trikivalscreatures.entity.goals.IntelligenceManager;
import com.jordi.trikivalscreatures.items.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

/**
 * DracoPez: pequeño dragón de agua, pasivo.
 * - Solo vive en el agua. Fuera de ella da saltos como un pez y acaba muriendo.
 * - Huye a toda velocidad de los jugadores, salvo si llevan bayas luminosas: entonces se acerca,
 *   rodea al jugador un par de veces y se queda quieto curioseando.
 * - Se reproduce con bayas luminosas.
 * - Se le pueden teñir las aletas con tinte (como la lana de las ovejas).
 * - Se puede coger con un cubo de agua (guarda tamaño y color de aletas).
 * - Cada ejemplar tiene un tamaño distinto: de 0,5 a 1,5 bloques de largo.
 */
public class DracoPezEntity extends AnimalEntity implements Bucketable {

// ANIMATION VARIABLES
    private final DracoPezAnimatorManager animationManager = new DracoPezAnimatorManager();

// DATA TRACKER VARIABLES
    private static final TrackedData<Integer> FIN_COLOR =
            DataTracker.registerData(DracoPezEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> FROM_BUCKET =
            DataTracker.registerData(DracoPezEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

// SIZE LOGIC
    // Length of the model at scale 1 (33 model pixels = 2.0625 blocks)
    public static final float MODEL_LENGTH_BLOCKS = 33.0F / 16.0F;
    public static final float MIN_LENGTH_BLOCKS = 0.5F;
    public static final float MAX_LENGTH_BLOCKS = 1.5F;
    public static final float MIN_SCALE = MIN_LENGTH_BLOCKS / MODEL_LENGTH_BLOCKS;
    public static final float MAX_SCALE = MAX_LENGTH_BLOCKS / MODEL_LENGTH_BLOCKS;
    // Babies are always born with the minimum size and grow up to their adult size
    public static final float BABY_SCALE = MIN_SCALE;

    private float adultScale = MIN_SCALE;

// AIR LOGIC (same as vanilla fish)
    private static final int MAX_AIR = 300;

    public static final float CURIOUS_RANGE = 12.0F;

// SWIM SPEEDS ("gears"). Multipliers passed to goals / move control.
// In water the real speed grows with the square of the multiplier (same physics as the axolotl):
// CALM ~1.2 blocks/s, CURIOUS ~2 blocks/s, FLEE ~4-5 blocks/s
    public static final double SPEED_CALM = 0.75;
    public static final double SPEED_CURIOUS = 1.0;
    public static final double SPEED_FLEE_SLOW = 1.4;
    public static final double SPEED_FLEE_FAST = 1.6;
    // Movement per tick above which the swim animation plays at double speed (fleeing)
    public static final double FAST_SWIM_THRESHOLD = 0.13;

    //region INITIALIZATION
    public DracoPezEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.1F, 0.5F, false);
        this.lookControl = new YawAdjustingLookControl(this, 20);
    }

    public static DefaultAttributeContainer.Builder createDracoPezAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 8.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, 1.0F)
                .add(EntityAttributes.TEMPT_RANGE, CURIOUS_RANGE);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        Random random = world.getRandom();
        this.adultScale = randomAdultScale(random);
        this.setFinColor(randomNaturalFinColor(random));

        EntityData data = super.initialize(world, difficulty, spawnReason, entityData);
        this.applyScaleForAge();
        return data;
    }

    public static boolean canSpawn(EntityType<DracoPezEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getFluidState(pos).isIn(FluidTags.WATER)
                && world.getFluidState(pos.down()).isIn(FluidTags.WATER)
                && world.getBlockState(pos.up()).isOf(Blocks.WATER);
    }

    // Like axolotls/fish: water inside the hitbox must not block spawning
    @Override
    public boolean canSpawn(WorldView world) {
        return world.doesNotIntersectEntities(this);
    }

    // AnimalEntity prefers bright grass blocks; in water every position is equally good (also dark lush caves)
    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return 0.0F;
    }
//endregion

//region DATA TRACKER
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(FIN_COLOR, DyeColor.WHITE.getIndex());
        builder.add(FROM_BUCKET, false);
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("FinColor", this.getFinColor().getIndex());
        view.putFloat("AdultScale", this.adultScale);
        view.putBoolean("FromBucket", this.isFromBucket());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.setFinColor(DyeColor.byIndex(view.getInt("FinColor", DyeColor.WHITE.getIndex())));
        this.adultScale = clampAdultScale(view.getFloat("AdultScale", MIN_SCALE));
        this.setFromBucket(view.getBoolean("FromBucket", false));
        this.applyScaleForAge();
    }
//endregion

//region FIN COLOR
    public DyeColor getFinColor() {
        return DyeColor.byIndex(this.dataTracker.get(FIN_COLOR));
    }

    public void setFinColor(DyeColor color) {
        this.dataTracker.set(FIN_COLOR, color.getIndex());
    }

    private static DyeColor randomNaturalFinColor(Random random) {
        // Like guppies: lots of colors. White is a bit more common.
        if (random.nextInt(4) == 0) {
            return DyeColor.WHITE;
        }
        DyeColor[] colors = DyeColor.values();
        return colors[random.nextInt(colors.length)];
    }
//endregion

//region SIZE
    private static float clampAdultScale(float scale) {
        return MathHelper.clamp(scale, MIN_SCALE, MAX_SCALE);
    }

    /**
     * Small (0.5-0.8 blocks) 40%, medium (0.8-1.15 blocks) 40%, large (1.15-1.5 blocks) 20%.
     */
    private static float randomAdultScale(Random random) {
        int roll = random.nextInt(10);
        float lengthBlocks;
        if (roll < 4) {
            lengthBlocks = MathHelper.nextBetween(random, 0.5F, 0.8F);
        } else if (roll < 8) {
            lengthBlocks = MathHelper.nextBetween(random, 0.8F, 1.15F);
        } else {
            lengthBlocks = MathHelper.nextBetween(random, 1.15F, 1.5F);
        }
        return clampAdultScale(lengthBlocks / MODEL_LENGTH_BLOCKS);
    }

    public float getAdultScale() {
        return this.adultScale;
    }

    /** Length in blocks of this DracoPez once it's an adult. */
    public float getAdultLengthBlocks() {
        return this.adultScale * MODEL_LENGTH_BLOCKS;
    }

    private void setBodyScale(float scale) {
        EntityAttributeInstance scaleAttr = this.getAttributeInstance(EntityAttributes.SCALE);
        if (scaleAttr != null) {
            scaleAttr.setBaseValue(scale);
        }
    }

    private void applyScaleForAge() {
        this.setBodyScale(this.isBaby() ? BABY_SCALE : this.adultScale);
    }

    // Babies don't use the vanilla 0.5 multiplier: they use BABY_SCALE so they are never smaller than half a block
    @Override
    public float getScaleFactor() {
        return 1.0F;
    }

    @Override
    protected void onGrowUp() {
        super.onGrowUp();
        this.applyScaleForAge();
    }
//endregion

//region ANIMATIONS
    public final DracoPezAnimatorManager getAnimationManager() {
        return animationManager;
    }
//endregion

//region UPDATE
    @Override
    public void tick() {
        super.tick();

        if (this.getEntityWorld().isClient()) {
            animationManager.updateActiveAnimation(this);
            animationManager.updateAnimations(this.age);
        }
    }

    @Override
    public void baseTick() {
        int air = this.getAir();
        super.baseTick();
        if (!this.isAiDisabled() && this.getEntityWorld() instanceof ServerWorld serverWorld) {
            this.tickWaterBreathingAir(serverWorld, air);
        }
    }

    // Same mechanic as vanilla fish: out of water it loses air and then takes damage until it dies
    private void tickWaterBreathingAir(ServerWorld world, int air) {
        if (this.isAlive() && !this.isTouchingWater()) {
            this.setAir(air - 1);
            if (this.getAir() == -20) {
                this.setAir(0);
                this.damage(world, this.getDamageSources().dryOut(), 2.0F);
            }
        } else {
            this.setAir(MAX_AIR);
        }
    }

    @Override
    public void tickMovement() {
        // Flop around like a fish when on land
        if (!this.isTouchingWater() && this.isOnGround() && this.verticalCollision) {
            this.setVelocity(this.getVelocity().add(
                    (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F,
                    0.4F,
                    (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.setOnGround(false);
            this.velocityDirty = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
        }

        super.tickMovement();
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    public int getMaxAir() {
        return MAX_AIR;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        // Dye the fins (like sheep wool)
        if (itemStack.getItem() instanceof DyeItem dyeItem) {
            DyeColor color = dyeItem.getColor();
            if (this.isAlive() && color != this.getFinColor()) {
                if (!this.getEntityWorld().isClient()) {
                    this.setFinColor(color);
                    itemStack.decrementUnlessCreative(1, player);
                    this.getEntityWorld().playSoundFromEntity(null, this, SoundEvents.ITEM_DYE_USE,
                            player.getSoundCategory(), 1.0F, 1.0F);
                }
                return ActionResult.SUCCESS;
            }
        }

        // Water bucket -> DracoPez bucket. If not, breeding with glow berries (handled by AnimalEntity)
        return Bucketable.tryBucket(player, hand, this).orElseGet(() -> super.interactMob(player, hand));
    }

    /** True if this player makes the DracoPez curious instead of scared. */
    public static boolean isAttractedBy(LivingEntity entity) {
        return entity instanceof PlayerEntity player && !player.isSpectator() && player.isHolding(Items.GLOW_BERRIES);
    }
//endregion

//region BUCKET
    @Override
    public boolean isFromBucket() {
        return this.dataTracker.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.dataTracker.set(FROM_BUCKET, fromBucket);
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, nbt -> {
            nbt.putInt("FinColor", this.getFinColor().getIndex());
            nbt.putFloat("AdultScale", this.adultScale);
            nbt.putInt("Age", this.getBreedingAge());
        });
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
        // Empty buckets (creative tab, /give) keep the random color and size picked when spawning
        if (nbt.contains("FinColor")) {
            this.setFinColor(DyeColor.byIndex(nbt.getInt("FinColor", DyeColor.WHITE.getIndex())));
        }
        if (nbt.contains("AdultScale")) {
            this.adultScale = clampAdultScale(nbt.getFloat("AdultScale", this.adultScale));
        }
        if (nbt.contains("Age")) {
            this.setBreedingAge(nbt.getInt("Age", 0));
        }
        this.applyScaleForAge();
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.DRACOPEZ_BUCKET);
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }

    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBucket();
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isFromBucket() && !this.hasCustomName();
    }
//endregion

//region BREEDING
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.GLOW_BERRIES);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        DracoPezEntity baby = ModEntities.DRACOPEZ.create(world, SpawnReason.BREEDING);
        if (baby != null) {
            DyeColor babyColor = this.getFinColor();
            float babyAdultScale = this.adultScale;

            if (entity instanceof DracoPezEntity mate) {
                // Fin colors are mixed like sheep wool
                babyColor = DyeColor.mixColors(world, this.getFinColor(), mate.getFinColor());
                // Size somewhere between both parents, with a little variation
                float t = this.random.nextFloat();
                babyAdultScale = MathHelper.lerp(t, this.adultScale, mate.adultScale)
                        + (this.random.nextFloat() - 0.5F) * 0.1F;
            }

            baby.setFinColor(babyColor);
            baby.adultScale = clampAdultScale(babyAdultScale);
            baby.setBodyScale(BABY_SCALE);
            baby.setPersistent();
        }
        return baby;
    }
//endregion

//region SOUNDS
    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_TROPICAL_FISH_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_DEATH;
    }

    // Splashes while swimming near the surface, like vanilla fish
    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_FISH_SWIM;
    }

    // Sound of each jump when it is out of the water (same as tropical fish)
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_FLOP;
    }
//endregion

//region INTELLIGENCE
    @Override
    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

    @Override
    protected void initGoals() {
        IntelligenceManager.setGoals(this, DracoPezIntelligenceManager::new);
    }
//endregion
}
