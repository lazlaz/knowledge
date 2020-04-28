package com.laz.knowledge.fifteen;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

//WebSocketClient连接
public class WebSocketConnect {// websocket建立连接
	public void clientConnect() throws URISyntaxException {
		System.out.println("00");
		WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://192.168.5.4:8000/v2/rtasr/")) {
			// 打开链接
			@Override
			public void onOpen(ServerHandshake shake) {
				System.out.println("握手。。。");
				//this.send("xx");
			}

			// 这个方法自动接收服务器发过来的信息,直接在此处调用自己写的方法即可.本人将消息存入到session中，别处可以监听，然后取出再清空
			@Override
			public void onMessage(String msgString) {
				System.out.println("websocket返回消息" + msgString);
			}

			// 客户端发生错误,即将关闭!
			@Override
			public void onError(Exception e) {
				e.printStackTrace();
				System.out.println("发生错误已关闭");
			}

			// 关闭链接
			@Override
			public void onClose(int arg0, String arg1, boolean arg2) {
				System.out.println("链接已关闭");
			}
		};
		webSocketClient.connect();
		
	}
	public static void main(String[] args) throws URISyntaxException {
		new WebSocketConnect().clientConnect();;
	}
}