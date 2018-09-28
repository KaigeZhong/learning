package com.validate.ctrl;

import com.validate.bean.ValidateBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Valid可以在filed上实现嵌套验证
 * Validated不能在field上，
 */
@RestController
public class SampleCtrl {

  /*{"userName":"dd","age":120,"isFalse":true,"birthday":"21010-21-12"}*/
  @RequestMapping("/")
  public String validate(@RequestBody @Valid ValidateBean validateBean, BindingResult result) {
    if(result.hasErrors()){
      /**
       * 如果验证不通过会将相应的信息放到result里面
       */
      for (ObjectError error : result.getAllErrors()) {
        System.out.println(error.getDefaultMessage());
      }
    }
    System.out.println(validateBean);
    return "success";
  }
}
