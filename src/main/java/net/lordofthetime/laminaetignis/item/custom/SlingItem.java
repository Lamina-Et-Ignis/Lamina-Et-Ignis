package net.lordofthetime.laminaetignis.item.custom;

import net.lordofthetime.laminaetignis.entity.custom.PebbleProjectileEntity;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.tags.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class SlingItem extends BowItem {
    public SlingItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (itemStack) -> itemStack.is(ModTags.Items.SLING_AMMO);
    }
    public static float getPowerForTime(int charge){
        float f = charge / 10f;
        if(f >  2f){
            f = 2f;
        }
        return f;
    };
    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        int charge = this.getUseDuration(pStack) - pTimeLeft;
        if(charge < 5){
            return;
        }
        if (pEntityLiving instanceof Player player) {
            ItemStack itemstack = null;
            if(player.getAbilities().instabuild) {
                itemstack = ModItems.PEBBLE.get().getDefaultInstance();
            }
            else{
                for(int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                    ItemStack slotItemStack = player.getInventory().getItem(i);
                    if(slotItemStack.getTags().anyMatch(Predicate.isEqual(ModTags.Items.SLING_AMMO))) {
                        itemstack = slotItemStack;
                        break;
                    }
                }
            }
            if(itemstack.getTags().anyMatch(Predicate.isEqual(ModTags.Items.SLING_AMMO))){
                pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
                if (!pLevel.isClientSide) {
                    PebbleProjectileEntity pebble = new PebbleProjectileEntity(player, pLevel);
                    pebble.setItem(itemstack);
                    pebble.setDamage(2 * getPowerForTime(charge));
                    pebble.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                    pStack.hurtAndBreak(1, player, (p) -> {
                            p.broadcastBreakEvent(player.getUsedItemHand());});
                    pLevel.addFreshEntity(pebble);
                }

                player.awardStat(Stats.ITEM_USED.get(this));
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
            }
        }
    }
}
