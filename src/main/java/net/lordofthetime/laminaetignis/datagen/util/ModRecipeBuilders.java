package net.lordofthetime.laminaetignis.datagen.util;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeBuilders implements IConditionBuilder {
    public record gridItems(Item nugget, List<Item> ores){}
    public static void gritBuilder(Consumer<FinishedRecipe> pWriter, gridItems items, Item grit, TagKey<Item> hammer){

        Item nugget = items.nugget;
        List<Item> ores = items.ores;
        if(nugget!=null){
            hammerRecipe(pWriter,grit,nugget,hammer,1);
            oreSmelting(pWriter,List.of(grit),RecipeCategory.MISC,nugget,0.1f,20*60,"nuggets");
            oreBlasting(pWriter,List.of(grit),RecipeCategory.MISC,nugget,0.1f,20*45,"nuggets");
        }
        if (ores != null) {
            ores.forEach((ore) ->{
                hammerRecipe(pWriter,grit,ore,hammer,1,
                        ResourceLocation.tryBuild(LaminaEtIgnis.MODID,getItemName(grit)+"_from_"+getItemName(ore)));
            });
        }
    }
    public static void hammerRecipe(Consumer<FinishedRecipe> pWriter,Item result, Item material, TagKey<Item> hammer, int amount){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,result,amount)
                .requires(material)
                .requires(hammer)
                .unlockedBy(getHasName(material),has(material))
                .save(pWriter);
    }
    public static void hammerRecipe(Consumer<FinishedRecipe> pWriter,Item result, Item material, TagKey<Item> hammer, int amount, ResourceLocation location){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,result,amount)
                .requires(material)
                .requires(hammer)
                .unlockedBy(getHasName(material),has(material))
                .save(pWriter,location);
    }
    public static void plateBuilder(Consumer<FinishedRecipe> pWriter, Item plate, Item ingot, TagKey<Item> hammer){
        hammerRecipe(pWriter,plate,ingot,hammer,1);
    }
    public static void chipBuilder(Consumer<FinishedRecipe> pWriter, Item chip, Item nugget, TagKey<Item> hammer){
        hammerRecipe(pWriter,chip,nugget,hammer,1);
    }
    public static void nuggetBuilder(Consumer<FinishedRecipe> pWriter, Item nugget, Item ingot){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ingot)
                .pattern("   ")
                .pattern("###")
                .pattern("###")
                .define('#', nugget)
                .unlockedBy(getHasName(nugget), has(nugget))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,nugget,6)
                .requires(ingot)
                .unlockedBy(getHasName(ingot),has(ingot))
                .save(pWriter);
    }
    public static void nuggetBuilder(Consumer<FinishedRecipe> pWriter, Item nugget, Item ingot, ResourceLocation ingotLocation, ResourceLocation nuggetLocation){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ingot)
                .pattern("   ")
                .pattern("###")
                .pattern("###")
                .define('#', nugget)
                .unlockedBy(getHasName(nugget), has(nugget))
                .save(pWriter,ingotLocation);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,nugget,6)
                .requires(ingot)
                .unlockedBy(getHasName(ingot),has(ingot))
                .save(pWriter,nuggetLocation);
    }
    public static  void hammerBuilder(Consumer<FinishedRecipe> pWriter, Item material, Item result){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("#|#")
                .pattern(" | ")
                .unlockedBy(getHasName(material),has(material))
                .save(pWriter);;
    }
    public static  void mehtyBuilder(Consumer<FinishedRecipe> pWriter, Item material, Item result){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .define('#', material)
                .pattern("  #")
                .pattern(" # ")
                .pattern("#  ")
                .unlockedBy(getHasName(material),has(material))
                .save(pWriter);;
    }
    public static void stairBuilder(Consumer<FinishedRecipe> pWriter, Block material, Block result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .define('#', material)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy(getHasName(material),has(material))
                .save(pWriter);;
    }
    public static void slabBuilder(Consumer<FinishedRecipe> pWriter, Block material, Block result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 6)
                .define('#', material)
                .pattern("###")
                .unlockedBy(getHasName(material),has(material))
                .save(pWriter);;
    }
    public static void wallBuilder(Consumer<FinishedRecipe> pWriter,Block material, Block result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 6)
                .define('#', material)
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(material),has(material))
                .save(pWriter);;
    }

    public static void brickBuilder(Consumer<FinishedRecipe> pWriter, Block baseItem, Block blockType){

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, blockType.asItem(), 4)
                .pattern("CC ")
                .pattern("CC ")
                .define('C', baseItem)
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(pWriter);
    }
    public static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    public static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    public static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).
                    save(pFinishedRecipeConsumer, LaminaEtIgnis.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }




    /*
        Protected functions from RecipeProvider.java
     */
    protected static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... pPredicates) {
        return new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, pPredicates);
    }
    protected static InventoryChangeTrigger.TriggerInstance has(MinMaxBounds.Ints pCount, ItemLike pItem) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItem).withCount(pCount).build());
    }
    protected static InventoryChangeTrigger.TriggerInstance has(ItemLike pItemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance has(TagKey<Item> pTag) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pTag).build());
    }
    protected static String getHasName(ItemLike pItemLike) {
        return "has_" + getItemName(pItemLike);
    }
    protected static String getItemName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getPath();
    }
}
