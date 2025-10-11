package net.lordofthetime.laminaetignis.network;

import net.lordofthetime.laminaetignis.block.entity.AdvancedBarrelBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketToggleSealClient {
    private final BlockPos pos;
    private final boolean sealed;

    public PacketToggleSealClient(BlockPos pos, boolean sealed) {
        this.pos = pos;
        this.sealed = sealed;
    }

    public PacketToggleSealClient(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.sealed = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeBoolean(sealed);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.level == null) return;
            BlockEntity be = mc.level.getBlockEntity(pos);
            if (be instanceof AdvancedBarrelBlockEntity barrel) {
                barrel.sealed = sealed;
            }
        });
        context.get().setPacketHandled(true);
    }
}
