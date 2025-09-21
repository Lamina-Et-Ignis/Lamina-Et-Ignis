package net.lordofthetime.laminaetignis.entity.custom;


import net.lordofthetime.laminaetignis.entity.ModEntities;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class PebbleProjectileEntity extends ThrowableItemProjectile {


    public PebbleProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public PebbleProjectileEntity(Level pLevel) {
        super(ModEntities.PEBBLE_PROJECTILE.get(), pLevel);
    }public PebbleProjectileEntity(LivingEntity livingEntity, Level pLevel) {
        super(ModEntities.PEBBLE_PROJECTILE.get(),livingEntity, pLevel);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.PEBBLE.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        pResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), (float)2);
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level().isClientSide) {
            ItemStack stack = new ItemStack(getDefaultItem(), 1); // 1 pebble
            BlockPos pos = pResult.getBlockPos().above();
            ItemEntity itemEntity = new ItemEntity(level(),
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    stack);
            itemEntity.setPickUpDelay(10);
            level().addFreshEntity(itemEntity);
            this.discard();
        }
    }
}
