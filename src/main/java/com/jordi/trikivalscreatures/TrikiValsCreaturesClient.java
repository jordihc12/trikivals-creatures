package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.client.ModModelLayers;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoModel;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoRenderer;
import com.jordi.trikivalscreatures.entity.pejelagarto.spit.PejelagartoSpitEntityRendderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TrikiValsCreaturesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.PEJELAGARTO, PejelagartoRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PEJELAGARTO, PejelagartoModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.PEJELAGARTO_SPIT, PejelagartoSpitEntityRendderer::new);
    }
}