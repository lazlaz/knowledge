package com.laz.knowledge.six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BioClient {
	final static String ADDRESS = "127.0.0.1";
    final static int PORT = 8765;

    public static void main(String[] args) throws InterruptedException {

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedReader reader = null;
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(System.in));
            //向服务器端发送数据
            //每进行一次out.println，就会收到一次服务器的响应。
            out.println("接收到客户端的请求数据 " + reader.readLine());

           

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
        Thread.sleep(30000);
    }
}
