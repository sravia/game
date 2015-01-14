package com.net.packet.decoders;

import com.game.entity.player.Player;
import com.net.packet.Packet;

import java.util.logging.Logger;

@PacketOpcode({ 0 })
public class DefaultPacketDecoder implements PacketDecoder {

	private static final Logger logger = Logger.getLogger(DefaultPacketDecoder.class.getName());

	@Override
	public void decode(Player player, Packet packet) {
		logger.warning("Unhandled Packet : [opcode=" + packet.getOpcode() + " length=" + packet.getLength() + "]");
	}

}
