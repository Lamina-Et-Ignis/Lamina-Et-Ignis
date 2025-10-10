package net.lordofthetime.laminaetignis.network;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.resources.ResourceLocation;
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
                PacketSyncFluid.class,
                PacketSyncFluid::encode,
                PacketSyncFluid::decode,
                PacketSyncFluid::handle);
    }

    public static void sendToTracking(PacketSyncFluid packet) {
        CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> packet.level().getChunkAt(packet.pos())), packet);
    }
}
