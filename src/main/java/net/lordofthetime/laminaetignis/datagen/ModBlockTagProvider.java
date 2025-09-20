package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
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

        //tool tags

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                //andesite
                ModBlocks.ANDESITE_BRICKS.get(),
                ModBlocks.ANDESITE_BRICK_STAIRS.get(),
                ModBlocks.ANDESITE_BRICK_SLAB.get(),
                ModBlocks.ANDESITE_BRICK_WALL.get(),
                //DIORITE
                ModBlocks.DIORITE_BRICKS.get(),
                ModBlocks.DIORITE_BRICK_STAIRS.get(),
                ModBlocks.DIORITE_BRICK_SLAB.get(),
                ModBlocks.DIORITE_BRICK_WALL.get(),
                //GRANITE
                ModBlocks.GRANITE_BRICKS.get(),
                ModBlocks.GRANITE_BRICK_STAIRS.get(),
                ModBlocks.GRANITE_BRICK_SLAB.get(),
                ModBlocks.GRANITE_BRICK_WALL.get(),

                //ORES
                ModBlocks.TIN_ORE.get()

        );
//        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
//        );
//        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
//        );
//        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
//        );

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
                ModBlocks.DIORITE_BRICK_WALL.get()

        );
//        this.tag(BlockTags.FENCES).add(
//        );
//        this.tag(BlockTags.FENCE_GATES).add(
//        );
//        this.tag(BlockTags.LOGS_THAT_BURN).add(
//        );
//        this.tag(BlockTags.PLANKS).add(
//        );

        // Mod Tags



    }
}
