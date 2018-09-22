package com.beanvalidation.customvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AgeValidator.class)
public @interface Age {

  String message() default "Must be greater than {value}";
  int value();

  //下面这两个属性必须添加
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}