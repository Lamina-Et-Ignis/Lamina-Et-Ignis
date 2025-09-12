package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
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

//        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
//        );
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
//        this.tag(BlockTags.WALLS).add(
//        );
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
