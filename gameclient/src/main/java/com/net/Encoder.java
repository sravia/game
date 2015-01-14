package com.net;

import com.net.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.Arrays;
import java.util.List;

public class Encoder extends MessageToMessageEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) throws Exception {
        System.out.println("encoding");
        int headerLength = 3;
        int payloadLength = packet.getLength();

        ByteBuf buffer = Unpooled.buffer(headerLength + payloadLength);
        buffer.writeByte(packet.getOpcode());
        buffer.writeBytes(packet.getPayload());
        System.out.println("Opcode:" + packet.getOpcode());
        System.out.println("Payload:"+ Arrays.toString(packet.getPayload().array()));
        out.add(buffer);
    }
}
