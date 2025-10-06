package net.lordofthetime.laminaetignis.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.lordofthetime.laminaetignis.block.custom.DryingRackBlock;
import net.lordofthetime.laminaetignis.block.entity.DryingRackBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
            Direction dir = pBlockEntity.getBlockState().getValue(DryingRackBlock.FACING);


            float scaleX;
            float scaleZ;

            float positonX;
            float positionZ;

            switch (dir) {
                case NORTH, SOUTH -> {
                    scaleX = 0.5f;
                    scaleZ = 0.7f;
                    positonX = 1f;
                    positionZ = 0.7f;
                }
                default -> { //EAST, WEST
                    scaleX = 0.7f;
                    scaleZ = 0.5f;
                    positonX = 0.7f;
                    positionZ = 1f;
                }
            }
            pPoseStack.pushPose();

            pPoseStack.scale(scaleX, 0.5f, scaleZ);
            pPoseStack.translate(positonX,1.15f,positionZ);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(270));
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(
                    switch (dir) {
                case NORTH -> 180f;
                case WEST  -> 90f;
                case EAST  -> 270f;
                default    -> 0f; //south
            }));

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
