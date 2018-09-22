package com.beanvalidation.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Integer> {

  protected int minAge;
  @Override
  public void initialize(Age ageValue) {
    this.minAge = ageValue.value();
  }

  @Override
  public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
    // null values are valid
    if ( age == null ) {
      return true;
    }
    return age >= minAge;
  }

}