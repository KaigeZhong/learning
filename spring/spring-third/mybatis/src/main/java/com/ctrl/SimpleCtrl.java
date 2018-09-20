package com.ctrl;

import com.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleCtrl {
    @Autowired
    MybatisService mybatisService;

    @RequestMapping("/")
    public String test() {
        return "test";
    }

    @RequestMapping("/mybatis")
    public void mybatis() {
        mybatisService.mybatisTest();
    }
}
