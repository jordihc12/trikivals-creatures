package com.jordi.trikivalscreatures;

import com.jordi.trikivalscreatures.datagen.ModBlockTagProvider;
import com.jordi.trikivalscreatures.datagen.ModEntityLootTableProvider;
import com.jordi.trikivalscreatures.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TrikiValsCreaturesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModEntityLootTableProvider::new);
	}
}
