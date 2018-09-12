package com.web;

import com.web.bean.Body;
import com.web.bean.Non;
import com.web.bean.Pojo;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
DispatcherServlet会在onRefresh()创建tomcat的时候在后台线程中实例化并注册到tomcat
而DispatcherServlet的handlerMappings（例如RequestMappingHandlerMapping
）则会在后面的finishBeanFactoryInitialization(beanFactory)主线程中被实例化。
 */
@RestController
public class SampleCtrl {
  public SampleCtrl() {
    System.out.println();
  }

  @RequestMapping("/test")
  String test() {
    return "test";
  }
  //会采用PathVariableMethodArgumentResolver进行路径解析
  @RequestMapping("/{name}")
  String home(@PathVariable("name") String name) {
    return "Hello World! " + name;
  }
  //会采用RequestParamMethodArgumentResolver进行请求参数解析
  @RequestMapping("/param")
  String account(@RequestParam Integer id, @RequestParam String name) {
    return id + name;
  }

  //会采用RequestResponseBodyMethodProcessor进行请求题解析
  @RequestMapping("/body")
  String user(@RequestBody Body body) {
    return body.getId() + body.getName();
  }

  //会采用ServletModelAttributeMethodProcessor进行请求参数解析
  @RequestMapping("/non")
  String non(Non non) {
    return non.getId() + non.getName();
  }
  //会采用RequestParamMethodArgumentResolver进行请求参数解析
  @RequestMapping("/non/general")
  String general(String name) {
    return name;
  }
  //会采用ServletModelAttributeMethodProcessor进行请求参数解析
  @RequestMapping("/modelattr")
  String modelAttr(@ModelAttribute Pojo pojo) {
    return pojo.getId() + pojo.getName();
  }

}
