package com.learning.spring.third.json.jackson.instance.ctrl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.spring.third.json.jackson.instance.bean.JacksonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl {
    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping("/")
    public String jacksonApp() throws JsonProcessingException {
        JacksonBean jacksonBean = new JacksonBean();
        jacksonBean.setjId(1);
        jacksonBean.setjName("Kevin");
        String s = objectMapper.writeValueAsString(jacksonBean);
        return s;
    }
}
