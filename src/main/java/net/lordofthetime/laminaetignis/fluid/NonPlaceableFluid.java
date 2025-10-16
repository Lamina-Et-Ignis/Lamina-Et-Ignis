package net.lordofthetime.laminaetignis.fluid;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class NonPlaceableFluid extends ForgeFlowingFluid.Source {


    public NonPlaceableFluid(Properties properties, Item bucket) {
        super(properties);
    }


    @Override
    public boolean isSource(FluidState state) {
        return true; // treat all as source for logic purposes
    }


    //no logic for spread or tick
    @Override
    protected void spread(Level pLevel, BlockPos pPos, FluidState pState) {

    }
    @Override
    public void tick(Level level, BlockPos pos, FluidState state) {
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockGetter world, BlockPos pos, Fluid fluid, Direction dir) {
        return false;
    }
    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return null;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor worldIn, BlockPos pos, BlockState state) {

    }
}
