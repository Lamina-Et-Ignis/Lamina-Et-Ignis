package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LaminaEtIgnis.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        //delete tags

        //add copper back to tin tools when it will be obtainable with ore sand
//        tag(BlockTags.NEEDS_STONE_TOOL).remove(Blocks.COPPER_ORE);
//        tag(BlockTags.NEEDS_STONE_TOOL).remove(Blocks.DEEPSLATE_COPPER_ORE);


        //tool tags

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                //Stone and Deepslate
                ModBlocks.CRUDE_STONE_BRICKS.get(),
                ModBlocks.CRUDE_STONE_BRICK_STAIRS.get(),
                ModBlocks.CRUDE_STONE_BRICK_SLAB.get(),
                ModBlocks.CRUDE_STONE_BRICK_WALL.get(),
                ModBlocks.CRUDE_DEEPSLATE_BRICKS.get(),
                ModBlocks.CRUDE_DEEPSLATE_BRICK_STAIRS.get(),
                ModBlocks.CRUDE_DEEPSLATE_BRICK_SLAB.get(),
                ModBlocks.CRUDE_DEEPSLATE_BRICK_WALL.get(),
                //andesite
                ModBlocks.ANDESITE_BRICKS.get(),
                ModBlocks.ANDESITE_BRICK_STAIRS.get(),
                ModBlocks.ANDESITE_BRICK_SLAB.get(),
                ModBlocks.ANDESITE_BRICK_WALL.get(),
                ModBlocks.ANDESITE_COBBLE.get(),
                ModBlocks.ANDESITE_COBBLE_STAIRS.get(),
                ModBlocks.ANDESITE_COBBLE_SLAB.get(),
                ModBlocks.ANDESITE_COBBLE_WALL.get(),
                ModBlocks.CRUDE_ANDESITE_BRICKS.get(),
                ModBlocks.CRUDE_ANDESITE_BRICK_STAIRS.get(),
                ModBlocks.CRUDE_ANDESITE_BRICK_SLAB.get(),
                ModBlocks.CRUDE_ANDESITE_BRICK_WALL.get(),
                //DIORITE
                ModBlocks.DIORITE_BRICKS.get(),
                ModBlocks.DIORITE_BRICK_STAIRS.get(),
                ModBlocks.DIORITE_BRICK_SLAB.get(),
                ModBlocks.DIORITE_BRICK_WALL.get(),
                ModBlocks.DIORITE_COBBLE.get(),
                ModBlocks.DIORITE_COBBLE_STAIRS.get(),
                ModBlocks.DIORITE_COBBLE_SLAB.get(),
                ModBlocks.DIORITE_COBBLE_WALL.get(),
                ModBlocks.CRUDE_DIORITE_BRICKS.get(),
                ModBlocks.CRUDE_DIORITE_BRICK_STAIRS.get(),
                ModBlocks.CRUDE_DIORITE_BRICK_SLAB.get(),
                ModBlocks.CRUDE_DIORITE_BRICK_WALL.get(),
                //GRANITE
                ModBlocks.GRANITE_BRICKS.get(),
                ModBlocks.GRANITE_BRICK_STAIRS.get(),
                ModBlocks.GRANITE_BRICK_SLAB.get(),
                ModBlocks.GRANITE_BRICK_WALL.get(),
                ModBlocks.GRANITE_COBBLE.get(),
                ModBlocks.GRANITE_COBBLE_STAIRS.get(),
                ModBlocks.GRANITE_COBBLE_SLAB.get(),
                ModBlocks.GRANITE_COBBLE_WALL.get(),
                ModBlocks.CRUDE_GRANITE_BRICKS.get(),
                ModBlocks.CRUDE_GRANITE_BRICK_STAIRS.get(),
                ModBlocks.CRUDE_GRANITE_BRICK_SLAB.get(),
                ModBlocks.CRUDE_GRANITE_BRICK_WALL.get(),
                //CALCITE
                ModBlocks.CALCITE_BRICKS.get(),
                ModBlocks.CALCITE_BRICK_STAIRS.get(),
                ModBlocks.CALCITE_BRICK_SLAB.get(),
                ModBlocks.CALCITE_BRICK_WALL.get(),
                ModBlocks.CALCITE_COBBLE.get(),
                ModBlocks.CALCITE_COBBLE_STAIRS.get(),
                ModBlocks.CALCITE_COBBLE_SLAB.get(),
                ModBlocks.CALCITE_COBBLE_WALL.get(),
                ModBlocks.CRUDE_CALCITE_BRICKS.get(),
                ModBlocks.CRUDE_CALCITE_BRICK_STAIRS.get(),
                ModBlocks.CRUDE_CALCITE_BRICK_SLAB.get(),
                ModBlocks.CRUDE_CALCITE_BRICK_WALL.get(),
                //TUFF
                ModBlocks.TUFF_BRICKS.get(),
                ModBlocks.TUFF_BRICK_STAIRS.get(),
                ModBlocks.TUFF_BRICK_SLAB.get(),
                ModBlocks.TUFF_BRICK_WALL.get(),
                ModBlocks.TUFF_COBBLE.get(),
                ModBlocks.TUFF_COBBLE_STAIRS.get(),
                ModBlocks.TUFF_COBBLE_SLAB.get(),
                ModBlocks.TUFF_COBBLE_WALL.get(),
                ModBlocks.CRUDE_TUFF_BRICKS.get(),
                ModBlocks.CRUDE_TUFF_BRICK_STAIRS.get(),
                ModBlocks.CRUDE_TUFF_BRICK_SLAB.get(),
                ModBlocks.CRUDE_TUFF_BRICK_WALL.get(),

                //ORES
                ModBlocks.TIN_ORE.get()

        );
