package com.net.packet;

import com.game.entity.player.Player;
import com.net.packet.decoders.DefaultPacketDecoder;
import com.net.packet.decoders.PacketDecoder;
import com.net.packet.decoders.PacketOpcode;
import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.util.Arrays;

public class PacketManager {

    private static final PacketManager INSTANCE = new PacketManager();

    public static PacketManager getPacketManager() {
        return INSTANCE;
    }

    private static PacketDecoder[] packetDecoders = new PacketDecoder[256];

    public PacketManager() {
        /*PacketDecoder defaultDecoder = new DefaultPacketDecoder();
        for (int i = 0; i < packetDecoders.length; i++) {
            if (packetDecoders[i] == null) {
                packetDecoders[i] = defaultDecoder;
            }
        }*/
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

    public static void loadPacketDecoders() throws Exception {
        File[] files = new File("./src/main/java/com/net/packet/decoders/").listFiles();
        for (File file : files) {
            Class<?> c = Class.forName("com.net.packet.decoders." + file.getName().replaceAll(
                    ".java", ""));

            if (PacketDecoder.class.isAssignableFrom(c) && !c.isInterface()) {
                PacketDecoder packet = (PacketDecoder) c.newInstance();
                if (packet.getClass().getAnnotation(PacketOpcode.class) == null) {
                    throw new Exception("PacketOpcode missing:" + packet);
                }

                int packetOpcodes[] = packet.getClass().getAnnotation(
                        PacketOpcode.class).value();

                for (int opcode : packetOpcodes) {
                    PacketManager.getPacketManager().bind(opcode, packet);
                    System.out.println("Bound " + packet.toString() + " to opcode : " + opcode);
                }
            }
        }
    }

}
