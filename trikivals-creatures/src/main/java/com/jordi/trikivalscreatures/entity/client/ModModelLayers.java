package com.jordi.trikivalscreatures.entity.client;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

//Helper class that creates custom entity model layers
@Environment(EnvType.CLIENT)
public class ModModelLayers {
    public static final EntityModelLayer PEJELAGARTO =
            new EntityModelLayer(Identifier.of(TrikiValsCreatures.MOD_ID, "pejelagarto"), "main");

    public static final EntityModelLayer DRACOPEZ =
            new EntityModelLayer(Identifier.of(TrikiValsCreatures.MOD_ID, "dracopez"), "main");
}
