package learning.spring.boot.web.tomcat.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl {
  @RequestMapping("/")
  public String test() {
    return "test";
  }
}
