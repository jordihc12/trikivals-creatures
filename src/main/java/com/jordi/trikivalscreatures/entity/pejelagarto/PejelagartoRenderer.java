package com.jordi.trikivalscreatures.entity.pejelagarto;

import com.google.common.collect.Maps;
import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.client.ModModelLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class PejelagartoRenderer extends MobEntityRenderer<PejelagartoEntity, PejelagartoRenderState, PejelagartoModel> {
    private static final Map<PejelagartoVariant, Identifier> TEXTURES = Util.make(Maps.newEnumMap(PejelagartoVariant.class), map -> {
        map.put(PejelagartoVariant.PLAINS, Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/pejelagarto_plains.png"));
        map.put(PejelagartoVariant.JUNGLE, Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/pejelagarto_jungle.png"));
        map.put(PejelagartoVariant.PINK, Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/pejelagarto_pink.png"));
        map.put(PejelagartoVariant.SAND, Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/pejelagarto_sand.png"));
        map.put(PejelagartoVariant.ALBINO, Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/pejelagarto_albino.png"));
    });

    public PejelagartoRenderer(EntityRendererFactory.Context context) {
        super(context, new PejelagartoModel(context.getPart(ModModelLayers.PEJELAGARTO)), 0.4f);
    }

    @Override
    public Identifier getTexture(PejelagartoRenderState state) {
        return TEXTURES.get(state.variant);
    }

    @Override
    public void render(PejelagartoRenderState state, MatrixStack matrixStack,
                       OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {
        float scalingFactor = state.variant.getVariantSize();

        if(state.baby) {
            scalingFactor *= 0.5f;
        }
        matrixStack.scale(scalingFactor, scalingFactor, scalingFactor);

        super.render(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }

    @Override
    public PejelagartoRenderState createRenderState() {
        return new PejelagartoRenderState();
    }

    @Override
    public void updateRenderState(PejelagartoEntity livingEntity, PejelagartoRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
