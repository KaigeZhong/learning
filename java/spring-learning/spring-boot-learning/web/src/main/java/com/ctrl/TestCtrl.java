package com.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestCtrl {
    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestURI();
        request.getHeader("Host");
        return "success111111111111111111111111111111111";
    }
}
