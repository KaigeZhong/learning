package com.beanvalidation.bean;

import com.beanvalidation.customvalidator.Age;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Student {
  @NotNull(message = "名字不能为空")
  private String name;
  @Age(18)
  private Integer age;
  //可实现嵌套验证, 但只有加在filed上才会嵌套验证，加在方法参数前, 对参数进行嵌套验证
  @Valid
  private List<Teacher> teacher;

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public void setTeacher(List<Teacher> teacher) {
    this.teacher = teacher;
  }
}
