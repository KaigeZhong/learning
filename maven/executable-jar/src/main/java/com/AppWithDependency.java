package com;

import org.apache.commons.lang3.StringUtils;

public class AppWithDependency {
    public static void main(String[] args) {
        StringUtils.isBlank("");
        System.out.println("hello executable jar with dependency");
    }
}
