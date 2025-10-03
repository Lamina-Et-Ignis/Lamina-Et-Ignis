package net.lordofthetime.laminaetignis.loot;

import net.lordofthetime.laminaetignis.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VanillaLootOverrides {


    /*--------------------------------------

        Sheep drops change disabled for 0.1
        update, planned release in 0.1.1

    --------------------------------------*/

//    @SubscribeEvent
//    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
//        if (!(event.getTarget() instanceof Sheep sheep)) return;
//        ItemStack stack = event.getItemStack();
//        if (stack.getItem() != Items.SHEARS) return;
//        if (!sheep.level().isClientSide && sheep.readyForShearing()) {
//            event.setCanceled(true);
//            stack.hurtAndBreak(1, event.getEntity(), e -> e.broadcastBreakEvent(event.getHand()));
//            List<ItemStack> drops = List.of(
//                    new ItemStack(ModItems.GREASY_FLEECE.get(),1)
//            );
//
//            for (ItemStack drop : drops) {
//                sheep.spawnAtLocation(drop);
//            }
//            sheep.setSheared(true);
//        }
//    }
//    @SubscribeEvent
//    public static void onLivingDrops(LivingDropsEvent event) {
//        if (event.getEntity() instanceof Sheep sheep) {
//            // Remove wool drops added outside the loot table
//            event.getDrops().removeIf(itemEntity ->
//                    itemEntity.getItem().getTags().anyMatch(Predicate.isEqual(BlockTags.WOOL)) ||
//                            itemEntity.getItem().is(Items.WHITE_WOOL)
//            );
//        }
//    }
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
    if (event.getName().equals(ResourceLocation.tryBuild("minecraft","entities/sheep"))) {


          // replace for 0.1.1
          event.getTable().addPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(0.0F, 2.0F))
                                .add(LootItem.lootTableItem(Items.BONE)).build());
          event.getTable().addPool(LootPool.lootPool()
                  .setRolls(BinomialDistributionGenerator.binomial(1,0.66F))
                  .add(LootItem.lootTableItem(Items.STRING)).build());
//        event.setTable(LootTable.lootTable()
//                .withPool(
//                        LootPool.lootPool()
//                                .setRolls(UniformGenerator.between(0.0F, 2.0F))
//                                .add(LootItem.lootTableItem(Items.BONE))
//                ).withPool(
//                        LootPool.lootPool()
//                                .setRolls(ConstantValue.exactly(1))
//                                .add(LootItem.lootTableItem(ModItems.GREASY_FLEECE.get()))
//                ).build());
    }


        //stones

        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/stone"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(4.0F, 9.0F))
                                    .add(LootItem.lootTableItem(ModItems.PEBBLE.get()))
                    ).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/deepslate"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(4.0F, 9.0F))
                                    .add(LootItem.lootTableItem(ModItems.DEEPSLATE_PEBBLE.get()))
                    ).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/tuff"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(4.0F, 9.0F))
                                    .add(LootItem.lootTableItem(ModItems.TUFF_PEBBLE.get()))
                    ).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/calcite"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(4.0F, 9.0F))
                                    .add(LootItem.lootTableItem(ModItems.CALCITE_PEBBLE.get()))
                    ).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/diorite"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(4.0F, 9.0F))
                                    .add(LootItem.lootTableItem(ModItems.DIORITE_PEBBLE.get()))
                    ).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/andesite"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(4.0F, 9.0F))
                                    .add(LootItem.lootTableItem(ModItems.ANDESITE_PEBBLE.get()))
                    ).build());
        }if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/granite"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(4.0F, 9.0F))
                                    .add(LootItem.lootTableItem(ModItems.GRANITE_PEBBLE.get()))
                    ).build());
        }


        //ores
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
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/deepslate_copper_ore"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(2.0F, 3.0F))
                                    .add(LootItem.lootTableItem(ModItems.DEEPSLATE_PEBBLE.get()))
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
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/deepslate_coal_ore"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(2.0F, 3.0F))
                                    .add(LootItem.lootTableItem(ModItems.DEEPSLATE_PEBBLE.get()))
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
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 3.0F))
                    .add(LootItem.lootTableItem(ModItems.PEBBLE.get())).build());
        }
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/deepslate_iron_ore"))) {
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 3.0F))
                    .add(LootItem.lootTableItem(ModItems.DEEPSLATE_PEBBLE.get())).build());
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
        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/deepslate_gold_ore"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(2.0F, 4.0F))
                                    .add(LootItem.lootTableItem(ModItems.DEEPSLATE_PEBBLE.get()))
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