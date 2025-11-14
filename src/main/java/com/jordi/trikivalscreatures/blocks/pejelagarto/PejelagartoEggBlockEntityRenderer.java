package com.jordi.trikivalscreatures.blocks.pejelagarto;

import com.google.common.collect.Maps;
import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class PejelagartoEggBlockEntityRenderer implements BlockEntityRenderer<PejelagartoEggBlockEntity> {
    private static final Map<PejelagartoVariant, Identifier> TEXTURES = Util.make(Maps.newEnumMap(PejelagartoVariant.class), map -> {
        map.put(PejelagartoVariant.PLAINS, new Identifier(TrikiValsCreatures.MOD_ID, "textures/block/pejelagarto_egg_plains.png"));
        map.put(PejelagartoVariant.JUNGLE, new Identifier(TrikiValsCreatures.MOD_ID, "textures/block/pejelagarto_egg_jungle.png"));
        map.put(PejelagartoVariant.PINK, new Identifier(TrikiValsCreatures.MOD_ID, "textures/block/pejelagarto_egg_pink.png"));
        map.put(PejelagartoVariant.SAND, new Identifier(TrikiValsCreatures.MOD_ID, "textures/block/pejelagarto_egg_sand.png"));
        map.put(PejelagartoVariant.ALBINO, new Identifier(TrikiValsCreatures.MOD_ID, "textures/block/pejelagarto_egg_albino.png"));
    });

    private final PejelagartoEggBlockModel eggModel;

    public PejelagartoEggBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart root = PejelagartoEggBlockModel.createFourEggGeometry();
        this.eggModel = new PejelagartoEggBlockModel(root);
    }

    @Override
    public void render(PejelagartoEggBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        for (int i = 0; i < 4; i++) {
            int eggIndex = i + 1;
            ModelPart eggPart = this.eggModel.getEggPart(eggIndex);
            if(i < entity.getEggs().size()) {
                matrices.push();

                eggPart.visible = true;

                Identifier texture = TEXTURES.get(entity.getEggs().get(i));

                VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));

                eggPart.render(matrices, consumer, light, overlay);
                matrices.pop();
            }
            else {
                eggPart.visible = false;
            }
        }
    }
}
