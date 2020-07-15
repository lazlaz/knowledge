package com.laz.knowledge.six;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
	 //ͨ��������
    private Selector selector;
    
    //��ȡһ��ServerSocketͨ��������ʼ��ͨ��
    public NioServer init(int port) throws IOException{
        //��ȡһ��ServerSocketͨ��
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        //��ȡͨ��������
        selector=Selector.open();
        //��ͨ����������ͨ���󶨣���Ϊ��ͨ��ע��SelectionKey.OP_ACCEPT�¼���
        //ֻ�е����¼�����ʱ��Selector.select()�᷵�أ�����һֱ������
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("---");
        return this;
    }
    
    public void listen() throws IOException{
        System.out.println("�������������ɹ�");
        
        //ʹ����ѯ����selector
        while(true){
            //����ע����¼�����ʱ���������أ�����������
        	System.out.println("�ȴ�");
            selector.select();
            System.out.println("��ȡ�¼�");
            //��ȡselector�еĵ�������ѡ����Ϊע����¼�
            Iterator<SelectionKey> ite=selector.selectedKeys().iterator();
            
            while(ite.hasNext()){
                SelectionKey key = ite.next();
                //ɾ����ѡkey����ֹ�ظ�����
                ite.remove();
                //�ͻ������������¼�
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    //��ÿͻ�������ͨ��
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    //��ͻ��˷���Ϣ
                   // channel.write(ByteBuffer.wrap(new String("send message to client").getBytes()));
                    //����ͻ������ӳɹ���Ϊ�ͻ���ͨ��ע��SelectionKey.OP_READ�¼���
                    channel.register(selector, SelectionKey.OP_READ);
                    
                    System.out.println("�ͻ������������¼�");
                }else if(key.isReadable()){//�пɶ������¼�
                    //��ȡ�ͻ��˴������ݿɶ�ȡ��Ϣͨ����
                    SocketChannel channel = (SocketChannel)key.channel();
                    //������ȡ���ݻ�����
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    int read = channel.read(buffer);
                    byte[] data = buffer.array();
                    String message = new String(data);
                    
                    System.out.println("receive message from client, size:" + buffer.position() + " msg: " + message);
//                    ByteBuffer outbuffer = ByteBuffer.wrap(("server.".concat(msg)).getBytes());
//                    channel.write(outbuffer);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        new NioServer().init(9981).listen();
    }
}
