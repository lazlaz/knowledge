package com.laz.knowledge.six;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.sun.corba.se.spi.activation.Server;

public class AioServer {
//	  static int PORT = 8000;  
//	    static int BUFFER_SIZE = 1024;  
//	    static String CHARSET = "utf-8"; //Ĭ�ϱ���  
//	    static CharsetDecoder decoder = Charset.forName(CHARSET).newDecoder(); //����  
//	  
//	    int port;  
//	    //ByteBuffer buffer;  
//	    AsynchronousServerSocketChannel serverChannel;  
//	  
//	    public AioServer(int port) throws IOException {  
//	        this.port = port;  
//	        //this.buffer = ByteBuffer.allocate(BUFFER_SIZE);  
//	        this.decoder = Charset.forName(CHARSET).newDecoder();  
//	    }  
//	  
//	    private void listen() throws Exception {  
//	  
//	        //��һ������ͨ��  
//	        //�󶨷���˿�  
//	        this.serverChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port), 100);  
//	        this.serverChannel.accept(this, new AcceptHandler());  
//	  
//	        Thread t = new Thread(new Runnable() {  
//	            @Override  
//	            public void run() {  
//	                while (true) {  
//	                  //  System.out.println("������...");  
//	                    try {  
//	                        Thread.sleep(10000);  
//	                    } catch (InterruptedException e) {  
//	                        e.printStackTrace();  
//	                    }  
//	                }  
//	            }  
//	        });  
//	        t.start();  
//	  
//	    }  
//	  
//	  
//	    /** 
//	     * accept��һ������ʱ�Ļص� 
//	     */  
//	    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {  
//	        @Override  
//	        public void completed(final AsynchronousSocketChannel client, AioServer attachment) {  
//	            try {  
//	                System.out.println("Զ�̵�ַ��" + client.getRemoteAddress());  
//	                //tcp�������  
//	                client.setOption(StandardSocketOptions.TCP_NODELAY, true);  
//	                client.setOption(StandardSocketOptions.SO_SNDBUF, 1024);  
//	                client.setOption(StandardSocketOptions.SO_RCVBUF, 1024);  
//	  
//	                if (client.isOpen()) {  
//	                    System.out.println("client.isOpen��" + client.getRemoteAddress());  
//	                    final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);  
//	                    buffer.clear();  
//	                    client.read(buffer, client, new ReadHandler(buffer));  
//	                }  
//	  
//	            } catch (Exception e) {  
//	                e.printStackTrace();  
//	            } finally {  
//	                attachment.serverChannel.accept(attachment, this);// �����µ����󣬵ݹ���á�  
//	            }  
//	        }  
//	  
//	        @Override  
//	        public void failed(Throwable exc, AioServer attachment) {  
//	            try {  
//	                exc.printStackTrace();  
//	            } finally {  
//	                attachment.serverChannel.accept(attachment, this);// �����µ����󣬵ݹ���á�  
//	            }  
//	        }  
//	    }  
//	  
//	    /** 
//	     * Read���������ݵĻص� 
//	     */  
//	    private class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {  
//	  
//	        private ByteBuffer buffer;  
//	  
//	        public ReadHandler(ByteBuffer buffer) {  
//	            this.buffer = buffer;  
//	        }  
//	  
//	        @Override  
//	        public void completed(Integer result, AsynchronousSocketChannel attachment) {  
//	            try {  
//	                if (result < 0) {// �ͻ��˹ر�������  
//	                	AioServer.close(attachment);  
//	                } else if (result == 0) {  
//	                    System.out.println("������"); // ���������  
//	                } else {  
//	                    // ��ȡ���󣬴���ͻ��˷��͵�����  
//	                    buffer.flip();  
//	                    CharBuffer charBuffer = AioServer.decoder.decode(buffer);  
//	                    System.out.println(charBuffer.toString()); //��������  
//	  
//	                    //��Ӧ��������������Ӧ���  
//	                    buffer.clear();  
//	                    String res = "HTTP/1.1 200 OK" + "\r\n\r\n" + "hellworld";  
//	                    buffer = ByteBuffer.wrap(res.getBytes());  
//	                    attachment.write(buffer, attachment, new WriteHandler(buffer));//Response����Ӧ��  
//	                }  
//	            } catch (Exception e) {  
//	                e.printStackTrace();  
//	            }  
//	        }  
//	  
//	        @Override  
//	        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {  
//	            exc.printStackTrace();  
//	            AioServer.close(attachment);  
//	        }  
//	    }  
//	  
//	    /** 
//	     * Write��Ӧ������Ļص� 
//	     */  
//	    private class WriteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {  
//	        private ByteBuffer buffer;  
//	  
//	        public WriteHandler(ByteBuffer buffer) {  
//	            this.buffer = buffer;  
//	        }  
//	  
//	        @Override  
//	        public void completed(Integer result, AsynchronousSocketChannel attachment) {  
//	            buffer.clear();  
//	            AioServer.close(attachment);  
//	        }  
//	  
//	        @Override  
//	        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {  
//	            exc.printStackTrace();  
//	            AioServer.close(attachment);  
//	        }  
//	    }  
//	  
//	    public static void main(String[] args) {  
//	        try {  
//	            System.out.println("������������...");  
//	            AioServer server = new AioServer(PORT);  
//	            server.listen();  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        }  
//	    }  
//	  
//	    private static void close(AsynchronousSocketChannel client) {  
//	        try {  
//	            client.close();  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        }  
//	    }  
	 public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
	        try {
	            AsynchronousServerSocketChannel serverChannel =  AsynchronousServerSocketChannel.open();
	            serverChannel.bind(new InetSocketAddress(8000));
	            while (true) {
	                Future<AsynchronousSocketChannel> future = serverChannel.accept();
	                System.out.println("����");
	                //��ȡ���ӳɹ�֮���AsynchronousSocketChannel
	                AsynchronousSocketChannel socketChannel = future.get();
	                socketChannel.write(ByteBuffer.wrap("��ã�����AIO����".getBytes("utf-8"))).get();
	            }
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
	    }
}
