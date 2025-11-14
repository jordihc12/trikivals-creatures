package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.blocks.ModBlockEntities;
import com.jordi.trikivalscreatures.blocks.pejelagarto.PejelagartoEggBlockEntityRenderer;
import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.client.ModModelLayers;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoModel;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class TrikiValsCreaturesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.PEJELAGARTO, PejelagartoRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PEJELAGARTO, PejelagartoModel::getTexturedModelData);

        BlockEntityRendererFactories.register(ModBlockEntities.PEJELAGARTO_EGG_BE, PejelagartoEggBlockEntityRenderer::new);
    }
}