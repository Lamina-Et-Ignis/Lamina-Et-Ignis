package net.lordofthetime.laminaetignis.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.lordofthetime.laminaetignis.block.entity.DryingRackBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class DryingRackBlockEntityRendered implements BlockEntityRenderer<DryingRackBlockEntity> {

    public DryingRackBlockEntityRendered(BlockEntityRendererProvider.Context context){

    }

    @Override
    public void render(DryingRackBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {



        ItemStack itemStack = pBlockEntity.getStack();

        if(!itemStack.is(ItemStack.EMPTY.getItem())){
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            pPoseStack.pushPose();
            pPoseStack.translate(0.5f,0.625f,0.5f);
            pPoseStack.scale(0.5f,0.5f,1f);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(270));

            itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(),pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, pPoseStack,pBuffer,pBlockEntity.getLevel(),1);
            pPoseStack.popPose();
        }
    }

    private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK,pos);
        int sLight = level.getBrightness(LightLayer.SKY,pos);
        return LightTexture.pack(bLight,sLight);
    }
}
