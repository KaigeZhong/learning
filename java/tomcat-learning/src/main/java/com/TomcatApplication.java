package com;

import com.servlet.HelloServlet;
import com.servlet.HeyServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

import java.io.File;

public class TomcatApplication {

  public static void main(String[] args) throws LifecycleException {
    /**
     * tomcat本质上就是个facade
     */
    Tomcat tomcat = new Tomcat();

    /**
     * 设置context, 在设置context的同时会自动配置server,engine和host
     * getHost->getEngine->getService->getServer
     */


    /**
     * {
     *     "server":{
     *         "services":[
     *             {
     *                 "connectors":[
     *                     {
     *                         "protocolHandler":{
     *                             "endpoint":{
     *                                 "acceptors":[
     *                                     {
     *                                         "comments":"acceptor接受连接交给poller的queue中，因为accptors和pollers都在endpoint中，所以poller和endpoint相互可见"
     *                                     }
     *                                 ],
     *                                 "pollers":[
     *                                     {
     *                                         "events":"queue接收来至于acceptors的新连接",
     *                                         "comments":"1. 将queue中的连接注册到selector中 2. 并从selector中获取key，交给executor， 因为poller和executor都在endpoint中，所以相互可见"
     *                                     }
     *                                 ],
     *                                 "executor":{
     *                                     "comments":"执行new SocketProcessor（），SocketProcessor会交给handler处理"
     *                                 },
     *                                 "handler":{
     *                                     "comments":"1. 该handler为ConnectionHandler，handler回去创建new Http11Processor（），然后交给Http11Processor 2. Http11Processor会执行Http11Processor.process, Http11Processor.service, 然后调用getAdapter() .service交给adpater处理，这里的adapter就是 .connectors中的adapter。3.adapter.service会去调用connector .getService().getContainer().getPipeline().getFirst() .invoke( request, response);进入容器 4. 每个容器（engine，host，context）都有自己的pipeline，pipeline持有Valve链表，pipeline类似于filter chain，Ｖalvｅ类似于filter"
     *                                 }
     *                             }
     *                         },
     *                         "adapter":{
     *                             "comments":"该adapter为CoyoteAdapter"
     *                         }
     *                     }
     *                 ],
     *                 "engine":{
     *                     "children(host: key,value --> hostname,host)":[
     *                         {
     *                             "children(context: key, value --> path,context)":[
     *                                 {
     *                                     "children(Servlet: key, value --> servletname,servlet)":[
     *
     *                                     ],
     *                                     "servletMappings":[
     *
     *                                     ]
     *                                 }
     *                             ]
     *                         }
     *                     ]
     *                 }
     *             }
     *         ]
     *     }
     * }
     */

    /**
     *  配置server
     */
    tomcat.setBaseDir(Thread.currentThread().getContextClassLoader().getResource("").getFile());
    Server server = tomcat.getServer();
        /*
        设置catalinaHome和catalinaBase，也可以通过tomcat.setBaseDir(Thread.currentThread()
        .getContextClassLoader().getResource("").getFile())进行设置
        catalinaHome是Tomcat的安装目录，catalinaBase是Tomcat的工作目录.默认两者是相同的
        如果我们想要运行Tomcat的多个实例,我们可以配置多个工作目录，每个运行实例独占一个工作目录，但是共享同一个安装目录
        Tomcat每个运行实例需要使用自己的conf、logs、temp、webapps、work和shared目录，因此catalinaBase就指向这些目录的根目录
        */
    server.setCatalinaHome(
      new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()));
    server.setCatalinaBase(
      new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()));
    /*设置server端口,tomcat.getServer().await()方法用于监听该端口号，可远程通过telnet，输入SHUTDOWN命令关闭tomcat*/
    server.setPort(8005);

    /**
     *  配置service
     */
    tomcat.getService().setName("myService");
    /**
     * 配置connector
     */
    //设置connector端口,在创建connector时，默认采用tomcat中的port，所以也可以通过tomcat.setPort(9080)来配置端口号
    tomcat.getConnector().setPort(9080);

    /**
     * 配置engine
     */
    tomcat.getEngine().setName("myEngine");
    /**
     * 配置host
     */
    //tomcat会根据当前请求的host header来确定使用哪个host
    tomcat.getHost().setName("localhost");
    tomcat.getHost().setAutoDeploy(false);
    /**
     * 配置context
     */
    StandardContext context = new StandardContext();
    context.setPath("/mycontext");
    context.addLifecycleListener(new FixContextListener());
    tomcat.getHost().addChild(context);
    /**
     * 配置servlet
     * localhost:9080/mycontext/hello
     */
    //设置servlet,也可以调用tomcat.addServlet(context, "helloServlet", new HelloServlet());
    tomcat.addServlet("/mycontext", "helloServlet", new HelloServlet());
    //添加servlet的映射，curl http://localhost:9080/mycontext/hello
    context.addServletMappingDecoded("/hello", "helloServlet");
    tomcat.addServlet("/mycontext", "heyServlet", new HeyServlet());
    context.addServletMappingDecoded("/hey", "heyServlet");

    /**
     * 启动tomcat
     */
    tomcat.start();
    //监听上面设置的8005端口，用于远程关闭tomcat，默认为端口号为-1，即不打开
    tomcat.getServer().await();
  }
}
