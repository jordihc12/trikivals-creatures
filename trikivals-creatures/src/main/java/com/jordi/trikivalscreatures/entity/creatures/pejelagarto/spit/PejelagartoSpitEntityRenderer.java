package com.jordi.trikivalscreatures.entity.creatures.pejelagarto.spit;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LlamaSpitEntityModel;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class PejelagartoSpitEntityRenderer extends EntityRenderer<PejelagartoSpitEntity, PejelagartoSpitEntityRenderState> {
    private static final Identifier TEXTURE = Identifier.of("textures/entity/llama/spit.png");
    private final LlamaSpitEntityModel model;

    public PejelagartoSpitEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new LlamaSpitEntityModel(context.getPart(EntityModelLayers.LLAMA_SPIT));
    }

    public void render(
            PejelagartoSpitEntityRenderState pejelagartoSpitEntityRenderState,
            MatrixStack matrixStack,
            OrderedRenderCommandQueue orderedRenderCommandQueue,
            CameraRenderState cameraRenderState
    ) {
        matrixStack.push();
        matrixStack.translate(0.0F, 0.15F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(pejelagartoSpitEntityRenderState.yaw - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(pejelagartoSpitEntityRenderState.pitch));

        orderedRenderCommandQueue.submitModel(
                this.model,
                pejelagartoSpitEntityRenderState,
                matrixStack,
                this.model.getLayer(TEXTURE),
                pejelagartoSpitEntityRenderState.light,
                OverlayTexture.DEFAULT_UV,
                pejelagartoSpitEntityRenderState.outlineColor,
                null
        );
        matrixStack.pop();
        super.render(pejelagartoSpitEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }

    public PejelagartoSpitEntityRenderState createRenderState() {
        return new PejelagartoSpitEntityRenderState();
    }

    public void updateRenderState(PejelagartoSpitEntity pejelagartoSpitEntity, PejelagartoSpitEntityRenderState pejelagartoRenderState, float f) {
        super.updateRenderState(pejelagartoSpitEntity, pejelagartoRenderState, f);
        pejelagartoRenderState.pitch = pejelagartoSpitEntity.getLerpedPitch(f);
        pejelagartoRenderState.yaw = pejelagartoSpitEntity.getLerpedYaw(f);
    }
}
