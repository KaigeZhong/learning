package com.beanvalidation;

import com.beanvalidation.bean.Student;
import com.beanvalidation.bean.Teacher;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.LinkedList;
import java.util.Set;

public class ValidationL {
  public static void main(String[] args) {
    Student xiaoming = getBean();
    validate(xiaoming);

  }

  private static Student getBean() {
    Student bean = new Student();
    bean.setName(null);
    bean.setAge(17);
    LinkedList<Teacher> teachers = new LinkedList<>();
    Teacher teacher = new Teacher();
    teacher.setName("语文");
    teachers.add(teacher);
    teachers.add(new Teacher());
    bean.setTeacher(teachers);
    return bean;
  }

  //default: the first available provider will be returned.
//  private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

  /**
   * 1、普通模式（默认是这个模式）
   *
   * 　　普通模式(会校验完所有的属性，然后返回所有的验证失败信息)
   * 2、快速失败返回模式
   *
   * 　　快速失败返回模式(只要有一个验证失败，则返回)
   */
  private static ValidatorFactory factory =
    Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
//  private static ValidatorFactory factory =
//    Validation.byProvider(HibernateValidator.class).configure().failFast(true)
//      .buildValidatorFactory();

  public static <T> void validate(T t) {
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

    for (ConstraintViolation<T> constraintViolation : constraintViolations) {
      System.out.println(constraintViolation.getMessage());
    }
  }
}
