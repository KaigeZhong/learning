package com.learning.spring.third.json.fastjson.ctrl;

import com.learning.spring.third.json.fastjson.bean.FastJsonBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl {

    @RequestMapping("/")
    public FastJsonBean fastJsonBean(@RequestBody FastJsonBean fastJsonBeanR) {
        if (fastJsonBeanR != null) {
            System.out.println(fastJsonBeanR.getjId() + ", " + fastJsonBeanR.getjName());
        }
        FastJsonBean fastJsonBean = new FastJsonBean();
        fastJsonBean.setjId(1);
        fastJsonBean.setjName("Kevin");
        return fastJsonBean;
    }
}