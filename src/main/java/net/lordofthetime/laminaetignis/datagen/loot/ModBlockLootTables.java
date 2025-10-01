package net.lordofthetime.laminaetignis.datagen.loot;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
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
        this.add(ModBlocks.TIN_ORE.get(),
                block -> createOreDrops(ModBlocks.TIN_ORE.get(),ModItems.RAW_TIN.get(),
                        ConstantValue.exactly(1),
                        BinomialDistributionGenerator.binomial(1,0.2f)));
        this.add(ModBlocks.IRON_SAND.get(),
                block -> createOreSandDrops(ModBlocks.IRON_SAND.get(),
                        Map.of(
                                ModItems.IRON_GRIT.get(),UniformGenerator.between(1,2),
                                Items.IRON_NUGGET,BinomialDistributionGenerator.binomial(1,0.5f)
                        )));
        this.add(ModBlocks.CRUDE_IRON_SAND.get(),
                block -> createOreSandDrops(ModBlocks.IRON_SAND.get(),
                        Map.of(
                                ModItems.CRUDE_IRON_GRIT.get(),UniformGenerator.between(1,2),
                                ModItems.CRUDE_IRON_NUGGET.get(),BinomialDistributionGenerator.binomial(1,0.5f)
                        )));
        this.add(ModBlocks.GOLD_SAND.get(),
                block -> createOreSandDrops(ModBlocks.GOLD_SAND.get(),
                        Map.of(
                                ModItems.GOLD_GRIT.get(),UniformGenerator.between(1,2),
                                Items.GOLD_NUGGET,BinomialDistributionGenerator.binomial(1,0.5f)
                        )));
        this.add(ModBlocks.CRUDE_GOLD_SAND.get(),
                block -> createOreSandDrops(ModBlocks.GOLD_SAND.get(),
                        Map.of(
                                ModItems.CRUDE_GOLD_GRIT.get(),UniformGenerator.between(1,2),
                                ModItems.CRUDE_GOLD_NUGGET.get(),BinomialDistributionGenerator.binomial(1,0.5f)
                        )));
    }
    protected LootTable.Builder createOreDrops(Block pBlock, Item item, NumberProvider amount) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(amount))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
    protected LootTable.Builder createOreSandDrops(Block block, Map<Item,NumberProvider> items){
        LootTable.Builder table = LootTable.lootTable();
                items.forEach(
                        (item,amount) -> {
                            table.withPool(LootPool.lootPool()
                                    .setRolls(amount)
                                    .add(LootItem.lootTableItem(item)
                                            .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
                        }
                );
                return table.withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.SAND)));
    }
    protected LootTable.Builder createOreDrops(Block pBlock, Item item,NumberProvider amount, BinomialDistributionGenerator bonus) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(amount))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        ))
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.PEBBLE.get()))
                        .setRolls(UniformGenerator.between(2,3)))
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(item))
                        .setRolls(bonus));
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
