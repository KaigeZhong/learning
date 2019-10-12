package com.component.genric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GBean {
    private PBean pBean;

    private String name;


    @Value("${name}")
    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    public void setpBean(PBean pBean) {
        this.pBean = pBean;
    }
}
