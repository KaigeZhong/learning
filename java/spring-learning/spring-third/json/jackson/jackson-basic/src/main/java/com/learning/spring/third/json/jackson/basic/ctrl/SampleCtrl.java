package com.learning.spring.third.json.jackson.basic.ctrl;

import com.learning.spring.third.json.jackson.basic.bean.JacksonBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl {

    @RequestMapping("/")
    public JacksonBean jacksonApp() {
        JacksonBean jacksonBean = new JacksonBean();
        jacksonBean.setjId(1);
        jacksonBean.setjName("Kevin");
        return jacksonBean;
    }
}
