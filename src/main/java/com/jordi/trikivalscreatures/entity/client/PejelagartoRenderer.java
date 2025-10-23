package com.jordi.trikivalscreatures.entity.client;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.custom.PejelagartoEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PejelagartoRenderer extends MobEntityRenderer<PejelagartoEntity, PejelagartoModel<PejelagartoEntity>> {
    private static final Identifier TEXTURE = new Identifier(TrikiValsCreatures.MOD_ID, "textures/entity/pejelagarto.png");

    public PejelagartoRenderer(EntityRendererFactory.Context context) {
        super(context, new PejelagartoModel<>(context.getPart(ModModelLayers.PEJELAGARTO)), 0.4f);
    }

    @Override
    public Identifier getTexture(PejelagartoEntity entity) {
        return TEXTURE;
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
