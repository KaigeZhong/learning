package com.lean.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("a", "aaa");
        cookie.setMaxAge(120);
        req.getInputStream();
        resp.addCookie(cookie);
        resp.setContentType("text/html");
        resp.getOutputStream().write("11111111111aaaaaaaa".getBytes());
        resp.getOutputStream().flush();

    }
}
