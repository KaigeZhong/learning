package com.learning.spring.third.swaggerconfig.ctrl;

import com.learning.spring.third.swaggerconfig.bean.BeanWithSwagger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrlWithSwagger {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public BeanWithSwagger beanWithSwagger() {
        return new BeanWithSwagger(1, "Kevin", "18");
    }
}
