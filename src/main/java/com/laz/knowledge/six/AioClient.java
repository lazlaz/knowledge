package com.laz.knowledge.six;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class AioClient {
//	 private int port;
//	    private ByteBuffer send = ByteBuffer.allocate(2048);
//	    private ByteBuffer recive = ByteBuffer.allocate(2048);
//	    private boolean isDone = false;
//
//	    public AioClient(int port) {
//	        this.port = port;
//	    }
//
//	    public void send(String message) throws IOException {
//	        System.out.println("正在发送...");
//	        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
//	        client.connect(new InetSocketAddress(port), null, new CompletionHandler<Void, Void>() {
//
//	            @Override
//	            public void completed(Void result, Void attachment) {
//	            	System.out.println("连接成功");
//	                send.clear();
//	                send.put(message.getBytes());
//	                send.flip();
//	                recive.clear();
//	                // 写
//	                client.write(send, null, new CompletionHandler<Integer, Void>() {
//	                    @Override
//	                    public void completed(Integer result, Void attachment) {
//
//	                        // 读取结果
//	                        try {
//	                            Integer integer = client.read(recive).get();
//	                            System.out.println("size:"+integer+"> 收到结果："+new String(recive.array()));
//	                            isDone = true;
//	                        } catch (InterruptedException | ExecutionException e) {
//	                            e.printStackTrace();
//	                        }
//
//	                    }
//
//	                    @Override
//	                    public void failed(Throwable exc, Void attachment) {
//
//	                    }
//	                });
//
//	            }
//
//	            @Override
//	            public void failed(Throwable exc, Void attachment) {
//
//	            }
//	        });
//
//	        while (isDone == false) {
//	            try {
//	                Thread.sleep(1);// 这里起到wait的作用,不做点事情此处会被优化为wait 会一直挂起
//	            } catch (InterruptedException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//
//	    public static void main(String[] args) {
//	        try {
//	            new AioClient(8000).send("客户端的数据包");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		 ByteBuffer buff = ByteBuffer.allocate(1024);
	        Charset utf = Charset.forName("utf-8");
	        try {
	            AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open();
	            clientChannel.connect(new InetSocketAddress("127.0.0.1",8000)).get();
	            buff.clear();
	            clientChannel.read(buff).get();
	            buff.flip();
	            String content = utf.decode(buff).toString();
	            System.out.println("服务器信息："+content);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
}
