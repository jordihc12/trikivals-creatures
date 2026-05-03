package com.jordi.trikivalscreatures.entity.creatures.dracopez.render;

import com.jordi.trikivalscreatures.entity.creatures.dracopez.DracoPezEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class DracoPezRenderer extends MobEntityRenderer<DracoPezEntity, DracoPezRenderState, EntityModel<DracoPezRenderState>> {
    //TODO: Replace Small fish texture with dracopez texture when finished
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/fish/tropical_a.png");

    public DracoPezRenderer(EntityRendererFactory.Context context) {
        super(context, new DracoPezModel(context.getPart(EntityModelLayers.TROPICAL_FISH_SMALL)), 0.15F);
        this.addFeature(new DracoPezColorFeatureRenderer(this, context.getEntityModels()));
    }

    @Override
    public Identifier getTexture(DracoPezRenderState state) {
        return TEXTURE;
    }

    @Override
    public DracoPezRenderState createRenderState() {
        return new DracoPezRenderState();
    }

    public void updateRenderState(DracoPezEntity dracoPezEntity, DracoPezRenderState dracoPezEntityRenderState, float f) {
        super.updateRenderState(dracoPezEntity, dracoPezEntityRenderState, f);
        dracoPezEntityRenderState.patternColor = dracoPezEntity.getPatternColor().getEntityColor();
    }

    public void render(
            DracoPezRenderState dracoPezEntityRenderState,
            MatrixStack matrixStack,
            OrderedRenderCommandQueue orderedRenderCommandQueue,
            CameraRenderState cameraRenderState
    ) {
        super.render(dracoPezEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }

    protected void setupTransforms(DracoPezRenderState dracoPezEntityRenderState, MatrixStack matrixStack, float f, float g) {
        super.setupTransforms(dracoPezEntityRenderState, matrixStack, f, g);
        float h = 4.3F * MathHelper.sin(0.6F * dracoPezEntityRenderState.age);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(h));
        if (!dracoPezEntityRenderState.touchingWater) {
            matrixStack.translate(0.2F, 0.1F, 0.0F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }
    }
}
