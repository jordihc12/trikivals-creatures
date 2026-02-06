package com.jordi.trikivalscreatures.entity.pejelagarto.spit;

import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PejelagartoSpitEntity extends ProjectileEntity {
    private final int POISON_ATTACK_DURATION = 300;

    public PejelagartoSpitEntity(EntityType<? extends PejelagartoSpitEntity> entityEntityType, World world) {
        super(entityEntityType, world);
    }

    public PejelagartoSpitEntity(World world, PejelagartoEntity owner) {
        this(ModEntities.PEJELAGARTO_SPIT, world);
        this.setOwner(owner);
        this.setPosition(
                owner.getX() - (owner.getWidth() + 1.0F) * 0.5 * MathHelper.sin(owner.bodyYaw * (float) (Math.PI / 180.0)),
                owner.getEyeY(),
                owner.getZ() + (owner.getWidth() + 1.0F) * 0.5 * MathHelper.cos(owner.bodyYaw * (float) (Math.PI / 180.0))
        );
    }

    @Override
    public void tick() {
        super.tick();
        Vec3d vec3d = this.getVelocity();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        this.onCollision(hitResult);
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        this.updateRotation();
        if (this.getEntityWorld().getStatesInBox(this.getBoundingBox()).noneMatch(AbstractBlock.AbstractBlockState::isAir)) {
            this.discard();
        } else if (this.isTouchingWater()) {
            this.discard();
        } else {
            this.setVelocity(vec3d.multiply(0.99F));
            if (!this.hasNoGravity()) {
                this.setVelocity(this.getVelocity().add(0.0, -0.06F, 0.0));
            }

            this.setPosition(d, e, f);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getOwner() instanceof LivingEntity livingEntity && this.getEntityWorld() instanceof ServerWorld serverWorld) {
            Entity entity = entityHitResult.getEntity();
            entity.damage(serverWorld, this.getDamageSources().mobProjectile(this, livingEntity), 1.0F);

            StatusEffectInstance customEffect = new StatusEffectInstance(
                    StatusEffects.POISON,
                    POISON_ATTACK_DURATION,
                    1
            );
            ((LivingEntity)entity).addStatusEffect(customEffect);
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getEntityWorld().isClient()) {
            this.discard();
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        double d = packet.getVelocity().getX();
        double e = packet.getVelocity().getY();
        double f = packet.getVelocity().getZ();

        for (int i = 0; i < 7; i++) {
            double g = 0.4 + 0.1 * i;
            this.getEntityWorld().addParticleClient(ParticleTypes.SPIT, this.getX(), this.getY(), this.getZ(), d * g, e, f * g);
        }

        this.setVelocity(d, e, f);
    }
}
