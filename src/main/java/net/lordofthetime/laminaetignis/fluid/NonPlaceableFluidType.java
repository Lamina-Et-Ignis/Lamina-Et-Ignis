package net.lordofthetime.laminaetignis.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class NonPlaceableFluidType extends FluidType {

    private final int tintColor;
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final  ResourceLocation overlayTexture;


    public NonPlaceableFluidType(Properties properties, int tintColor, ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlayTexture) {
        super(properties);
        this.tintColor = tintColor;
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.overlayTexture = overlayTexture;
    }

    @Override
    public boolean canConvertToSource(FluidStack stack) {
        return false;
    }
    @Override
    public boolean isVaporizedOnPlacement(Level level, BlockPos pos, FluidStack stack) {
        return false;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public int getTintColor()
            {
                return tintColor;
            }
            @Override
            public ResourceLocation getStillTexture()
            {
                return stillTexture;
            }

            @Override
            public ResourceLocation getFlowingTexture()
            {
                return flowingTexture;
            }

            @Override
            @Nullable
            public ResourceLocation getOverlayTexture()
            {
                return overlayTexture;
            }
        });
    }
}
