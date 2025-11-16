package com.jordi.trikivalscreatures.client.model;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModels {
    public static final Model TEMPLATE_PEJELAGARTO_EGG = block("template_pejelagarto_egg", TextureKey.ALL);
    public static final Model TEMPLATE_TWO_PEJELAGARTO_EGGS = block("template_two_pejelagarto_egg", TextureKey.ALL);
    public static final Model TEMPLATE_THREE_PEJELAGARTO_EGGS = block("template_three_pejelagarto_egg", TextureKey.ALL);
    public static final Model TEMPLATE_FOUR_PEJELAGARTO_EGGS = block("template_four_pejelagarto_egg", TextureKey.ALL);

    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(TrikiValsCreatures.MOD_ID,
                "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
}
