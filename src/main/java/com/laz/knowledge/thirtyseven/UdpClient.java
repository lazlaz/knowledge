package com.laz.knowledge.thirtyseven;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	public static void main(String[] args) throws IOException {
		DatagramSocket client = new DatagramSocket();
		InetAddress addr = InetAddress.getByName("192.168.5.9");
		int port = 5051;
		byte[] sendBuf;
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("请输要发送的内容：");
			String str = sc.nextLine();
			sendBuf = str.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, addr, port);
			client.send(sendPacket);
			if (str.endsWith("q") || str.endsWith("quit")) {
				break;
			}
		}
		client.close();
	}
}
