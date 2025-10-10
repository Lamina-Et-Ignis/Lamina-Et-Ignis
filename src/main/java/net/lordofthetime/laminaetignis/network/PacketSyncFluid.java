package net.lordofthetime.laminaetignis.network;

import net.lordofthetime.laminaetignis.block.entity.AdvancedBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record PacketSyncFluid(BlockPos pos, FluidStack fluid, Level level) {

    public static void encode(PacketSyncFluid pkt, net.minecraft.network.FriendlyByteBuf buf) {
        buf.writeBlockPos(pkt.pos());
        buf.writeNbt(pkt.fluid().writeToNBT(new CompoundTag()));
    }

    public static PacketSyncFluid decode(net.minecraft.network.FriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        CompoundTag tag = buf.readNbt();
        FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag);
        // level will be set by handler on client
        return new PacketSyncFluid(pos, fluid, null);
    }

    public static void handle(PacketSyncFluid pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Level level = pkt.level() != null ? pkt.level() : net.minecraft.client.Minecraft.getInstance().level;
            if (level == null) return;
            if (level.getBlockEntity(pkt.pos()) instanceof AdvancedBarrelBlockEntity barrel) {
                barrel.getFluidTank().setFluid(pkt.fluid());
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
