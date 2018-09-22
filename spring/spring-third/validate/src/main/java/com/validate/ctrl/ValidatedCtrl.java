package com.validate.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@RestController
@Validated
public class ValidatedCtrl {
  /**
   * 如果验证不通过会直接抛出异常
   * 可通过异常handler来获取验证信息
   * @ControllerAdvice public class GlobalExceptionHandler { @ExceptionHandler
   * (ConstraintViolationException.class) @ResponseBody @ResponseStatus(HttpStatus.BAD_REQUEST)
   * public String handleValidationException(ConstraintViolationException e){ for
   * (ConstraintViolation<?> s:e.getConstraintViolations()){ return s.getInvalidValue()+": "+s
   * .getMessage(); } return "请求参数不合法"; } }
   *
   * ---------------------
   *
   * 本文来自 曹金桂 的CSDN 博客 ，全文地址请点击：https://blog.csdn
   * .net/onupway/article/details/78367629?utm_source=copy
   * @param vStr
   * @return
   */
  @RequestMapping("/param")
  public String validString(@RequestParam @Size(min = 1, max = 3) String vStr) {
    return vStr;
  }
}
