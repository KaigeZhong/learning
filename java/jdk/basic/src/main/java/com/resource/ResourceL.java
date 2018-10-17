package com.resource;

public class ResourceL {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("a.xml"));
        System.out.println(ResourceL.class.getResource("/a.xml"));
    }
}