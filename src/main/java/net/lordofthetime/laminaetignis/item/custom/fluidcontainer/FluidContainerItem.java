package net.lordofthetime.laminaetignis.item.custom.fluidcontainer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FluidContainerItem extends Item {

    private final int capacity;

    public FluidContainerItem(Properties pProperties, int capacity) {
        super(pProperties);
        this.capacity = capacity;
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
            return new FluidCapabilityProvider(stack,capacity);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handler ->{
            FluidStack fluid = handler.getFluidInTank(0);
            if(!fluid.isEmpty()){
                pTooltipComponents.add(Component.literal(
                        fluid.getAmount() + " / " + handler.getTankCapacity(0) + " mB"));
            }
            else{
                pTooltipComponents.add(Component.literal(
                        "0 / " + handler.getTankCapacity(0) + " mB"));
            }
        });

        super.appendHoverText(stack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Component getName(ItemStack pStack) {
        IFluidHandlerItem handler = pStack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null);
        FluidStack fluid = handler.getFluidInTank(0);
        if(!fluid.isEmpty()){
            // copies fluid name and puts it before normal item name
            MutableComponent prefix = Component.literal(fluid.getDisplayName().getString() + " ");
            return prefix.append(super.getName(pStack));
        }
        return super.getName(pStack);
    }
}
