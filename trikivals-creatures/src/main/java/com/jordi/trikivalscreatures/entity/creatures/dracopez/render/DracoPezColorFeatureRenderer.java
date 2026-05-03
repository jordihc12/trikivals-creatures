package com.jordi.trikivalscreatures.entity.creatures.dracopez.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DracoPezColorFeatureRenderer extends FeatureRenderer<DracoPezRenderState, EntityModel<DracoPezRenderState>> {
    private final DracoPezModel model;
    //TODO: Replace Small fish pattern texture with dracopez pattern texture when ready
    private static final Identifier PATTERN_TEXTURE = Identifier.ofVanilla("textures/entity/fish/tropical_a_pattern_1.png");

    public DracoPezColorFeatureRenderer(FeatureRendererContext<DracoPezRenderState, EntityModel<DracoPezRenderState>> context, LoadedEntityModels loader) {
        super(context);
        model = new DracoPezModel(loader.getModelPart(EntityModelLayers.TROPICAL_FISH_SMALL_PATTERN));
    }

    public void render(MatrixStack matrixStack,
                       OrderedRenderCommandQueue orderedRenderCommandQueue,
                       int i,
                       DracoPezRenderState dracoPezEntityRenderState,
                       float f,
                       float g) {
        render(model, PATTERN_TEXTURE, matrixStack, orderedRenderCommandQueue, i, dracoPezEntityRenderState, dracoPezEntityRenderState.patternColor, 1);
    }
}
