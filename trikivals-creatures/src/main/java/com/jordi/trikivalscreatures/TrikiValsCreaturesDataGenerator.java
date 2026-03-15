package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.datagen.ModBlockLootTableProvider;
import com.jordi.trikivalscreatures.datagen.tag_providers.ModBlockTagProvider;
import com.jordi.trikivalscreatures.datagen.ModEntityLootTableProvider;
import com.jordi.trikivalscreatures.datagen.ModModelProvider;
import com.jordi.trikivalscreatures.datagen.tag_providers.ModEntityTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TrikiValsCreaturesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModEntityTagProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModEntityLootTableProvider::new);
	}
}
