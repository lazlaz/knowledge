package com.laz.knowledge.fifteen;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

//WebSocketClient����
public class WebSocketConnect {// websocket��������
	public void clientConnect() throws URISyntaxException {
		System.out.println("00");
		WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://192.168.5.4:8000/v2/rtasr/")) {
			// ������
			@Override
			public void onOpen(ServerHandshake shake) {
				System.out.println("���֡�����");
				//this.send("xx");
			}

			// ��������Զ����շ���������������Ϣ,ֱ���ڴ˴������Լ�д�ķ�������.���˽���Ϣ���뵽session�У��𴦿��Լ�����Ȼ��ȡ�������
			@Override
			public void onMessage(String msgString) {
				System.out.println("websocket������Ϣ" + msgString);
			}

			// �ͻ��˷�������,�����ر�!
			@Override
			public void onError(Exception e) {
				e.printStackTrace();
				System.out.println("���������ѹر�");
			}

			// �ر�����
			@Override
			public void onClose(int arg0, String arg1, boolean arg2) {
				System.out.println("�����ѹر�");
			}
		};
		webSocketClient.connect();
		
	}
	public static void main(String[] args) throws URISyntaxException {
		new WebSocketConnect().clientConnect();;
	}
}