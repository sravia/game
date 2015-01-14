package com.net.packet.decoders;

import com.game.entity.player.Player;
import com.net.packet.Packet;

public interface PacketDecoder {

    public void decode(Player player, Packet packet);

}
