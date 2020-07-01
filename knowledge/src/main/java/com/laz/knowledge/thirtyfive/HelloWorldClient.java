package com.laz.knowledge.thirtyfive;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class HelloWorldClient {

	private int port;
	private String address;

	public HelloWorldClient(int port, String address) {
		this.port = port;
		this.address = address;
	}

	public void start() {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>(){

					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						System.out.println("--");
						ChannelPipeline pipeline = socketChannel.pipeline();
						pipeline.addLast(new LineBasedFrameDecoder(2048));     //字节解码器 ,其中2048是规定一行数据最大的字节数。  用于解决拆包问题
						pipeline.addLast("decoder", new StringDecoder());// 字符串解码和编码
						pipeline.addLast("encoder", new StringEncoder());
						pipeline.addLast("handler", new ChannelInboundHandlerAdapter(){
							
							@Override
							public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
								System.out.println("客户端收到消息:[" + msg + "]");
							}
						});//自定义handler
					}
				});

		try {
			ChannelFuture future = bootstrap.connect(address,port).sync();
			Channel channel = future.channel();
			channel.writeAndFlush("我是客户端,地址:" + channel.remoteAddress());
			channel.closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		HelloWorldClient client = new HelloWorldClient(8888, "127.0.0.1");
		client.start();
	}
}
