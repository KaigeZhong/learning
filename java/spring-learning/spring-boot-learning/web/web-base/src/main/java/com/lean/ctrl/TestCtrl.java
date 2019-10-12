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
    public User test1(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setId("1");
        user.setName("my name");
        return user;
    }
}
