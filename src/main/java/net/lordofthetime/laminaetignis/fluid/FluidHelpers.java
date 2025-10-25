package net.lordofthetime.laminaetignis.fluid;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

public class FluidHelpers {

    public static float[] getFluidColorRGBA(FluidStack fluid) {
        int color = IClientFluidTypeExtensions.of(fluid.getFluid()).getTintColor(fluid);
        return new float[] {
                ((color >> 16) & 0xFF) / 255f, // R
                ((color >> 8) & 0xFF) / 255f,  // G
                (color & 0xFF) / 255f,         // B
                ((color >> 24) & 0xFF) / 255f  // A
        };
    }
    public static TextureAtlasSprite getSprite(FluidStack fluid){
        return Minecraft.getInstance()
                .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(IClientFluidTypeExtensions.of(fluid.getFluid()).getStillTexture(fluid));
    }

}
