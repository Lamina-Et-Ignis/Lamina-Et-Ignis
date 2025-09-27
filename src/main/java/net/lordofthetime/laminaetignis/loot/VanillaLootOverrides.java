package net.lordofthetime.laminaetignis.loot;

import net.lordofthetime.laminaetignis.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VanillaLootOverrides {

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {

        //mobs
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","entities/cow"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(BinomialDistributionGenerator.binomial(1, 0.40F))
                                    .add(LootItem.lootTableItem(ModItems.COW_HIDE.get()))
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(0.0F, 2.0F))
                                    .add(LootItem.lootTableItem(Items.BONE))
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                    .add(LootItem.lootTableItem(Items.BEEF))
                    ).build());
        }


        //stone and ores

        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/stone"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(4.0F, 9.0F))
                                    .add(LootItem.lootTableItem(ModItems.PEBBLE.get()))
                    ).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/copper_ore"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(2.0F, 3.0F))
                                    .add(LootItem.lootTableItem(ModItems.PEBBLE.get()))
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(1.0F, 1.0F))
                                    .add(LootItem.lootTableItem(Items.RAW_COPPER))
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(BinomialDistributionGenerator.binomial(1, 0.20F))
                                    .add(LootItem.lootTableItem(Items.RAW_COPPER))
                    ).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/coal_ore"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(2.0F, 3.0F))
                                    .add(LootItem.lootTableItem(ModItems.PEBBLE.get()))
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(1.0F, 1.0F))
                                    .add(LootItem.lootTableItem(Items.COAL))
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(BinomialDistributionGenerator.binomial(1, 0.20F))
                                    .add(LootItem.lootTableItem(Items.COAL))
                    ).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/iron_ore"))) {
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 3.0F)).add(LootItem.lootTableItem(ModItems.PEBBLE.get())).build());

        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/gold_ore"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(2.0F, 4.0F))
                                    .add(LootItem.lootTableItem(ModItems.PEBBLE.get()))
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(1.0F, 1.0F))
                                    .add(LootItem.lootTableItem(ModItems.GOLDVEIN.get()))
                    ).withPool(
                            LootPool.lootPool()
                                    .setRolls(BinomialDistributionGenerator.binomial(1, 0.05F))
                                    .add(LootItem.lootTableItem(Items.RAW_GOLD))
                    ).build());
        }
    }
}