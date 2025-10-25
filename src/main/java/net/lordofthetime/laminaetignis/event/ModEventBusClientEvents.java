package net.lordofthetime.laminaetignis.event;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.block.entity.ModBlockEntities;
import net.lordofthetime.laminaetignis.block.entity.renderer.AdvancedBarrelBlockEntityRenderer;
import net.lordofthetime.laminaetignis.block.entity.renderer.DryingRackBlockEntityRendered;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LaminaEtIgnis.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.DRYING_RACK_BLOCK_ENTITY.get(), DryingRackBlockEntityRendered::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ADVANCED_BARREL_BLOCK_ENTITY.get(), AdvancedBarrelBlockEntityRenderer::new);
    }
}
