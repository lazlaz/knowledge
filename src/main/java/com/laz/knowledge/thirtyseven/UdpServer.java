package com.laz.knowledge.thirtyseven;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class UdpServer {
	public static void main(String[] args) throws IOException {
		DatagramSocket server = new DatagramSocket(5051);
		byte[] recvBuf  = new byte[100];
		DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
		while (true) {
			server.receive(recvPacket);
			String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
			System.out.println("收到:" + recvStr);
			if (recvStr.endsWith("q") || recvStr.endsWith("quit")) {
				break;
			}
		}
		server.close();
	}
}
