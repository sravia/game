package com.net;

import com.net.packet.PacketManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public final class Network extends ChannelInboundHandlerAdapter {

    private ServerBootstrap bootstrap;
    private ChannelFuture channelFuture;
    private int port;

    public Network(int port) throws Exception {
        this.port = port;
        if (bootstrap == null) {
            load();
        }
        PacketManager.loadPacketDecoders();
    }

    public void load() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decoder", new NetworkLoginDecoder());
                            ch.pipeline().addLast("encoder", new NetworkEncoder());
                            ch.pipeline().addLast(new NetworkHandler());
                        }
                    })
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (channelFuture != null) {
            channelFuture.channel().close();
        }
    }

    public void run() {
        try {
            channelFuture = bootstrap.bind(port);
            System.out.println("Network started on[Port: " + port + "]");
            channelFuture.sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println("Network thread fail!" + e);
        } finally {
            bootstrap.group().shutdownGracefully();
            bootstrap.childGroup().shutdownGracefully();
            bootstrap = null;
            channelFuture = null;
        }
    }

}
