package com.jordi.trikivalscreatures.predicates;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import com.jordi.trikivalscreatures.items.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ModPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.PEJELAGARTO_EGG_ITEM, Identifier.of(TrikiValsCreatures.MOD_ID, "variant"),
                (stack, world, entity, seed) -> {
                    NbtCompound nbt = stack.getNbt();
                    assert nbt != null;
                    if(nbt.isEmpty()) return 0.0F;
                    else if(nbt.contains("variant")) {
                        return ((float) PejelagartoVariant.byName(nbt.getString("variant")).getId() /
                                (PejelagartoVariant.values().length - 1));
                    }

                    NbtCompound blockStateTag = nbt.getCompound(BlockItem.BLOCK_STATE_TAG_KEY);
                    if(blockStateTag.isEmpty()) return 0.0F;
                    else if(blockStateTag.contains("variant")) {
                        return ((float) PejelagartoVariant.byName(blockStateTag.getString("variant")).getId() /
                                (PejelagartoVariant.values().length - 1));
                    }

                    NbtCompound propertiesTag = blockStateTag.getCompound("Properties");
                    if(propertiesTag.isEmpty()) return 0.0F;
                    else if (propertiesTag.contains("variant")) {
                        return ((float) PejelagartoVariant.byName(propertiesTag.getString("variant")).getId() /
                                (PejelagartoVariant.values().length - 1));
                    }

                    return 0.0F;
                });
    }
}
