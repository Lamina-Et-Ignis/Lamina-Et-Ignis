package net.lordofthetime.laminaetignis.block.entity;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LaminaEtIgnis.MODID);

    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> DRYING_RACK_BLOCK_ENTITY =
                BLOCK_ENTITIES.register("drying_rack_block_entity",() ->
                        BlockEntityType.Builder.of(DryingRackBlockEntity::new,ModBlocks.DRYING_RACK.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
