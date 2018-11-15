package com.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("request scheme: " + req.getScheme());

        resp.addHeader("Connection", "Keep-Alive");
        resp.addHeader("Keep-Alive", "timeout=60, max=100");//过期时间60秒，max是最多100请求，强制断掉连接
        PrintWriter out = resp.getWriter();
        out.print("hello tomcat");
        out.flush();
        out.close();
    }

}