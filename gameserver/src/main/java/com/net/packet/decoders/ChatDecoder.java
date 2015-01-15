package com.net.packet.decoders;

import com.game.entity.player.Player;
import com.net.packet.Packet;

@PacketOpcode({ 2 })
public class ChatDecoder implements PacketDecoder {

    @Override
    public void decode(Player player, Packet packet) {

    }
}