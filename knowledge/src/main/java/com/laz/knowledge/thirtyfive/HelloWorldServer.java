package com.laz.knowledge.thirtyfive;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class HelloWorldServer {
private int port;
	
	public HelloWorldServer(int port) {
		this.port = port;
	}
	
	public void start(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();//创建父子线程组
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		ServerBootstrap server = new ServerBootstrap();
		server.group(bossGroup, workGroup)
			  .channel(NioServerSocketChannel.class)//指定处理客户端的通道
			  .childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					ChannelPipeline pipeline = socketChannel.pipeline();
					
					pipeline.addLast("decoder", new StringDecoder());// 字符串解码和编码
					pipeline.addLast("encoder", new StringEncoder());
					pipeline.addLast("handler", new ChannelInboundHandlerAdapter(){
						
						@Override
						public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
							System.out.println("服务端收到消息:[" + msg + "]");
							StringBuffer sb = new StringBuffer();
							for (int i=0;i<50;i++) {
								sb.append("我是一个服务端消息");
							}
							sb.append("\n");
							ctx.writeAndFlush(sb.toString());
						}
					});//自定义handler
				}
			  });//通道初始化
		try {
			ChannelFuture future = server.bind(port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		HelloWorldServer server = new HelloWorldServer(8888);
		server.start();
	}
}
