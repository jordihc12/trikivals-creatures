package com.jordi.trikivalscreatures.entity.client;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

//Helper class that creates custom entity model layers
public class ModModelLayers {
    public static final EntityModelLayer PEJELAGARTO =
            new EntityModelLayer(new Identifier(TrikiValsCreatures.MOD_ID, "pejelagarto"), "main");
}
