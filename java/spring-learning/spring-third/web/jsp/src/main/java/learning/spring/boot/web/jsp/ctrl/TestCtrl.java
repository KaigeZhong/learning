package learning.spring.boot.web.jsp.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 只是用于测试服务是否启动起来，与jsp无关
 */
@RestController
public class TestCtrl {

  @RequestMapping("/")
  public String test() {
    return "test";
  }
}
