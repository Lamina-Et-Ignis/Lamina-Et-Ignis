package net.lordofthetime.laminaetignis.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.lordofthetime.laminaetignis.block.custom.AdvancedBarrelBlock;
import net.lordofthetime.laminaetignis.block.entity.AdvancedBarrelBlockEntity;
import net.lordofthetime.laminaetignis.fluid.FluidHelpers;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Matrix4f;

public class AdvancedBarrelBlockEntityRenderer implements BlockEntityRenderer<AdvancedBarrelBlockEntity> {
    public AdvancedBarrelBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void render(AdvancedBarrelBlockEntity pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        FluidStack fluid = pBlockEntity.getFluidTank().getFluid();
        if(!fluid.isEmpty() && !pBlockEntity.getBlockState().getValue(AdvancedBarrelBlock.SEALED)){
            float minX = 2.5f / 16f;
            float maxX = 13.5f / 16f;
            float minZ = 2.5f / 16f;
            float maxZ = 13.5f / 16f;
            float minY = 1f / 16f;
            float maxY = 14f / 16f;
            TextureAtlasSprite still = FluidHelpers.getSprite(fluid);

            //percentage filled (between 0 and 1)
            float fill = (float) fluid.getAmount() / (float) pBlockEntity.getFluidTank().getCapacity();
            // heigh to render the fluid at (above minY to maxY fill) by the full percentage
            float fluidY = minY + (maxY - minY) * fill;
            float[] rgba = FluidHelpers.getFluidColorRGBA(fluid);
            Matrix4f matrix = poseStack.last().pose();
            VertexConsumer builder = pBuffer.getBuffer(RenderType.translucentNoCrumbling());

            // Render the top face of a fluid in the block.
            // - `matrix` provides the transformation for the vertices.
            // - `still` provides the texture coordinates for the fluid's sprite.
            // - `pPackedOverlay` and `pPackedLight` handle overlay and lighting.
            // - `.normal(0f, 1f, 0f)` sets the face's normal vector pointing upwards.
            // Each `builder.vertex(...).endVertex()` call defines one corner of the quad.
            builder.vertex(matrix, minX, fluidY, maxZ).color(rgba[0], rgba[1], rgba[2], rgba[3])
                    .uv(still.getU0(), still.getV0()).overlayCoords(pPackedOverlay).uv2(pPackedLight).normal(0f, 1f, 0f).endVertex();
            builder.vertex(matrix, maxX, fluidY, maxZ).color(rgba[0], rgba[1], rgba[2], rgba[3])
                    .uv(still.getU1(), still.getV0()).overlayCoords(pPackedOverlay).uv2(pPackedLight).normal(0f, 1f, 0f).endVertex();
            builder.vertex(matrix, maxX, fluidY, minZ).color(rgba[0], rgba[1], rgba[2], rgba[3])
                    .uv(still.getU1(), still.getV1()).overlayCoords(pPackedOverlay).uv2(pPackedLight).normal(0f, 1f, 0f).endVertex();
            builder.vertex(matrix, minX, fluidY, minZ).color(rgba[0], rgba[1], rgba[2], rgba[3])
                    .uv(still.getU0(), still.getV1()).overlayCoords(pPackedOverlay).uv2(pPackedLight).normal(0f, 1f, 0f).endVertex();

        }
    }

    private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK,pos);
        int sLight = level.getBrightness(LightLayer.SKY,pos);
        return LightTexture.pack(bLight,sLight);
    }
}
