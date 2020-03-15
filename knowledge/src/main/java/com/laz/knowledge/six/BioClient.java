package com.laz.knowledge.six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BioClient {
	final static String ADDRESS = "127.0.0.1";
    final static int PORT = 8765;

    public static void main(String[] args) {

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedReader reader = null;
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(System.in));
            //��������˷�������
            //ÿ����һ��out.println���ͻ��յ�һ�η���������Ӧ��
            out.println("���յ��ͻ��˵��������� " + reader.readLine());
            System.out.println("���յ���������������: " +  in.readLine());

//            out.println("���յ��ͻ��˵���������1111...");
//            System.out.println("Client: " +  in.readLine());
//
//            Thread.sleep(3000);
//            out.println("���յ��ͻ��˵���������...");
//            out.println("���յ��ͻ��˵���������1111...");
//            String response = in.readLine();
//            System.out.println("Client: " + response);

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
