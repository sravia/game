package com.engine.task.tasks;

import com.engine.GameEngine;
import com.engine.World;
import com.engine.task.Task;
import com.game.entity.player.Player;
import com.net.packet.PacketBuilder;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class SessionLoginTask implements Task {

    private Player player;

    public SessionLoginTask(Player player) {
        this.player = player;
    }

    @Override
    public void execute(GameEngine context) {
        int returnCode = 1;
        PacketBuilder pb = new PacketBuilder(returnCode);

        player.getChannel().write(pb).addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture arg0) throws Exception {
                player.getActionSender().sendLogin();
            }
        });
    }

}
