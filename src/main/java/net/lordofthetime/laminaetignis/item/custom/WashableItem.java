package net.lordofthetime.laminaetignis.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WashableItem extends Item {

    private final ItemLike item;
    private int useDuration = 20;
    public WashableItem(Properties properties, ItemLike item) {
        super(properties);
        this.item=item;
    }
    public WashableItem(Properties properties, ItemLike item, int useDuration) {
        super(properties);
        this.item=item;
        this.useDuration = useDuration;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return useDuration;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BRUSH;
    }

    // ✅ Works on air too
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.getBlockState(player.blockPosition()).getFluidState().is(FluidTags.WATER)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.pass(stack);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity){
        if(entity instanceof Player player){
            stack.shrink(1);
            player.addItem(new ItemStack(item,1));
            return stack;
        }
        return stack;
    }
    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (!(entity instanceof Player player)) return;

        int ticksUsed = this.getUseDuration(stack) - remainingUseDuration + 1;
        boolean doEffect = ticksUsed % 10 == 5;
        if (!doEffect) return;

        if (level.getBlockState(player.blockPosition()).getFluidState().is(FluidTags.WATER)) {

            Vec3 lookVec = entity.getLookAngle();
            // Always spawn about 1–1.5 blocks in front of the player’s eyes
            Vec3 eyePos = player.getEyePosition(1.0F);
            Vec3 pos = eyePos.add(lookVec.scale(1.2)); // clamp distance here
            level.playSound(player, player.blockPosition(), SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 0.5F, 1.0F);

            // Spawn splash particles
            for (int k = 0; k < 5; ++k) {
                level.addParticle(ParticleTypes.SPLASH,
                        pos.x + (level.getRandom().nextDouble() - 0.5) * 0.3,
                        pos.y,
                        pos.z + (level.getRandom().nextDouble() - 0.5) * 0.3,
                        0.0, 0.05, 0.0);
            }
        }
    }


}
