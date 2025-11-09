package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.entity.ModEntities;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoEntity;
import com.jordi.trikivalscreatures.items.ModItemGroups;
import com.jordi.trikivalscreatures.items.ModItems;
import com.jordi.trikivalscreatures.world.gen.ModEntityGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrikiValsCreatures implements ModInitializer {
	public static final String MOD_ID = "trikivals-creatures";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");
        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModEntityGeneration.addSpawns();

        FabricDefaultAttributeRegistry.register(ModEntities.PEJELAGARTO, PejelagartoEntity.createPejelagartoAttributes());
	}
}