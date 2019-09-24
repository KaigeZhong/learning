package com.lean.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestCtrl {
    @RequestMapping("/1")
    @ResponseBody
    public String test1(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestURI();
        request.getHeader("Host");
        return "success111111111111111111111111111111111";
    }

    @RequestMapping("/2")
    @ResponseBody
    public User test2(@RequestBody User user) {

        return user;
    }
}
