package com.net.packet;


import io.netty.buffer.ByteBuf;

public class Packet {

    private int opcode;
    private int length;
    private ByteBuf payload;

    public Packet(int opcode, ByteBuf payload) {
        this.opcode = opcode;
        this.payload = payload;
        this.length = payload.readableBytes();
    }

    public int getOpcode() {
        return opcode;
    }

    public ByteBuf getPayload() {
        return payload;
    }

    public int getLength() {
        return length;
    }



}
