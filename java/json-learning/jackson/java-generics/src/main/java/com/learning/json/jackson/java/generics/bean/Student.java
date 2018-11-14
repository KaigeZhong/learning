package com.learning.json.jackson.java.generics.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class) //JsonNaming不具备嵌套作用
public class Student {
    private Integer sId;
    private String sName;
    private Teacher sTeacher;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Teacher getsTeacher() {
        return sTeacher;
    }

    public void setsTeacher(Teacher sTeacher) {
        this.sTeacher = sTeacher;
    }
}
