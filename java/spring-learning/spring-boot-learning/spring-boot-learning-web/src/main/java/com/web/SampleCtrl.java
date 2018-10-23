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
DispatcherServlet会在onRefresh()创建tomcat的时候在后台线程中实例化并注册到tomcat,
而DispatcherServlet的handlerMappings（例如RequestMappingHandlerMapping
）则会在后面的finishBeanFactoryInitialization(beanFactory)主线程中被实例化。
 */
//RestController 本质就是@ResponseBody + @Controller
@RestController
public class SampleCtrl {
    public SampleCtrl() {
        System.out.println();
    }

    /**
     * 1. dispatcher -> handlerMapping 找到HandlerExecutionChain(即interceptors + handler,
     * handler就是我们ctrl中某个具体的方法)
     * 2. 执行HandlerExecutionChain.applyPreHandle
     * 3. adapter执行handler。
     * 4. 执行HandlerExecutionChain.applyPostHandle
     * 5. 执行HandlerExecutionChain..triggerAfterCompletion
     * <p>
     * 参数的解析：根据不同的参数注解找到对应的HandlerMethodArgumentResolver进行解析, 部分解析器还会调用HttpMessageConverter转换器
     * PathVariableMethodArgumentResolver->只能读取路径中的变量
     * RequestParamMethodArgumentResolver->可以读取路径后面的参数以及body中的form表单数据
     * RequestResponseBodyMethodProcessor->可以读取body中json格式
     */

    @RequestMapping("/test")
    String test() {
        return "test";
    }

    //会采用RequestResponseBodyMethodProcessor进行请求题解析
    @RequestMapping("/body")
    String user(@RequestBody Body body) {
        return body.getId() + body.getName();
    }

    //会采用PathVariableMethodArgumentResolver进行路径解析
    @RequestMapping("/path/{name}")
    String home(@PathVariable("name") String name) {
        return "Hello World! " + name;
    }

    //会采用RequestParamMethodArgumentResolver进行请求参数解析
    @RequestMapping("/param")
    String account(@RequestParam Integer id, @RequestParam String name) {
        return id + name;
    }


    //会采用ServletModelAttributeMethodProcessor进行请求参数解析
    @RequestMapping("/modelattr")
    String modelAttr(@ModelAttribute Pojo pojo) {
        return pojo.getId() + pojo.getName();
    }

    //会采用RequestParamMethodArgumentResolver进行请求参数解析
    @RequestMapping("/non/general")
    String general(String name) {
        return name;
    }

    //会采用ServletModelAttributeMethodProcessor进行请求参数解析
    @RequestMapping("/non")
    String non(Non non) {
        return non.getId() + non.getName();
    }


}
