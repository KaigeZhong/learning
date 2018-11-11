package learning.spring.boot.web.ctrl.ctrll;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/http")
public class HttpServletRequestCtrl {
    @RequestMapping("/servlet")
    String httpServlet(HttpServletRequest request) {
        //path
        System.out.println(request.getPathInfo());
        System.out.println(request.getPathTranslated());
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());

        //uri, url
        //http的path等于：request.getRequestURI() + “？” + request.getQueryString()
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());

        //query
        System.out.println(request.getQueryString());
        return "httpservlet";
    }
}
