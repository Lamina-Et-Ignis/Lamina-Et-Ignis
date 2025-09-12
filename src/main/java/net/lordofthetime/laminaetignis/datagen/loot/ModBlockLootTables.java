package net.lordofthetime.laminaetignis.datagen.loot;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for(RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()){
            if(String.valueOf(block.getId()).endsWith("slab")){
                this.add(block.get(), itemTable ->  createSlabItemTable(block.get()));
            }
            else if(String.valueOf(block.getId()).endsWith("leaves")){
                // change to sapling, block.ge
                // String.valueOf(block.getId()).split("_")[0] + "_sapling"
                this.add(block.get(), drops -> createLeavesDrops(drops,block.get(),NORMAL_LEAVES_SAPLING_CHANCES));
            }
            else{
                this.dropSelf(block.get());
            }
        }
//        add ores drop, make sure to somehow exclude them later
//        this.add(orehere ,block -> createRedstoneOreDrops());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
