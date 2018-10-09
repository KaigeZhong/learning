package com.uri;

import java.net.URI;

public class URIL {
    public static void main(String[] args) {
        String target = "node1:90";
        //host 不能含有_字符
//        String target = "t_node1:90";
        boolean hasScheme = target.contains("://");
        URI uri = hasScheme ? URI.create(target) : URI.create("dummyscheme://"+target);
        System.out.println(uri.getHost());
        System.out.println(uri.getPort());
        System.out.println(uri.getPath());
    }
}
