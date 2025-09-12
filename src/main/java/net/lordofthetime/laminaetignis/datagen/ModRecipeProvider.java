package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        //shaped
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CORRUPTED_BLOCK.get())
//                .pattern("CCC")
//                .pattern("CCC")
//                .pattern("CCC")
//                .define('C', ModItems.CORRUPTED_PARTICLE.get())
//                .unlockedBy(getHasName(ModItems.CORRUPTED_PARTICLE.get()),has(ModItems.CORRUPTED_PARTICLE.get()))
//                .save(pWriter);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAGICAL_BLOCK.get())
//                .pattern("CCC")
//                .pattern("CCC")
//                .pattern("CCC")
//                .define('C', ModItems.MAGICAL_PARTICLE.get())
//                .unlockedBy(getHasName(ModItems.MAGICAL_PARTICLE.get()),has(ModItems.MAGICAL_PARTICLE.get()))
//                .save(pWriter);
//
//        //shapeless
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CORRUPTED_PARTICLE.get(),9)
//                .requires(ModBlocks.CORRUPTED_BLOCK.get())
//                .unlockedBy(getHasName(ModBlocks.CORRUPTED_BLOCK.get()),has(ModBlocks.CORRUPTED_BLOCK.get()))
//                .save(pWriter);
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MAGICAL_PARTICLE.get(),9)
//                .requires(ModBlocks.MAGICAL_BLOCK.get())
//                .unlockedBy(getHasName(ModBlocks.MAGICAL_BLOCK.get()),has(ModBlocks.MAGICAL_BLOCK.get()))
//                .save(pWriter);

    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).
                    save(pFinishedRecipeConsumer, LaminaEtIgnis.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
