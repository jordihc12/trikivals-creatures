package com.jordi.trikivalscreatures.entity.palgaster.render;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.palgaster.PalgasterCoat;
import com.jordi.trikivalscreatures.entity.palgaster.PalgasterEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;

import java.util.EnumMap;
import java.util.Map;

/**
 * Draws the Palgaster mesh (exported from Palgaster.bbmodel) directly with its bones and animations.
 * The mesh is loaded once and shared, so many Palgasters only cost the vertex upload each frame.
 */
@Environment(EnvType.CLIENT)
public class PalgasterRenderer extends EntityRenderer<PalgasterEntity, PalgasterRenderState> {
    private static final Map<PalgasterCoat, Identifier> TEXTURES = new EnumMap<>(PalgasterCoat.class);
    private static final Identifier SADDLE_TEXTURE =
            Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/palgaster/palgaster_saddle.png");

    static {
        for (PalgasterCoat coat : PalgasterCoat.values()) {
            TEXTURES.put(coat, Identifier.of(TrikiValsCreatures.MOD_ID,
                    "textures/entity/palgaster/palgaster_" + coat.getId() + ".png"));
        }
    }

    public PalgasterRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.9f;
        PalgasterMesh.get();
    }

    @Override
    public PalgasterRenderState createRenderState() {
        return new PalgasterRenderState();
    }

    @Override
    public void updateRenderState(PalgasterEntity entity, PalgasterRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.bodyYaw = MathHelper.lerpAngleDegrees(tickDelta, entity.lastBodyYaw, entity.bodyYaw);
        state.coat = entity.getCoat();
        state.saddled = entity.hasSaddleEquipped();
        state.hurt = entity.hurtTime > 0 || entity.deathTime > 0;
        state.animation = entity.getClientAnimation();
        state.animationTime = entity.getClientAnimationTime(tickDelta);
        state.deathTime = entity.deathTime > 0 ? entity.deathTime + tickDelta : 0f;
    }

    @Override
    public void render(PalgasterRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue,
                       CameraRenderState cameraState) {
        PalgasterMesh mesh = PalgasterMesh.get();
        int boneCount = mesh.bones.size();
        if (boneCount == 0) {
            return;
        }

        // Animated bone transforms (rotation in degrees + position offset, Blockbench pixels)
        float[] pose = new float[boneCount * 6];
        PalgasterMesh.Anim anim = mesh.anims.get(state.animation);
        if (anim != null) {
            float t = anim.loop ? state.animationTime % anim.length : Math.min(state.animationTime, anim.length);
            float x = t / anim.length * (anim.samples - 1);
            int i0 = MathHelper.clamp((int) Math.floor(x), 0, anim.samples - 1);
            int i1 = Math.min(i0 + 1, anim.samples - 1);
            float f = x - i0;
            for (int b = 0; b < boneCount; b++) {
                float[] track = anim.tracks[b];
                if (track == null) continue;
                for (int c = 0; c < 6; c++) {
                    pose[b * 6 + c] = MathHelper.lerp(f, track[i0 * 6 + c], track[i1 * 6 + c]);
                }
            }
        }

        int light = state.light;
        int overlay = OverlayTexture.getUv(0.0f, state.hurt);
        RenderLayer bodyLayer = RenderLayers.entityCutoutNoCull(TEXTURES.get(state.coat));
        RenderLayer saddleLayer = RenderLayers.entityCutoutNoCull(SADDLE_TEXTURE);

        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - state.bodyYaw));
        if (state.deathTime > 0) {
            // Fall on its side like vanilla mobs
            float d = Math.min(1.0f, MathHelper.sqrt((state.deathTime - 1.0f) / 20.0f * 1.6f));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(d * 90.0f));
        }
        matrices.scale(1.0f / 16.0f, 1.0f / 16.0f, 1.0f / 16.0f);

        int[] chain = new int[boneCount];
        for (PalgasterMesh.Part part : mesh.parts) {
            if (part.saddle && !state.saddled) continue;

            int depth = 0;
            for (int b = part.bone; b >= 0; b = mesh.bones.get(b).parent) {
                chain[depth++] = b;
            }
            matrices.push();
            for (int k = depth - 1; k >= 0; k--) {
                int b = chain[k];
                PalgasterMesh.Bone bone = mesh.bones.get(b);
                int o = b * 6;
                matrices.translate(bone.px + pose[o + 3], bone.py + pose[o + 4], bone.pz + pose[o + 5]);
                if (pose[o] != 0 || pose[o + 1] != 0 || pose[o + 2] != 0) {
                    matrices.multiply(new Quaternionf().rotateZ(pose[o + 2] * MathHelper.RADIANS_PER_DEGREE)
                            .rotateY(pose[o + 1] * MathHelper.RADIANS_PER_DEGREE)
                            .rotateX(pose[o] * MathHelper.RADIANS_PER_DEGREE));
                }
                matrices.translate(-bone.px, -bone.py, -bone.pz);
            }

            final float[] data = part.data;
            queue.submitCustom(matrices, part.saddle ? saddleLayer : bodyLayer,
                    (entry, consumer) -> emitTriangles(entry, consumer, data, light, overlay));
            matrices.pop();
        }
        matrices.pop();

        super.render(state, matrices, queue, cameraState);
    }

    /** The entity layers use quads, so every triangle is sent as a quad with its last vertex repeated. */
    private static void emitTriangles(MatrixStack.Entry entry, VertexConsumer consumer, float[] d, int light, int overlay) {
        for (int i = 0; i < d.length; i += 24) {
            vertex(entry, consumer, d, i, light, overlay);
            vertex(entry, consumer, d, i + 8, light, overlay);
            vertex(entry, consumer, d, i + 16, light, overlay);
            vertex(entry, consumer, d, i + 16, light, overlay);
        }
    }

    private static void vertex(MatrixStack.Entry entry, VertexConsumer consumer, float[] d, int i, int light, int overlay) {
        consumer.vertex(entry, d[i], d[i + 1], d[i + 2])
                .color(-1)
                .texture(d[i + 3], d[i + 4])
                .overlay(overlay)
                .light(light)
                .normal(entry, d[i + 5], d[i + 6], d[i + 7]);
    }
}
