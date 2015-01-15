package com.net;

import com.net.packet.Packet;
import com.net.packet.PacketBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class NetworkGameDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("NetworkGameDecoder");
        if (in.isReadable()) {
            int opcode = in.readByte();
            if (opcode > 0) {
                ByteBuf payload = in.readBytes(in.readableBytes());
                Packet packet = new Packet(opcode,payload);
                out.add(packet);
            }
        }
    }
}
