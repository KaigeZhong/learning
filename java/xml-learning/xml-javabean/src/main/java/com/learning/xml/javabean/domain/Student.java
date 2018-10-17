package com.learning.xml.javabean.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 注解注意点：
 * 1. 类上的注解  @XmlRootElement 是必不可少的。注解的name属性是可选的，不给节点默认生成为和类名一样，不过全是小写的<student></student>。指定name="studentBean"这生成指定的节点名称,且和指定的大小写一致，有空格的话空格也会出现在标签中。<studentBean></studentBean>
 * 2. 成员变量/属性上的注解 @XmlElement 可选的注解。不加或者只是加上@XmlElement,没有指定生成节点的名称，默认是和每个属性的名称一致，就是全变成小写。以age为例,<age></age>,如果给上name属性指定生成节点名称，则生成完全和name属性一致的节点出来，和类上的name属性一样。
 */
@XmlRootElement(name="studentBean")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String address;

    //  @XmlElement(name="name")
    public String getName() {
        return name;
    }

    @XmlElement
    public int getAge() {
        return age;
    }

    @XmlElement(name="studentAddress")
    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
