package com.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleCtrl {

  @RequestMapping("/testException")
  public String testException(@RequestParam String status) {
    if ("1".equals(status)) {
      throw new IllegalStateException();
    } else if ("0".equals(status)) {
      return "success";
    } else {
      throw new IllegalArgumentException();
    }
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(IllegalStateException.class)//@ExceptionHandler的作用范围仅限于当前class, 不会处理其他class的异常
  public String exceptionHandler() {
    return "IllegalStateException";
  }
}
