package com.component.genric;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PBean {

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    public void close() {
        System.out.println("init");
    }
}
