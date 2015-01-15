package com.net.packet.decoders;

import com.game.entity.player.Player;
import com.net.packet.Packet;
import com.net.packet.PacketBuilder;
import io.netty.buffer.ByteBuf;

@PacketOpcode({ 1 })
public class LoginDecoder implements PacketDecoder {

    @Override
    public void decode(Player player, Packet packet) {
        System.out.println("logindecoder");
        ByteBuf payload = packet.getPayload();
        int usernameLength = payload.readInt();
        String username = PacketBuilder.readString(payload.readBytes(usernameLength));
        String password = PacketBuilder.readString(payload.readBytes(payload.readableBytes()));
        System.out.println("usernameLength:"+usernameLength);
        System.out.println("username:" + username);
        System.out.println("password:" + password);
    }
}