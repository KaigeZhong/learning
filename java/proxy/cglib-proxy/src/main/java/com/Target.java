package com;


public class Target {
    private String name;

    public String method1() {
        System.out.println("method1 running..." + name);
        return "aaa";
    }

    public String method2() {
        System.out.println("method2 running..." + name);
        return "bbb";
    }

    public void setName(String name) {
        this.name = name;
    }
}