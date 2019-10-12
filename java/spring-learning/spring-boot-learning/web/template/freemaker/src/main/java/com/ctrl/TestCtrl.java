package com.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestCtrl {

    @RequestMapping("/1")
    public ModelAndView test1() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setId("1");
        user.setName("my name");
        modelAndView.addObject(user);
        modelAndView.setViewName("list");
        return modelAndView;
    }
}
