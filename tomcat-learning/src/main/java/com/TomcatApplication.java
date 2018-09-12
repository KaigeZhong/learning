package com;

import com.servlet.HomeServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

public class TomcatApplication {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        //设置server端口,tomcat.getServer().await()方法用于监听该端口号，可远程通过telnet，输入SHUTDOWN命令关闭tomcat
//        tomcat.getServer().setPort(8005);
        //设置connector端口
        tomcat.setPort(9080);
        //设置work根目录
        tomcat.setBaseDir(Thread.currentThread().getContextClassLoader().getResource("").getFile());
//        tomcat.getHost().setAutoDeploy(false);

        /**
         * 设置context, 在设置context的同时会自动配置engine和host
         */
        StandardContext context = new StandardContext();
        context.setPath("/book");
        context.addLifecycleListener(new FixContextListener());
        tomcat.getHost().addChild(context);
        //设置servlet
        tomcat.addServlet(context, "homeServlet", new HomeServlet());
        //curl http://localhost:9080/book/hello
        context.addServletMappingDecoded("/hello", "homeServlet");
        tomcat.start();
        //监听上面设置的8005端口，用于远程关闭tomcat，默认为端口号为-1，即不打开
        tomcat.getServer().await();
    }
}