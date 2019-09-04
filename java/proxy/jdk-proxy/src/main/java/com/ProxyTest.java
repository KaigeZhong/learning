package com;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");//将生成的class文件落磁盘
        Target target = new Target();
        TargetInterface newProxyInstance = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName() + " start ...");
                        //反射知识点
                        Object invoke = method.invoke(target, args);
                        System.out.println(method.getName() + " end ...");
                        return invoke;
                    }
                });
        newProxyInstance.method1();
        newProxyInstance.method2();
    }
}