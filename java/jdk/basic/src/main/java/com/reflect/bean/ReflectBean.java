package com.reflect.bean;

import jdk.nashorn.internal.runtime.logging.Logger;

import javax.annotation.Resource;

@Logger
public class ReflectBean {
    @Resource
    private String name;

    public String getName() {
        return name;

    }

    public ReflectBean() {

    }

    private ReflectBean(String name) {
        this.name = name;
    }

    public static class ReflectInnerBean {
       private String innerName;

        public String getInnerName() {
            return innerName;
        }
    }
}
