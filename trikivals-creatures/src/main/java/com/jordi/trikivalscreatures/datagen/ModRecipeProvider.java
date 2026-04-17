package com.jordi.trikivalscreatures.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            private void customSmithingTemplateCopyingRecipe(ItemConvertible template, ItemConvertible resource) {
                this.createShaped(RecipeCategory.MISC, template, 2)
                        .input('#', resource)
                        .input('S', template)
                        .pattern("###")
                        .pattern("#S#")
                        .pattern("###")
                        .criterion(hasItem(template), this.conditionsFromItem(template))
                        .offerTo(this.exporter);
            }

            public void customSmithingTemplateCopyingRecipe(ItemConvertible template, Ingredient resource) {
                this.createShaped(RecipeCategory.MISC, template, 2)
                        .input('#', resource)
                        .input('S', template)
                        .pattern("###")
                        .pattern("#S#")
                        .pattern("###")
                        .criterion(hasItem(template), this.conditionsFromItem(template))
                        .offerTo(this.exporter);
            }

            @Override
            public void generate() {
                this.customSmithingTemplateCopyingRecipe(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERRACK);
                this.customSmithingTemplateCopyingRecipe(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLESTONE);
                this.customSmithingTemplateCopyingRecipe(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.SANDSTONE);
                this.customSmithingTemplateCopyingRecipe(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLESTONE);
                this.customSmithingTemplateCopyingRecipe(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, Items.MOSSY_COBBLESTONE);
                this.customSmithingTemplateCopyingRecipe(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLED_DEEPSLATE);
                this.customSmithingTemplateCopyingRecipe(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.END_STONE);
                this.customSmithingTemplateCopyingRecipe(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLESTONE);
                this.customSmithingTemplateCopyingRecipe(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.PRISMARINE);
                this.customSmithingTemplateCopyingRecipe(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, Items.BLACKSTONE);
                this.customSmithingTemplateCopyingRecipe(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE, Items.NETHERRACK);
                this.customSmithingTemplateCopyingRecipe(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.PURPUR_BLOCK);
                this.customSmithingTemplateCopyingRecipe(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLED_DEEPSLATE);
                this.customSmithingTemplateCopyingRecipe(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, Items.TERRACOTTA);
                this.customSmithingTemplateCopyingRecipe(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, Items.TERRACOTTA);
                this.customSmithingTemplateCopyingRecipe(Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE, Items.TERRACOTTA);
                this.customSmithingTemplateCopyingRecipe(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE, Items.TERRACOTTA);
                this.customSmithingTemplateCopyingRecipe(Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, Items.BREEZE_ROD);
                this.customSmithingTemplateCopyingRecipe(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, Ingredient.ofItems(Items.COPPER_BLOCK, Items.WAXED_COPPER_BLOCK));
            }
        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}
