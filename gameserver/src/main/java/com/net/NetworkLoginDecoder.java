package com.net;

import com.engine.World;
import com.game.entity.player.Player;
import com.net.packet.Packet;
import com.net.packet.PacketBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class NetworkLoginDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("NetworkLoginDecoder");
        int returnCode = 1;
        int opcode = in.readByte();
        if(opcode == 1){
            ByteBuf payload = in.readBytes(in.readableBytes());
            int usernameLength = payload.readInt();
            String username = PacketBuilder.readString(payload.readBytes(usernameLength));
            String password = PacketBuilder.readString(payload.readBytes(payload.readableBytes()));

            Player player = new Player(ctx.channel(),username,password);

            if (!World.getWorld().getPlayers().add(player)) {
                returnCode = 2; // Could not register player ( world is full )
                System.out.println("Could not register player : " + player + " [world full]");
            }
            ctx.channel().pipeline().replace("decoder", "decoder", new NetworkGameDecoder());
            World.getWorld().load(player);
        }
    }
}
