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
//	        System.out.println("���ڷ���...");
//	        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
//	        client.connect(new InetSocketAddress(port), null, new CompletionHandler<Void, Void>() {
//
//	            @Override
//	            public void completed(Void result, Void attachment) {
//	            	System.out.println("���ӳɹ�");
//	                send.clear();
//	                send.put(message.getBytes());
//	                send.flip();
//	                recive.clear();
//	                // д
//	                client.write(send, null, new CompletionHandler<Integer, Void>() {
//	                    @Override
//	                    public void completed(Integer result, Void attachment) {
//
//	                        // ��ȡ���
//	                        try {
//	                            Integer integer = client.read(recive).get();
//	                            System.out.println("size:"+integer+"> �յ������"+new String(recive.array()));
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
//	                Thread.sleep(1);// ������wait������,����������˴��ᱻ�Ż�Ϊwait ��һֱ����
//	            } catch (InterruptedException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//
//	    public static void main(String[] args) {
//	        try {
//	            new AioClient(8000).send("�ͻ��˵����ݰ�");
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
	            System.out.println("��������Ϣ��"+content);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
}
