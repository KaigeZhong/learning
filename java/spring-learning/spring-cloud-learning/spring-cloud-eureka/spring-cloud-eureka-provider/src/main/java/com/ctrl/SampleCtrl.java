package com.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl {
    @RequestMapping("/provider")
    public String home() {
        return "Hello provider";
    }
}
