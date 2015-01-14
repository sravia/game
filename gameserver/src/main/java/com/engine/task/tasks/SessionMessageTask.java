package com.engine.task.tasks;

import com.engine.GameEngine;
import com.engine.task.Task;
import com.net.packet.Packet;
import com.net.packet.PacketManager;
import io.netty.channel.ChannelHandlerContext;

public class SessionMessageTask implements Task {

    private ChannelHandlerContext session;

    private Packet message;

    public SessionMessageTask(ChannelHandlerContext session, Packet message) {
        this.session = session;
        this.message = message;
    }

    @Override
    public void execute(GameEngine context) {
        PacketManager.getPacketManager().handle(session, message);
    }

}
