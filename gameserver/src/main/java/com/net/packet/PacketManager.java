package com.net.packet;

import com.game.entity.player.Player;
import com.net.packet.decoders.DefaultPacketDecoder;
import com.net.packet.decoders.PacketDecoder;
import io.netty.channel.ChannelHandlerContext;

public class PacketManager {

    private static final PacketManager INSTANCE = new PacketManager();

    public static PacketManager getPacketManager() {
        return INSTANCE;
    }

    private PacketDecoder[] packetDecoders = new PacketDecoder[256];

    public PacketManager() {
        final PacketDecoder defaultDecoder = new DefaultPacketDecoder();
        for (int i = 0; i < packetDecoders.length; i++) {
            if (packetDecoders[i] == null) {
                packetDecoders[i] = defaultDecoder;
            }
        }
    }

    public void bind(int id, PacketDecoder decoder) {
        packetDecoders[id] = decoder;
    }

    public void handle(ChannelHandlerContext session, Packet packet) {
        try {
            packetDecoders[packet.getOpcode()].decode((Player) session.read(), packet);
        } catch (Exception ex) {
            System.out.println("Exception handling packet."+ ex);
            session.channel().close();
        }
    }

    /*public void handle(player player, Packet packet) {
        try {
            packetHandlers[packet.getOpcode()].handle(player, packet);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception handling packet.", ex);
            player.getSession().close();
        }
    }*/

}
