package com.jordi.trikivalscreatures.entity.pejelagarto.spit;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LlamaSpitEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class PejelagartoSpitEntityRenderer extends EntityRenderer<PejelagartoSpitEntity> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/llama/spit.png");
    private final LlamaSpitEntityModel<PejelagartoSpitEntity> model;

    public PejelagartoSpitEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new LlamaSpitEntityModel<>(context.getPart(EntityModelLayers.LLAMA_SPIT));
    }

    public void render(PejelagartoSpitEntity pejelagartoSpitEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0F, 0.15F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, pejelagartoSpitEntity.prevYaw, pejelagartoSpitEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, pejelagartoSpitEntity.prevPitch, pejelagartoSpitEntity.getPitch())));
        this.model.setAngles(pejelagartoSpitEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(pejelagartoSpitEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(PejelagartoSpitEntity pejelagartoSpitEntity) {
        return TEXTURE;
    }
}
