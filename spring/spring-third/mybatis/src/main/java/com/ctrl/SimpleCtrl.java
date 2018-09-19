package com.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleCtrl {

    @RequestMapping("/")
    public String test() {
        return "test";
    }
}
