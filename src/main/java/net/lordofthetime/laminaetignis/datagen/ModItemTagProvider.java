package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, LaminaEtIgnis.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.SLING_AMMO).add(
                ModItems.PEBBLE.get(),
                ModItems.DEEPSLATE_PEBBLE.get(),
                ModItems.TUFF_PEBBLE.get(),
                ModItems.CALCITE_PEBBLE.get(),
                ModItems.GRANITE_PEBBLE.get(),
                ModItems.ANDESITE_PEBBLE.get(),
                ModItems.DIORITE_PEBBLE.get()

        );
        this.tag(ModTags.Items.HIDES).add(
                ModItems.COW_HIDE.get(),
                Items.RABBIT_HIDE
        );
        this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(
                ModBlocks.GRANITE_COBBLE.get().asItem(),
                ModBlocks.DIORITE_COBBLE.get().asItem(),
                ModBlocks.ANDESITE_COBBLE.get().asItem()
        );
        //hammer tiers
        this.tag(ModTags.Items.HAMMER_TIER_0).add(
                ModItems.WOODEN_HAMMER.get(),
                ModItems.CRUDE_TIN_MEHTY.get(),
                ModItems.TIN_MEHTY.get(),
                ModItems.CRUDE_COPPER_MEHTY.get(),
                ModItems.COPPER_MEHTY.get()
        );
        this.tag(ModTags.Items.HAMMER_TIER_0).addTags(
                ModTags.Items.HAMMER_TIER_1
        );
        this.tag(ModTags.Items.HAMMER_TIER_1).add(
                ModItems.CRUDE_TIN_HAMMER.get(),
                ModItems.TIN_HAMMER.get(),
                ModItems.CRUDE_COPPER_HAMMER.get(),
                ModItems.COPPER_HAMMER.get()
        );



        // items tags when needed
//        this.tag(ItemTags.LOGS_THAT_BURN).add(
//                ModBlocks.LOG.get().asItem(),
//        );
//        this.tag(ItemTags.PLANKS).add(
//                ModBlocks.PLANK.get().asItem(),
//        );
    }
}
