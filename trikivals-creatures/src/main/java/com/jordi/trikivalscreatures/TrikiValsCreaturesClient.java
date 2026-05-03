package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.datagen.properties.PejelagartoVariantProperty;
import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.client.ModModelLayers;
import com.jordi.trikivalscreatures.entity.creatures.dracopez.render.DracoPezModel;
import com.jordi.trikivalscreatures.entity.creatures.dracopez.render.DracoPezRenderer;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.render.PejelagartoModel;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.render.PejelagartoRenderer;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.spit.PejelagartoSpitEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.TropicalFishEntityRenderer;
import net.minecraft.client.render.entity.model.SmallTropicalFishEntityModel;
import net.minecraft.client.render.item.property.select.SelectProperties;
import net.minecraft.util.Identifier;

public class TrikiValsCreaturesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        SelectProperties.ID_MAPPER.put(Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto_variant"), PejelagartoVariantProperty.TYPE);

        EntityRendererFactories.register(ModEntities.DRACOPEZ, DracoPezRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DRACOPEZ, DracoPezModel::getTexturedModelData);

        EntityRendererFactories.register(ModEntities.PEJELAGARTO, PejelagartoRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PEJELAGARTO, PejelagartoModel::getTexturedModelData);

        EntityRendererFactories.register(ModEntities.PEJELAGARTO_SPIT, PejelagartoSpitEntityRenderer::new);
    }
}