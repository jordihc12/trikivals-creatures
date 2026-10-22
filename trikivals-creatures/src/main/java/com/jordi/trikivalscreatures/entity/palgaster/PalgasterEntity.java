package com.jordi.trikivalscreatures.entity.palgaster;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Palgaster: big feathered mount (test version).
 * - Behaves like a horse: saddle it (right click with a saddle, or sneak + right click to open its inventory)
 *   and ride it.
 * - No taming mechanic yet: it spawns already tamed.
 * - Each Palgaster has a coat colour (like horses); the beak and crest are always yellow.
 */
public class PalgasterEntity extends AbstractHorseEntity {
    private static final TrackedData<Integer> COAT =
            DataTracker.registerData(PalgasterEntity.class, TrackedDataHandlerRegistry.INTEGER);

    // ---- client-side animation state (only used by the renderer) ----
    private float clientSpeed;
    private String clientAnimation = "idle";
    private int clientAnimationStart;

    public PalgasterEntity(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getEntityWorld().isClient()) {
            double dx = this.getX() - this.lastX;
            double dz = this.getZ() - this.lastZ;
            float speed = (float) Math.sqrt(dx * dx + dz * dz);
            this.clientSpeed += (speed - this.clientSpeed) * 0.35f;

            String next;
            if (this.isTouchingWater() && this.getFluidHeight(FluidTags.WATER) > 0.6) {
                next = "swim";
            } else if (this.clientSpeed > 0.17f) {
                next = "run";
            } else if (this.clientSpeed > 0.015f) {
                next = "walk";
            } else {
                next = "idle";
            }
            if (!next.equals(this.clientAnimation)) {
                this.clientAnimation = next;
                this.clientAnimationStart = this.age;
            }
        }
    }

    public String getClientAnimation() {
        return clientAnimation;
    }

    /** Seconds since the current animation started. */
    public float getClientAnimationTime(float tickDelta) {
        return (this.age - this.clientAnimationStart + tickDelta) / 20.0f;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.HORSE_FOOD);
    }

    public static DefaultAttributeContainer.Builder createPalgasterAttributes() {
        return AbstractHorseEntity.createBaseHorseAttributes()
                .add(EntityAttributes.MAX_HEALTH, 34.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.26)
                .add(EntityAttributes.JUMP_STRENGTH, 0.8)
                .add(EntityAttributes.STEP_HEIGHT, 1.1);
    }

    // Fixed stats (no random horse stats for now)
    @Override
    protected void initAttributes(Random random) {
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        this.setCoat(PalgasterCoat.values()[world.getRandom().nextInt(PalgasterCoat.values().length)]);
        // Test version: already tamed so it can be saddled and ridden straight away
        this.setTame(true);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COAT, 0);
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("Coat", this.getCoat().ordinal());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.setCoat(PalgasterCoat.byIndex(view.getInt("Coat", 0)));
    }

    public PalgasterCoat getCoat() {
        return PalgasterCoat.byIndex(this.dataTracker.get(COAT));
    }

    public void setCoat(PalgasterCoat coat) {
        this.dataTracker.set(COAT, coat.ordinal());
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_HORSE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_HORSE_DEATH;
    }

    @Override
    protected @Nullable SoundEvent getAngrySound() {
        return SoundEvents.ENTITY_HORSE_ANGRY;
    }
}
