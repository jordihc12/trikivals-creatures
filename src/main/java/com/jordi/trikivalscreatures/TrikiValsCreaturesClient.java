package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.client.ModModelLayers;
import com.jordi.trikivalscreatures.entity.client.PejelagartoModel;
import com.jordi.trikivalscreatures.entity.client.PejelagartoRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TrikiValsCreaturesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.PEJELAGARTO, PejelagartoRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PEJELAGARTO, PejelagartoModel::getTexturedModelData);
    }
}