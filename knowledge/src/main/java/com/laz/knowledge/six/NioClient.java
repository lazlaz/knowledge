package com.laz.knowledge.six;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
	//�ܵ�������
    private Selector selector;
    
    public NioClient init(String serverIp, int port) throws IOException{
        //��ȡsocketͨ��
        SocketChannel channel = SocketChannel.open();
        
        channel.configureBlocking(false);
        //���ͨ��������
        selector=Selector.open();
        
        //�ͻ������ӷ���������Ҫ����channel.finishConnect();����ʵ��������ӡ�
        channel.connect(new InetSocketAddress(serverIp, port));
        //Ϊ��ͨ��ע��SelectionKey.OP_CONNECT�¼�
        channel.register(selector, SelectionKey.OP_CONNECT);
        return this;
    }
    
    public void listen() throws IOException{
        System.out.println("�ͻ�������");
        //��ѯ����selector
        while(true){
            //ѡ��ע�����io�������¼�(��һ��ΪSelectionKey.OP_CONNECT)
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey key = ite.next();
                //ɾ����ѡ��key����ֹ�ظ�����
                ite.remove();
                if(key.isConnectable()){
                    SocketChannel channel=(SocketChannel)key.channel();
                    
                    //����������ӣ����������
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    
                    channel.configureBlocking(false);
                    //�������������Ϣ
                    channel.write(ByteBuffer.wrap(new String("send message to server.").getBytes()));
                    
                    //���ӳɹ���ע����շ�������Ϣ���¼�
                    channel.register(selector, SelectionKey.OP_READ);
                    System.out.println("�ͻ������ӳɹ�");
                }else if(key.isReadable()){ //�пɶ������¼���
                    SocketChannel channel = (SocketChannel)key.channel();
                    
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    channel.read(buffer);
                    byte[] data = buffer.array();
                    String message = new String(data);
                    
                    System.out.println("recevie message from server:, size:" + buffer.position() + " msg: " + message);
                    channel.close();
//                    ByteBuffer outbuffer = ByteBuffer.wrap(("client.".concat(msg)).getBytes());
//                    channel.write(outbuffer);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        new NioClient().init("127.0.0.1", 9981).listen();
    }
}
