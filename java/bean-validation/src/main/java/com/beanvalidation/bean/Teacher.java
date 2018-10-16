package com.beanvalidation.bean;

import javax.validation.constraints.NotBlank;

public class Teacher {
  @NotBlank(message = "teacher name can not be blank")
  private String name;

  public void setName(String name) {
    this.name = name;
  }
}
