package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.datagen.properties.PejelagartoVariantProperty;
import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.client.ModModelLayers;
import com.jordi.trikivalscreatures.entity.pejelagarto.render.PejelagartoModel;
import com.jordi.trikivalscreatures.entity.pejelagarto.render.PejelagartoRenderer;
import com.jordi.trikivalscreatures.entity.pejelagarto.spit.PejelagartoSpitEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.item.property.select.SelectProperties;
import net.minecraft.util.Identifier;

public class TrikiValsCreaturesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        SelectProperties.ID_MAPPER.put(Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto_variant"), PejelagartoVariantProperty.TYPE);

        EntityRendererFactories.register(ModEntities.PEJELAGARTO, PejelagartoRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PEJELAGARTO, PejelagartoModel::getTexturedModelData);

        EntityRendererFactories.register(ModEntities.PEJELAGARTO_SPIT, PejelagartoSpitEntityRenderer::new);
    }
}