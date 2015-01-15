package com.engine.task.tasks;

import com.engine.GameEngine;
import com.engine.task.Task;
import com.net.packet.Packet;
import com.net.packet.PacketManager;
import io.netty.channel.ChannelHandlerContext;

public class SessionMessageTask implements Task {

    private ChannelHandlerContext ctx;
    private Packet packet;

    public SessionMessageTask(ChannelHandlerContext ctx, Packet packet) {
        this.ctx = ctx;
        this.packet = packet;
    }

    @Override
    public void execute(GameEngine context) {
        System.out.println("executing");
        PacketManager.getPacketManager().handle(ctx, packet);
    }

}
