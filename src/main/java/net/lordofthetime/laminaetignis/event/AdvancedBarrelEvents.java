package net.lordofthetime.laminaetignis.event;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.block.custom.AdvancedBarrelBlock;
import net.lordofthetime.laminaetignis.block.entity.AdvancedBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LaminaEtIgnis.MODID)
public class AdvancedBarrelEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        Player player = event.getEntity();
        BlockPos pos = event.getPos();
        ItemStack stack = event.getItemStack();

        if (level.getBlockState(pos).getBlock() instanceof AdvancedBarrelBlock && player.isCrouching()) {
            if (!(level.getBlockEntity(pos) instanceof AdvancedBarrelBlockEntity barrel)) return;

            // Try fluid interaction first
            if (FluidUtil.interactWithFluidHandler(player, event.getHand(), level, pos, event.getFace())) {
                barrel.updateClient();
                event.setCanceled(true);
                event.setCancellationResult(net.minecraft.world.InteractionResult.SUCCESS);
            }
            else{
                barrel.setSealed(!barrel.sealed);
                barrel.updateClient();
                event.setCanceled(true);
                event.setCancellationResult(net.minecraft.world.InteractionResult.SUCCESS);
            }
        }
    }
}