//        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
//        );
//        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
//        );
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                ModBlocks.CRUDE_IRON_SAND.get(),
                ModBlocks.IRON_SAND.get(),
                ModBlocks.CRUDE_GOLD_SAND.get(),
                ModBlocks.GOLD_SAND.get()
        );

        //tool tier tags

//        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
//        );
//        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
//        );
//        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
//        );
//        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(
//        );

        //Fence, fence gates, walls tags
        this.tag(BlockTags.WALLS).add(
                ModBlocks.ANDESITE_BRICK_WALL.get(),
                ModBlocks.GRANITE_BRICK_WALL.get(),
                ModBlocks.DIORITE_BRICK_WALL.get(),
                ModBlocks.TUFF_BRICK_WALL.get(),
                ModBlocks.CALCITE_BRICK_WALL.get(),
                ModBlocks.ANDESITE_COBBLE_WALL.get(),
                ModBlocks.GRANITE_COBBLE_WALL.get(),
                ModBlocks.DIORITE_COBBLE_WALL.get(),
                ModBlocks.TUFF_COBBLE_WALL.get(),
                ModBlocks.CALCITE_COBBLE_WALL.get(),
                ModBlocks.CRUDE_ANDESITE_BRICK_WALL.get(),
                ModBlocks.CRUDE_GRANITE_BRICK_WALL.get(),
                ModBlocks.CRUDE_DIORITE_BRICK_WALL.get(),
                ModBlocks.CRUDE_TUFF_BRICK_WALL.get(),
                ModBlocks.CRUDE_CALCITE_BRICK_WALL.get(),
                ModBlocks.CRUDE_DEEPSLATE_BRICK_WALL.get(),
                ModBlocks.CRUDE_STONE_BRICK_WALL.get()


        );
//        this.tag(BlockTags.FENCES).add(
//        );
//        this.tag(BlockTags.FENCE_GATES).add(
//        );
//        this.tag(BlockTags.LOGS_THAT_BURN).add(
//        );
//        this.tag(BlockTags.PLANKS).add(
//        );

        // add copper back to tin tools when it will be obtainable with ore sand
        this.tag(ModTags.Blocks.NEEDS_TIN_TOOL).add(
//                Blocks.COPPER_ORE,
//                Blocks.DEEPSLATE_COPPER_ORE,
                ModBlocks.TIN_ORE.get()
        );



    }
}
