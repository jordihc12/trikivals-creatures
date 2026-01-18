package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.client.ModModelLayers;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoModel;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoRenderer;
import com.jordi.trikivalscreatures.entity.pejelagarto.spit.PejelagartoSpitEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactories;

public class TrikiValsCreaturesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        EntityRendererFactories.register(ModEntities.PEJELAGARTO, PejelagartoRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PEJELAGARTO, PejelagartoModel::getTexturedModelData);

        EntityRendererFactories.register(ModEntities.PEJELAGARTO_SPIT, PejelagartoSpitEntityRenderer::new);
    }
}