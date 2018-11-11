package learning.spring.boot.web.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * .@ExceptionHandler本身的作用范围是所在的class内，但RestControllerAdvice可以使@ExceptionHandler、@InitBinder
 * 、@ModelAttribute注解的方法应用到所有的 @RequestMapping注解的方法。
 * Note: RestControllerAdvice只是一个Component而不是Controller，所以不支持在方法上注解RequestMapping
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(Exception.class)
  public String exceptionHandler(Exception e) {
    return "Unknown error from global exception handler: " + e.getStackTrace();
  }
}
