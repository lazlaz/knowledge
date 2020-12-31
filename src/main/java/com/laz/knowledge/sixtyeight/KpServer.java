package com.laz.knowledge.sixtyeight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class KpServer {
	private Logger log = LoggerFactory.getLogger(KpServer.class);
	private int port;

    public KpServer(int port) {
        this.port = port;
    }

    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap().group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer());

        try {
            ChannelFuture future = server.bind(port).sync();
            log.info("start server");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server start fail",e);
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        KpServer server = new KpServer(7788);
        server.start();
    }
}
