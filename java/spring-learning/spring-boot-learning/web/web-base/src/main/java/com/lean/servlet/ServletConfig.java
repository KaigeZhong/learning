package com.lean.servlet;

import com.lean.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet());
        servletRegistrationBean.addUrlMappings("/custom-servlet");
        return servletRegistrationBean;
    }
}
