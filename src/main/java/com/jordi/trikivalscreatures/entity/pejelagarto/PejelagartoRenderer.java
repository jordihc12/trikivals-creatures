package com.jordi.trikivalscreatures.entity.pejelagarto;

import com.google.common.collect.Maps;
import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.client.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class PejelagartoRenderer extends MobEntityRenderer<PejelagartoEntity, PejelagartoModel<PejelagartoEntity>> {
    private static final Map<PejelagartoVariant, Identifier> TEXTURES = Util.make(Maps.newEnumMap(PejelagartoVariant.class), map -> {
        map.put(PejelagartoVariant.GREEN, new Identifier(TrikiValsCreatures.MOD_ID, "textures/entity/pejelagarto_green.png"));
        map.put(PejelagartoVariant.PINK, new Identifier(TrikiValsCreatures.MOD_ID, "textures/entity/pejelagarto_pink.png"));
    });

    public PejelagartoRenderer(EntityRendererFactory.Context context) {
        super(context, new PejelagartoModel<>(context.getPart(ModModelLayers.PEJELAGARTO)), 0.4f);
    }

    @Override
    public Identifier getTexture(PejelagartoEntity entity) {
        return TEXTURES.get(entity.getVariant());
    }

    @Override
    public void render(PejelagartoEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }
        else {
            matrixStack.scale(1.0f,1.0f,1.0f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
