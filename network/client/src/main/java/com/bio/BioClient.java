package com.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BioClient {

    public static void main(String [] args)
    {
        new BioClient().connect();
    }
    Socket sk = null;
    BufferedReader reader = null;
    PrintWriter wtr = null;
    BufferedReader keyin = null;

    public BioClient()
    {

        try
        {
            sk = new Socket("127.0.0.1", 8080);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void connect()
    {
        try
        {
            keyin = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(sk
                    .getInputStream()));
            wtr = new PrintWriter(sk.getOutputStream());

            while (true)
            {
                String get = keyin.readLine();
                if (get != null & get.length() > 0)
                {
                    wtr.println(get);
                    wtr.flush();
//                  wtr.close();
//                  System.out.println(get + "发送完毕");
                }
//                if (reader != null)
//                {
//                    String line = reader.readLine();
//                    System.out.println("从服务器来的信息：" + line);
//
//                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
