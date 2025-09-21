package net.lordofthetime.laminaetignis.entity;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.entity.custom.PebbleProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities{

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LaminaEtIgnis.MODID);

    public static final RegistryObject<EntityType<PebbleProjectileEntity>> PEBBLE_PROJECTILE =
            ENTITY_TYPES.register("pebble_projectile", () -> EntityType.Builder.<PebbleProjectileEntity>of(PebbleProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("pebble_projectile"));

    public static void  register(IEventBus eventBus) {ENTITY_TYPES.register(eventBus);}
}
