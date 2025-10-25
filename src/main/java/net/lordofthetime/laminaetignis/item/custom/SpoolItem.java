package net.lordofthetime.laminaetignis.item.custom;

import net.lordofthetime.laminaetignis.recipe.WindingRecipe;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class SpoolItem extends Item {

    private final int useDuration = 20;

    public SpoolItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return useDuration;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BRUSH;
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity){
        if(entity instanceof Player player){
            ItemStack input;
            if (player.getMainHandItem().equals(stack)) {
                input = player.getOffhandItem();
            }
            else{
                input = player.getMainHandItem();
            }

            WindingRecipe windingRecipe = getRecipe(input,level);
            if (windingRecipe != null) {
                //spool
                stack.shrink(1);

                input.shrink(windingRecipe.getInput().getCount());
                player.addItem(new ItemStack(windingRecipe.getOutput().getItem()));
            }
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

        level.playSound(player, player.blockPosition(), SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 0.5F, 1.0F);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack spool = player.getItemInHand(hand);
        ItemStack material;
        if(hand.equals(InteractionHand.MAIN_HAND)){
            material = player.getOffhandItem();
        }
        else{
            material = player.getMainHandItem();
        }

        WindingRecipe windingRecipe = getRecipe(material,level);
        System.out.println(windingRecipe.getInput());
        if (windingRecipe != null) {
            if(windingRecipe.getInput().getCount() <= material.getCount()){
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(spool);
            }
        }
        return InteractionResultHolder.pass(spool);
    }
    public WindingRecipe getRecipe(ItemStack material, Level level){
        SimpleContainer fake = new SimpleContainer(1);
        fake.setItem(0, material);
        Optional<WindingRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(WindingRecipe.Type.INSTANCE, fake, level);
        return recipe.orElse(null);
    }
}
