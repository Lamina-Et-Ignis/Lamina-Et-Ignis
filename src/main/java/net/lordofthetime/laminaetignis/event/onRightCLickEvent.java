package net.lordofthetime.laminaetignis.event;

import net.lordofthetime.laminaetignis.item.custom.SpoolItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class onRightCLickEvent {

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
}
//

