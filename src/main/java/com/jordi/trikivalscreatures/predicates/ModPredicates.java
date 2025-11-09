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
//        ModelPredicateProviderRegistry.register(ModItems.PEJELAGARTO_EGG_ITEM, Identifier.of(TrikiValsCreatures.MOD_ID, "variant"),
//                (stack, world, entity, seed) -> {
//                    NbtCompound nbt = stack.getNbt();
//                    if(nbt == null) return 0;
//                    NbtCompound blockStateTag = nbt.getCompound(BlockItem.BLOCK_STATE_TAG_KEY);
//                    if(blockStateTag.isEmpty()) return 0;
//
//                    if(blockStateTag.contains("variant")) {
//                        return PejelagartoVariant.byName(blockStateTag.getString("variant")).getId();
//                    }
//                    else {
//                        NbtCompound propertiesTag = blockStateTag.getCompound("Properties");
//                        if(propertiesTag.isEmpty()) return 0;
//
//                        return PejelagartoVariant.byName(propertiesTag.getString("variant")).getId();
//                    }
//                });
    }
}
