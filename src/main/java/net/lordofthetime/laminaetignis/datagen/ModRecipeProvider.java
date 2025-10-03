package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.datagen.util.ModRecipeBuilders;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.tags.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.*;
import java.util.function.Consumer;

import static net.lordofthetime.laminaetignis.datagen.util.ModRecipeBuilders.*;


public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder{
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    private static final List<ItemLike> TIN_SMELTABLES = List.of(ModItems.RAW_TIN.get(),ModBlocks.TIN_ORE.get());
    private static final List<ItemLike> COPPER_SMELTABLES = List.of(Items.RAW_COPPER,Blocks.COPPER_ORE,Blocks.DEEPSLATE_COPPER_ORE);
    private static final List<ItemLike> IRON_SMELTABLES = List.of(Items.RAW_IRON,Blocks.IRON_ORE,Blocks.DEEPSLATE_IRON_ORE);

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //shaped

        //bricks
        Map.ofEntries(
                Map.entry(Blocks.ANDESITE, ModBlocks.ANDESITE_BRICKS.get()),
                Map.entry(Blocks.DIORITE, ModBlocks.DIORITE_BRICKS.get()),
                Map.entry(Blocks.GRANITE, ModBlocks.GRANITE_BRICKS.get()),
                Map.entry(Blocks.CALCITE, ModBlocks.CALCITE_BRICKS.get()),
                Map.entry(Blocks.TUFF, ModBlocks.TUFF_BRICKS.get()),
                Map.entry(ModBlocks.ANDESITE_COBBLE.get(), ModBlocks.CRUDE_ANDESITE_BRICKS.get()),
                Map.entry(ModBlocks.DIORITE_COBBLE.get(), ModBlocks.CRUDE_DIORITE_BRICKS.get()),
                Map.entry(ModBlocks.GRANITE_COBBLE.get(), ModBlocks.CRUDE_GRANITE_BRICKS.get()),
                Map.entry(ModBlocks.CALCITE_COBBLE.get(), ModBlocks.CRUDE_CALCITE_BRICKS.get()),
                Map.entry(ModBlocks.TUFF_COBBLE.get(), ModBlocks.CRUDE_TUFF_BRICKS.get()),
                Map.entry(Blocks.COBBLED_DEEPSLATE, ModBlocks.CRUDE_DEEPSLATE_BRICKS.get())
        ).forEach((base,brick) ->{brickBuilder(pWriter, base, brick);});

        stairBuilder(pWriter,ModBlocks.CRUDE_DIORITE_BRICKS.get(),ModBlocks.CRUDE_DIORITE_BRICK_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.CRUDE_DIORITE_BRICKS.get(),ModBlocks.CRUDE_DIORITE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CRUDE_DIORITE_BRICKS.get(),ModBlocks.CRUDE_DIORITE_BRICK_WALL.get());

        stairBuilder(pWriter,ModBlocks.CRUDE_ANDESITE_BRICKS.get(),ModBlocks.CRUDE_ANDESITE_BRICK_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.CRUDE_ANDESITE_BRICKS.get(),ModBlocks.CRUDE_ANDESITE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CRUDE_ANDESITE_BRICKS.get(),ModBlocks.CRUDE_ANDESITE_BRICK_WALL.get());

        ModRecipeBuilders.stairBuilder(pWriter,ModBlocks.CRUDE_GRANITE_BRICKS.get(),ModBlocks.CRUDE_GRANITE_BRICK_STAIRS.get());
        ModRecipeBuilders.slabBuilder(pWriter,ModBlocks.CRUDE_GRANITE_BRICKS.get(),ModBlocks.CRUDE_GRANITE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CRUDE_GRANITE_BRICKS.get(),ModBlocks.CRUDE_GRANITE_BRICK_WALL.get());

        ModRecipeBuilders.stairBuilder(pWriter,ModBlocks.CRUDE_CALCITE_BRICKS.get(),ModBlocks.CRUDE_CALCITE_BRICK_STAIRS.get());
        ModRecipeBuilders.slabBuilder(pWriter,ModBlocks.CRUDE_CALCITE_BRICKS.get(),ModBlocks.CRUDE_CALCITE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CRUDE_CALCITE_BRICKS.get(),ModBlocks.CRUDE_CALCITE_BRICK_WALL.get());

        ModRecipeBuilders.stairBuilder(pWriter,ModBlocks.CRUDE_TUFF_BRICKS.get(),ModBlocks.CRUDE_TUFF_BRICK_STAIRS.get());
        ModRecipeBuilders.slabBuilder(pWriter,ModBlocks.CRUDE_TUFF_BRICKS.get(),ModBlocks.CRUDE_TUFF_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CRUDE_TUFF_BRICKS.get(),ModBlocks.CRUDE_TUFF_BRICK_WALL.get());

        ModRecipeBuilders.stairBuilder(pWriter,ModBlocks.CRUDE_DEEPSLATE_BRICKS.get(),ModBlocks.CRUDE_DEEPSLATE_BRICK_STAIRS.get());
        ModRecipeBuilders.slabBuilder(pWriter,ModBlocks.CRUDE_DEEPSLATE_BRICKS.get(),ModBlocks.CRUDE_DEEPSLATE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CRUDE_DEEPSLATE_BRICKS.get(),ModBlocks.CRUDE_DEEPSLATE_BRICK_WALL.get());

        ModRecipeBuilders.stairBuilder(pWriter,ModBlocks.CRUDE_STONE_BRICKS.get(),ModBlocks.CRUDE_STONE_BRICK_STAIRS.get());
        ModRecipeBuilders.slabBuilder(pWriter,ModBlocks.CRUDE_STONE_BRICKS.get(),ModBlocks.CRUDE_STONE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CRUDE_STONE_BRICKS.get(),ModBlocks.CRUDE_STONE_BRICK_WALL.get());

        stairBuilder(pWriter,ModBlocks.DIORITE_BRICKS.get(),ModBlocks.DIORITE_BRICK_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.DIORITE_BRICKS.get(),ModBlocks.DIORITE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.DIORITE_BRICKS.get(),ModBlocks.DIORITE_BRICK_WALL.get());

        stairBuilder(pWriter,ModBlocks.ANDESITE_BRICKS.get(),ModBlocks.ANDESITE_BRICK_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.ANDESITE_BRICKS.get(),ModBlocks.ANDESITE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.ANDESITE_BRICKS.get(),ModBlocks.ANDESITE_BRICK_WALL.get());

        ModRecipeBuilders.stairBuilder(pWriter,ModBlocks.GRANITE_BRICKS.get(),ModBlocks.GRANITE_BRICK_STAIRS.get());
        ModRecipeBuilders.slabBuilder(pWriter,ModBlocks.GRANITE_BRICKS.get(),ModBlocks.GRANITE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.GRANITE_BRICKS.get(),ModBlocks.GRANITE_BRICK_WALL.get());

        ModRecipeBuilders.stairBuilder(pWriter,ModBlocks.CALCITE_BRICKS.get(),ModBlocks.CALCITE_BRICK_STAIRS.get());
        ModRecipeBuilders.slabBuilder(pWriter,ModBlocks.CALCITE_BRICKS.get(),ModBlocks.CALCITE_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CALCITE_BRICKS.get(),ModBlocks.CALCITE_BRICK_WALL.get());

        ModRecipeBuilders.stairBuilder(pWriter,ModBlocks.TUFF_BRICKS.get(),ModBlocks.TUFF_BRICK_STAIRS.get());
        ModRecipeBuilders.slabBuilder(pWriter,ModBlocks.TUFF_BRICKS.get(),ModBlocks.TUFF_BRICK_SLAB.get());
        wallBuilder(pWriter,ModBlocks.TUFF_BRICKS.get(),ModBlocks.TUFF_BRICK_WALL.get());

        //cobble
        stairBuilder(pWriter,ModBlocks.DIORITE_COBBLE.get(),ModBlocks.DIORITE_COBBLE_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.DIORITE_COBBLE.get(),ModBlocks.DIORITE_COBBLE_SLAB.get());
        wallBuilder(pWriter,ModBlocks.DIORITE_COBBLE.get(),ModBlocks.DIORITE_COBBLE_WALL.get());

        stairBuilder(pWriter,ModBlocks.ANDESITE_COBBLE.get(),ModBlocks.ANDESITE_COBBLE_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.ANDESITE_COBBLE.get(),ModBlocks.ANDESITE_COBBLE_SLAB.get());
        wallBuilder(pWriter,ModBlocks.ANDESITE_COBBLE.get(),ModBlocks.ANDESITE_COBBLE_WALL.get());

        stairBuilder(pWriter,ModBlocks.GRANITE_COBBLE.get(),ModBlocks.GRANITE_COBBLE_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.GRANITE_COBBLE.get(),ModBlocks.GRANITE_COBBLE_SLAB.get());
        wallBuilder(pWriter,ModBlocks.GRANITE_COBBLE.get(),ModBlocks.GRANITE_COBBLE_WALL.get());

        stairBuilder(pWriter,ModBlocks.CALCITE_COBBLE.get(),ModBlocks.CALCITE_COBBLE_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.CALCITE_COBBLE.get(),ModBlocks.CALCITE_COBBLE_SLAB.get());
        wallBuilder(pWriter,ModBlocks.CALCITE_COBBLE.get(),ModBlocks.CALCITE_COBBLE_WALL.get());

        stairBuilder(pWriter,ModBlocks.TUFF_COBBLE.get(),ModBlocks.TUFF_COBBLE_STAIRS.get());
        slabBuilder(pWriter,ModBlocks.TUFF_COBBLE.get(),ModBlocks.TUFF_COBBLE_SLAB.get());
        wallBuilder(pWriter,ModBlocks.TUFF_COBBLE.get(),ModBlocks.TUFF_COBBLE_WALL.get());



        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CORD_SPOOL.get())
                .pattern("CCC")
                .pattern("CSC")
                .pattern("CCC")
                .define('C', Items.STRING)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.STICK),has(Items.STICK))
                .unlockedBy(getHasName(Items.STRING),has(Items.STRING))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CRUDE_STONE_BRICKS.get(),4)
                .pattern("CC ")
                .pattern("CC ")
                .define('C', Items.COBBLESTONE)
                .unlockedBy(getHasName(Items.COBBLESTONE),has(Items.COBBLESTONE))
                .save(pWriter);


        //hammers
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WOODEN_HAMMER.get())
                .pattern("CCC")
                .pattern("CSC")
                .pattern(" S ")
                .define('C', ItemTags.PLANKS)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_planks",has(ItemTags.PLANKS))
                .save(pWriter);

        hammerBuilder(pWriter,ModItems.CRUDE_TIN_INGOT.get(),ModItems.CRUDE_TIN_HAMMER.get());
        hammerBuilder(pWriter,ModItems.TIN_INGOT.get(),ModItems.TIN_HAMMER.get());

        hammerBuilder(pWriter,ModItems.CRUDE_COPPER_INGOT.get(),ModItems.CRUDE_COPPER_HAMMER.get());
        hammerBuilder(pWriter,Items.COPPER_INGOT,ModItems.COPPER_HAMMER.get());

        //mehty pipes
        mehtyBuilder(pWriter,ModItems.CRUDE_TIN_INGOT.get(),ModItems.CRUDE_TIN_MEHTY.get());
        mehtyBuilder(pWriter,ModItems.TIN_INGOT.get(),ModItems.TIN_MEHTY.get());

        mehtyBuilder(pWriter,ModItems.CRUDE_COPPER_INGOT.get(),ModItems.CRUDE_COPPER_MEHTY.get());
        mehtyBuilder(pWriter,Items.COPPER_INGOT,ModItems.COPPER_MEHTY.get());


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.WOODEN_CLUB.get())
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .define('#', ItemTags.PLANKS)
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(pWriter, ResourceLocation.tryBuild("minecraft", "wooden_sword"));

        //sling
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.SLING.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern("#/#")
                .define('#', Items.STRING)
                .define('/', ModItems.LEATHER_STRAP.get())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.COW_HIDE_PELT_CLOAK.get())
                .pattern(" |/")
                .pattern("# #")
                .pattern("###")
                .define('#', ModItems.COW_HIDE.get())
                .define('/', ModItems.CORD_SPOOL.get())
                .define('|', ModItems.BONE_NEEDLE.get())
                .unlockedBy("has_hide", has(ModTags.Items.HIDES))
                .save(pWriter);
        //nugget recipes
        Map.of(
                ModItems.CRUDE_COPPER_NUGGET.get(),ModItems.CRUDE_COPPER_INGOT.get(),
                ModItems.TIN_NUGGET.get(), ModItems.TIN_INGOT.get(),
                ModItems.CRUDE_TIN_NUGGET.get(),ModItems.CRUDE_TIN_INGOT.get(),
                ModItems.CRUDE_GOLD_NUGGET.get(),ModItems.CRUDE_GOLD_INGOT.get(),
                ModItems.CRUDE_IRON_NUGGET.get(),ModItems.CRUDE_IRON_INGOT.get()
        ).forEach((nugget,ingot )->{nuggetBuilder(pWriter,nugget,ingot);});

        nuggetBuilder(pWriter,ModItems.COPPER_NUGGET.get(),Items.COPPER_INGOT,
                ResourceLocation.tryBuild("minecraft", "copper_ingot_from_nuggets"),
                ResourceLocation.tryBuild("minecraft", "copper_nugget"));
        nuggetBuilder(pWriter,Items.GOLD_NUGGET,Items.GOLD_INGOT,
                ResourceLocation.tryBuild("minecraft", "gold_ingot_from_nuggets"),
                ResourceLocation.tryBuild("minecraft", "gold_nugget"));
        nuggetBuilder(pWriter,Items.IRON_NUGGET,Items.IRON_INGOT,
                ResourceLocation.tryBuild("minecraft", "iron_ingot_from_nuggets"),
                ResourceLocation.tryBuild("minecraft", "iron_nugget"));


        //cobble smelting

        Map.of(
                ModBlocks.GRANITE_COBBLE.get(), Blocks.GRANITE,
                ModBlocks.DIORITE_COBBLE.get(), Blocks.DIORITE,
                ModBlocks.ANDESITE_COBBLE.get(), Blocks.ANDESITE,
                ModBlocks.CALCITE_COBBLE.get(), Blocks.CALCITE,
                ModBlocks.TUFF_COBBLE.get(), Blocks.TUFF,
                Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE,
                Blocks.COBBLESTONE, Blocks.STONE
        ).forEach((cobble,result) ->{
            oreSmelting(pWriter,List.of(cobble),RecipeCategory.MISC,result,0.1f,20*20, getItemName(result));
        });
        //plates
        plateBuilder(pWriter,ModItems.CRUDE_TIN_PLATE.get(),ModItems.CRUDE_TIN_INGOT.get(),ModTags.Items.HAMMER_TIER_1);
        plateBuilder(pWriter,ModItems.TIN_PLATE.get(),ModItems.TIN_INGOT.get(),ModTags.Items.HAMMER_TIER_1);

        plateBuilder(pWriter,ModItems.CRUDE_COPPER_PLATE.get(),ModItems.CRUDE_COPPER_INGOT.get(),ModTags.Items.HAMMER_TIER_1);
        plateBuilder(pWriter,ModItems.COPPER_PLATE.get(),Items.COPPER_INGOT,ModTags.Items.HAMMER_TIER_1);

        plateBuilder(pWriter,ModItems.CRUDE_GOLD_PLATE.get(),ModItems.CRUDE_GOLD_INGOT.get(),ModTags.Items.HAMMER_TIER_1);
        plateBuilder(pWriter,ModItems.GOLD_PLATE.get(),Items.GOLD_INGOT,ModTags.Items.HAMMER_TIER_1);

        plateBuilder(pWriter,ModItems.CRUDE_IRON_PLATE.get(),ModItems.CRUDE_IRON_INGOT.get(),ModTags.Items.HAMMER_TIER_1);
        plateBuilder(pWriter,ModItems.IRON_PLATE.get(),Items.IRON_INGOT,ModTags.Items.HAMMER_TIER_1);

        //chips
        chipBuilder(pWriter,ModItems.CRUDE_TIN_CHIP.get(), ModItems.CRUDE_TIN_NUGGET.get(),ModTags.Items.HAMMER_TIER_1);
        chipBuilder(pWriter,ModItems.TIN_CHIP.get(), ModItems.TIN_NUGGET.get(),ModTags.Items.HAMMER_TIER_1);

        chipBuilder(pWriter,ModItems.CRUDE_COPPER_CHIP.get(), ModItems.CRUDE_COPPER_NUGGET.get(),ModTags.Items.HAMMER_TIER_1);
        chipBuilder(pWriter,ModItems.COPPER_CHIP.get(), ModItems.COPPER_NUGGET.get(),ModTags.Items.HAMMER_TIER_1);

        chipBuilder(pWriter,ModItems.CRUDE_GOLD_CHIP.get(), ModItems.CRUDE_GOLD_NUGGET.get(),ModTags.Items.HAMMER_TIER_1);
        chipBuilder(pWriter,ModItems.GOLD_CHIP.get(), Items.GOLD_NUGGET,ModTags.Items.HAMMER_TIER_1);

        chipBuilder(pWriter,ModItems.CRUDE_IRON_CHIP.get(), ModItems.CRUDE_IRON_NUGGET.get(),ModTags.Items.HAMMER_TIER_1);
        chipBuilder(pWriter,ModItems.IRON_CHIP.get(), Items.IRON_NUGGET,ModTags.Items.HAMMER_TIER_1);

        //grits
        gritBuilder(pWriter,new gridItems(Items.IRON_NUGGET,null)
                ,ModItems.IRON_GRIT.get(),ModTags.Items.HAMMER_TIER_1);
        gritBuilder(pWriter,new gridItems(ModItems.CRUDE_IRON_NUGGET.get(),List.of(Items.IRON_ORE,Items.DEEPSLATE_IRON_ORE,Items.RAW_IRON))
                ,ModItems.CRUDE_IRON_GRIT.get(),ModTags.Items.HAMMER_TIER_1);
        gritBuilder(pWriter,new gridItems(Items.GOLD_NUGGET,null)
                ,ModItems.GOLD_GRIT.get(),ModTags.Items.HAMMER_TIER_1);
        gritBuilder(pWriter,new gridItems(ModItems.CRUDE_GOLD_NUGGET.get(),List.of(Items.GOLD_ORE,Items.DEEPSLATE_GOLD_ORE,Items.RAW_GOLD))
                ,ModItems.CRUDE_GOLD_GRIT.get(),ModTags.Items.HAMMER_TIER_1);


        //shapeless
        hammerRecipe(pWriter,Items.RAW_GOLD,ModItems.GOLDVEIN.get(),ModTags.Items.HAMMER_TIER_0,1);
        hammerRecipe(pWriter,ModItems.CHALKOUS.get(),ModItems.COPPER_CHIP.get(),ModTags.Items.HAMMER_TIER_1,1);
        //scroll and patterns
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS,ModItems.SCROLL.get(),1)
                .requires(ModItems.LEATHER_STRAP.get())
                .requires(Items.PAPER)
                .unlockedBy(getHasName(Items.PAPER),has(Items.PAPER))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS,ModItems.HELLENIC_BLUEPRINT.get(),1)
                .requires(ModItems.SCROLL.get())
                .requires(Items.IRON_INGOT)
                .requires(ItemTags.FLOWERS)
                .unlockedBy(getHasName(ModItems.SCROLL.get()),has(ModItems.SCROLL.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS,ModItems.ROMAN_BLUEPRINT.get(),1)
                .requires(ModItems.SCROLL.get())
                .requires(Items.IRON_INGOT)
                .requires(Items.FEATHER)
                .unlockedBy(getHasName(ModItems.SCROLL.get()),has(ModItems.SCROLL.get()))
                .save(pWriter);
        //pebbles
        Map.of(
                ModItems.PEBBLE.get(), Blocks.COBBLESTONE,
                ModItems.DEEPSLATE_PEBBLE.get(), Blocks.COBBLED_DEEPSLATE,
                ModItems.ANDESITE_PEBBLE.get(), ModBlocks.ANDESITE_COBBLE.get(),
                ModItems.GRANITE_PEBBLE.get(), ModBlocks.GRANITE_COBBLE.get(),
                ModItems.DIORITE_PEBBLE.get(), ModBlocks.DIORITE_COBBLE.get(),
                ModItems.TUFF_PEBBLE.get(), ModBlocks.TUFF_COBBLE.get(),
                ModItems.CALCITE_PEBBLE.get(), ModBlocks.CALCITE_COBBLE.get()
                ).forEach(
                (pebble,cobble) ->{
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,pebble,9)
                            .requires(cobble)
                            .unlockedBy(getHasName(cobble),has(cobble))
                            .save(pWriter);
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,cobble,1)
                            .requires(pebble,9)
                            .unlockedBy(getHasName(pebble),has(pebble))
                            .save(pWriter);
                }
        );



        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS,ModItems.WRAPPED_WOODEN_CLUB.get(),1)
                .requires(ModItems.LEATHER_STRAP.get())
                .requires(ModItems.WOODEN_CLUB.get())
                .unlockedBy(getHasName(ModItems.LEATHER_STRAP.get()),has(ModItems.LEATHER_STRAP.get()))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LEATHER_STRAP.get(),4)
                .requires(Items.LEATHER)
                .unlockedBy(getHasName(Items.LEATHER),has(Items.LEATHER))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Items.LEATHER,1)
                .requires(ModItems.COW_HIDE.get(),2)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ModItems.COW_HIDE.get()),has(ModItems.COW_HIDE.get()))
                .save(pWriter, ResourceLocation.tryBuild(LaminaEtIgnis.MODID, "leather_from_cow_hide"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Items.LEATHER,1)
                .requires(Items.RABBIT_HIDE,4)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(Items.RABBIT_HIDE),has(Items.RABBIT_HIDE))
                .save(pWriter, ResourceLocation.tryBuild("minecraft", "leather"));

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

                //tin
                oreSmelting(pWriter,TIN_SMELTABLES,RecipeCategory.MISC,ModItems.CRUDE_TIN_INGOT.get(),0.7f,20*30,"tin");
                oreBlasting(pWriter,TIN_SMELTABLES,RecipeCategory.MISC,ModItems.CRUDE_TIN_INGOT.get(),0.5f,20*22+10,"tin");

                oreSmelting(pWriter,List.of(ModItems.CRUDE_TIN_INGOT.get()),RecipeCategory.MISC,ModItems.TIN_INGOT.get(),0.7f,20*30,"tin");
                oreBlasting(pWriter,List.of(ModItems.CRUDE_TIN_INGOT.get()),RecipeCategory.MISC,ModItems.TIN_INGOT.get(),0.5f,20*22+10,"tin");


        //copper
                oreSmelting(pWriter,COPPER_SMELTABLES,RecipeCategory.MISC,ModItems.CRUDE_COPPER_INGOT.get(),0.7f,800,"copper");
                oreBlasting(pWriter,COPPER_SMELTABLES,RecipeCategory.MISC,ModItems.CRUDE_COPPER_INGOT.get(),0.5f,600,"copper");

                oreSmelting(pWriter,List.of(ModItems.CRUDE_COPPER_INGOT.get()),RecipeCategory.MISC,Items.COPPER_INGOT,0.7f,800,"copper");
                oreBlasting(pWriter,List.of(ModItems.CRUDE_COPPER_INGOT.get()),RecipeCategory.MISC,Items.COPPER_INGOT,0.5f,600,"copper");

                //iron
                oreBlasting(pWriter,IRON_SMELTABLES,RecipeCategory.MISC,ModItems.CRUDE_IRON_INGOT.get(),0.5f,600,"iron");
                oreSmelting(pWriter,IRON_SMELTABLES,RecipeCategory.MISC,ModItems.CRUDE_IRON_INGOT.get(),0.5f,600,"iron");

                oreBlasting(pWriter,List.of(ModItems.CRUDE_IRON_INGOT.get()),RecipeCategory.MISC,Items.IRON_INGOT,0.5f,600*4,"iron");
                //gold
                oreBlasting(pWriter,List.of(Items.RAW_GOLD),RecipeCategory.MISC,ModItems.CRUDE_GOLD_INGOT.get(),0.5f,675,"gold");

                oreBlasting(pWriter,List.of(ModItems.CRUDE_GOLD_INGOT.get()),RecipeCategory.MISC,Items.GOLD_INGOT,0.5f,675,"gold");
    }
    private static void stairBuilder(Consumer<FinishedRecipe> writer, Block base, Block stairs) {
        ModRecipeBuilders.stairBuilder(writer, base, stairs);
    }
    private static void slabBuilder(Consumer<FinishedRecipe> writer, Block base, Block slab) {
        ModRecipeBuilders.slabBuilder(writer, base, slab);
    }
    private static void wallBuilder(Consumer<FinishedRecipe> writer, Block base, Block wall) {
        ModRecipeBuilders.wallBuilder(writer, base, wall);
    }
}
