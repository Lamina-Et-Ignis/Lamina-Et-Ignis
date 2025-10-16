package net.lordofthetime.laminaetignis.event;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.fluid.NonPlaceableFluid;
import net.lordofthetime.laminaetignis.item.custom.SpoolItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LaminaEtIgnis.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnRightClickEvents {

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack offhandItem = player.getOffhandItem();

        InteractionHand handUsed = event.getHand();

        if (!offhandItem.isEmpty() && offhandItem.getItem() instanceof SpoolItem && handUsed.equals(InteractionHand.MAIN_HAND)) {
            offhandItem.use(event.getLevel(), player, InteractionHand.OFF_HAND);
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void onRightClickBucket(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof BucketItem bucketItem) {
            Fluid fluid = bucketItem.getFluid();
            System.out.println(fluid.getFluidType());
            if (isNonPlaceableFluid(fluid)) {
                // prevent placement
                System.out.println("works");
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.FAIL);
            }
        }
    }
    public static boolean isNonPlaceableFluid(Fluid fluid) {
        // check if the fluid is an instance of either Source or Flowing subclass
        return fluid instanceof NonPlaceableFluid.Source || fluid instanceof NonPlaceableFluid.Flowing;
    }
}

