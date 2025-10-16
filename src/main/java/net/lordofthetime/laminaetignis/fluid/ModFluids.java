package net.lordofthetime.laminaetignis.fluid;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    // Registers
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, LaminaEtIgnis.MODID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, LaminaEtIgnis.MODID);

    //water textures, tint is applied separately
    private static final ResourceLocation WATER_STILL = ResourceLocation.tryBuild("minecraft", "block/water_still");
    private static final ResourceLocation WATER_FLOW = ResourceLocation.tryBuild("minecraft", "block/water_flow");
    private static final ResourceLocation WATER_OVERLAY = ResourceLocation.tryBuild("minecraft", "block/water_overlay");


    //Non-placeable fluid type registration
    public static final RegistryObject<FluidType> TANNIN_TYPE =
            FLUID_TYPES.register("tannin",
                    () -> new NonPlaceableFluidType(FluidType.Properties.create()
                            .lightLevel(2)
                            .viscosity(1500)
                            .temperature(300)
                            .lightLevel(0)
                            .canConvertToSource(false),
                            0xFF4B2E1E,
                            WATER_STILL,
                            WATER_FLOW,
                            WATER_OVERLAY
                    ));


    //Properties  and registration of tannin
    public static final ForgeFlowingFluid.Properties TANNIN_PROPERTIES =
            new ForgeFlowingFluid.Properties(
                    TANNIN_TYPE,
                    () -> ModFluids.TANNIN.get(),       // source
                    () -> ModFluids.TANNIN_FLOWING.get()      // flowing placeholder
            )
                    .bucket(() -> ModItems.TANNIN_BUCKET.get())
                    .block(() -> ModBlocks.TANNIN_BLOCK.get());

    public static final RegistryObject<FlowingFluid> TANNIN = FLUIDS.register("tannin",
            () -> new NonPlaceableFluid.Source(TANNIN_PROPERTIES));
    public static final RegistryObject<FlowingFluid> TANNIN_FLOWING = FLUIDS.register("tannin_flowing",
            () -> new NonPlaceableFluid.Flowing(TANNIN_PROPERTIES));

}
