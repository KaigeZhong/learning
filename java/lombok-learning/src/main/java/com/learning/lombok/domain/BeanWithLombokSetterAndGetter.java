package com.learning.lombok.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeanWithLombokSetterAndGetter {
    private Integer id;
    // *     public int getFoo() {
    // *         return this.foo;
    // *     }
    @Getter
    private String name;
    // *     public void setFoo(int foo) {
    // *         this.foo = foo;
    // *     }
    @Setter
    private String age;
    @Getter
    @Setter
    private String gender;
}
