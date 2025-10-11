package net.lordofthetime.laminaetignis.network;

import net.lordofthetime.laminaetignis.block.entity.AdvancedBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketToggleSeal {
    private final BlockPos pos;

    // Constructor used for sending
    public PacketToggleSeal(BlockPos pos) {
        this.pos = pos;
    }

    // Constructor used when receiving
    public PacketToggleSeal(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    // Encode data (client → server)
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    // Handle packet (server side)
    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player == null) return;

            BlockEntity be = player.level().getBlockEntity(pos);
            if (be instanceof AdvancedBarrelBlockEntity barrel) {
                barrel.setSealed(!barrel.sealed);
                barrel.updateClient();
            }
        });
        context.get().setPacketHandled(true);
    }
}

