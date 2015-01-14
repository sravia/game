package com.net;

import com.net.packet.Packet;
import com.net.packet.PacketBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class NetworkDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        /*
        if (avail > buf.readableBytes().RECEIVE_DATA_LIMIT) {
            return;
        }
        */
        System.out.println("decoder");
        int opcode = in.readByte();
        ByteBuf payload = in.readBytes(in.readableBytes());
        int usernameLength = payload.readInt();
        String username = PacketBuilder.readString(payload.readBytes(usernameLength));
        String password = PacketBuilder.readString(payload.readBytes(payload.readableBytes()));
        System.out.println("opcode:" + opcode);
        System.out.println("usernameLength:"+usernameLength);
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        // When logging in add player to ctx
        //ctx.write(player);
        Packet packet = new Packet(opcode,payload);
        out.add(packet);
    }
}
