package com.net.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class PacketBuilder {

    private int opcode;
    private final ByteBuf payload = Unpooled.buffer();

    public PacketBuilder() {
        this(-1);
    }

    public PacketBuilder(int opcode) {
        this.opcode = opcode;
    }

    public void putBytes(byte[] bytes) {
        payload.writeBytes(bytes);
    }

    public void putByte(byte b) {
        payload.writeByte(b);
    }

    public void putInt(int i) {
        payload.writeInt(i);
    }

    public void writeArray(ByteBuf buff, int... vals) {
        for (int i = 0; i < vals.length; i++) {
            buff.writeByte(vals[i]);
        }
    }

    public void writeArray(ByteBuf buff, byte... vals) {
        for (int i = 0; i < vals.length; i++) {
            buff.writeByte(vals[i]);
        }
    }

    public void writeS(ByteBuf buff, String value) {
        if(value == null)
            throw new RuntimeException("Value is null!");

        try {
            for (int i = 0; i < value.length(); i++) {
                buff.writeChar(value.charAt(i));
            }
            buff.writeChar('\000');
        }
        catch (Exception e) {
            System.out.println("Failed writing string!"+e);
        }
    }

    public void putString(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            payload.writeByte((byte) c);
        }
    }

    public static String readString(ByteBuf buffer) {
        StringBuilder builder = new StringBuilder();
        while (buffer.isReadable()) {
            builder.append((char) buffer.readUnsignedByte());
        }
        return builder.toString();
    }

    public Packet toPacket(){
        return new Packet(opcode,payload);
    }
}
