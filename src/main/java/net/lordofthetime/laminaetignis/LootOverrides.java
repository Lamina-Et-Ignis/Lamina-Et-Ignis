package net.lordofthetime.laminaetignis;

import net.lordofthetime.laminaetignis.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootOverrides {

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        // Check if the loot table belongs to Stone

        if (event.getName().equals(ResourceLocation.tryBuild("minecraft","blocks/stone"))) {
            event.setTable(LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(2.0F, 3.0F))
                                    .add(LootItem.lootTableItem(ModItems.PEBBLE.get()))
                    ).build());
        }
    }
}