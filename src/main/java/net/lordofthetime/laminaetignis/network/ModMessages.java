package net.lordofthetime.laminaetignis.network;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(ResourceLocation.tryBuild(LaminaEtIgnis.MODID, "main_channel"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    private static int packetId = 0;
    private static int nextID() {
        return packetId++;
    }

    public static void register() {
        CHANNEL.registerMessage(nextID(),
                PacketSyncFluidClient.class,
                PacketSyncFluidClient::encode,
                PacketSyncFluidClient::decode,
                PacketSyncFluidClient::handle);

        CHANNEL.registerMessage(nextID(),
                PacketToggleSeal.class,
                PacketToggleSeal::toBytes,
                PacketToggleSeal::new,
                PacketToggleSeal::handle);

        CHANNEL.registerMessage(nextID(),
                PacketToggleSealClient.class,
                PacketToggleSealClient::toBytes,
                PacketToggleSealClient::new,
                PacketToggleSealClient::handle);
    }

    public static void sendToTracking(Level level, BlockPos pos, Object packet) {
        CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(pos)), packet);
    }
}
