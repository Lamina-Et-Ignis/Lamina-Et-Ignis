package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CORD_SPOOL.get())
                .pattern("CCC")
                .pattern("CSC")
                .pattern("CCC")
                .define('C', Items.STRING)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.STICK),has(Items.STICK))
                .unlockedBy(getHasName(Items.STRING),has(Items.STRING))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLESTONE) // 👈 changed result
                .pattern("PP")
                .pattern("PP")
                .define('P', ModItems.PEBBLE.get())
                .unlockedBy(getHasName(ModItems.PEBBLE.get()), has(ModItems.PEBBLE.get()))
                .save(pWriter);

        //wooden tools
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WOODEN_HAMMER.get())
                .pattern("CCC")
                .pattern("CSC")
                .pattern(" S ")
                .define('C', Items.OAK_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.OAK_PLANKS),has(Items.OAK_PLANKS))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.WOODEN_CLUB.get()) // 👈 changed result
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .define('#', Items.OAK_PLANKS)
                .define('/', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(pWriter, ResourceLocation.tryBuild("minecraft", "wooden_sword"));

        //copper tools

        //tin tools

        //iron tools

        //shapeless
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PEBBLE.get(),2)
                .requires(Blocks.COBBLESTONE)
                .unlockedBy(getHasName(Blocks.COBBLESTONE),has(Blocks.COBBLESTONE))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BONE_SHARD.get(),2)
                .requires(Items.BONE)
                .requires(Items.FLINT)
                .unlockedBy(getHasName(Items.BONE),has(Items.BONE))
                .unlockedBy(getHasName(Items.FLINT),has(Items.FLINT))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS,ModItems.BONE_NEEDLE.get(),1)
                .requires(ModItems.BONE_SHARD.get())
                .requires(Items.FLINT)
                .unlockedBy(getHasName(ModItems.BONE_SHARD.get()),has(ModItems.BONE_SHARD.get()))
                .unlockedBy(getHasName(Items.FLINT),has(Items.FLINT))
                .save(pWriter);

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
