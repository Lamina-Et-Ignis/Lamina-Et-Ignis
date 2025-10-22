package net.lordofthetime.laminaetignis.item.custom.fluidcontainer;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

public class FluidCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    private final LazyOptional<IFluidHandlerItem> optional;
    private final FluidHandlerItemStack handler;

    public FluidCapabilityProvider(ItemStack stack, int capacity) {
        this.handler = new FluidHandlerItemStack(stack, capacity);
        this.optional = LazyOptional.of(() -> handler);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ForgeCapabilities.FLUID_HANDLER_ITEM ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return handler.getContainer().getOrCreateTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        handler.getContainer().setTag(nbt);
    }
}
