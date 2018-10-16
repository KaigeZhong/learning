package com.learning.lombok.domain;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class BeanWithLombokNotNull {
    private Integer id;
    /**
     * 生成空检查代码
     * 1. If put on a parameter, lombok will insert a null-check at the start of the method / constructor's body
     * 2. If put on a field, any generated method assigning a value to this field will also produce these nullchecks
     */

    // 生成后的代码
    // 只有在赋值时才做null check，这里set会检查，get不会
    @NonNull
    //public void setName(@NonNull String name) {
    //        if (name == null) {
    //            throw new NullPointerException("name");
    //        } else {
    //            this.name = name;
    //        }
    //    }
    @Setter
    @Getter
    private String name;
    private String age;
    private String gender;


    // 生成后的代码
    // public void updateName(@NonNull String age) {
    //        if (age == null) {
    //            throw new NullPointerException("age");
    //        } else {
    //            this.age = age;
    //        }
    //    }
    public void updateName(@NonNull String age) {
        this.age = age;
    }
}
