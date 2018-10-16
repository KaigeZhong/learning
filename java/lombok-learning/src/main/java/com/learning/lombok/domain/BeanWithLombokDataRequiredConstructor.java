package com.learning.lombok.domain;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BeanWithLombokDataRequiredConstructor {
    private Integer id;
    private String name;
    private String age;
    private String gender;
}
