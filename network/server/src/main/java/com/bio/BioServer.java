package com.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) throws IOException {
        new BioServer().service();
    }

    ServerSocket server = null;
    Socket sk = null;
    BufferedReader rdr = null;
    PrintWriter wtr = null;

    public BioServer() throws IOException {
        server = new ServerSocket(8080);
    }

    public void service() throws IOException {

        while (true) {
            System.out.println("Listenning...");
            sk = server.accept();
            ServerThread th = new ServerThread(sk);
            th.start();

        }
    }

    class ServerThread extends Thread {

        Socket sk = null;

        public ServerThread(Socket sk) {
            this.sk = sk;
        }

        public void run() {
            try {
                wtr = new PrintWriter(sk.getOutputStream());
                rdr = new BufferedReader(new InputStreamReader(sk
                        .getInputStream()));
                String line = rdr.readLine();
                System.out.println("从客户端来的信息：" + line);
//              特别，下面这句得加上     “\n”,
//                wtr.println("你好，服务器已经收到您的信息！'" + line + "'\n");
//                wtr.flush();
//                System.out.println("已经返回给客户端！");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
