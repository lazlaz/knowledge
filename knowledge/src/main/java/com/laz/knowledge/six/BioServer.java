package com.laz.knowledge.six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ServerHandler implements Runnable {
	private Socket socket;
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		 BufferedReader in = null;
	        PrintWriter out = null;
	        BufferedReader reader = null;
	        try {
	            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	            out = new PrintWriter(this.socket.getOutputStream(), true);
	            reader = new BufferedReader(new InputStreamReader(System.in));
	            String body = null;
	            while (true) {
	                body = in.readLine();
	                if (body == null) break;
	                System.out.println("Server :" + body);
	                //out.println("�������˻������Ӧ����.");
	                out.println(reader.readLine());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (out != null) {
	                try {
	                    out.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (socket != null) {
	                try {
	                    socket.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            socket = null;
	        }
		
	}
	
}

public class BioServer {
	 final static int PROT = 8765;

	    public static void main(String[] args) {

	        ServerSocket server = null;
	        try {
	            server = new ServerSocket(PROT);
	            System.out.println(" server start .. ");
	            //��������
	            while (true) {//����Ӧ��ѭ����ʹ�ÿ��Խ��ܶ���ͻ��˵�����
	                Socket socket = server.accept();
	                //�½�һ���߳�ִ�пͻ��˵�����
	                new Thread(new ServerHandler(socket)).start();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (server != null) {
	                try {
	                    server.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            server = null;
	        }
	    }
}
